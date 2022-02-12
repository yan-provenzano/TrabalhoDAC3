package ProjetoDAC_API.service;

import ProjetoDAC_API.model.colecaoEventoModel;
import ProjetoDAC_API.model.RecursoModel;
import ProjetoDAC_API.repository.colecaoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ColecaoEventoService {

    @Autowired
    private ColecaoEventoRepository repository;

    @Autowired
    private RecursoService recursoService;

    public ColecaoEventoModel salvarColecaoEvento(colecaoEventoModel colecaoEvento) {

        colecaoEventoModel newcolecaoEvento = repository.save(colecaoEvento);

        colecaoEvento.getrecursos().forEach(recurso -> {

            ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

            if (existingrecurso.getBody() instanceof RecursoModel) {

                ((RecursoModel) existingrecurso.getBody()).setcolecao(colecaoEvento);

                recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

            }

        });

        return NewColecaoEvento;
    }

    public List<colecaoEventoModel> getColecaoEventos() {
        return (List<colecaoEventoModel>) repository.findAll();
    }

    public ResponseEntity<?> getcolecaoEvento(Long id) {
        colecaoEventoModel existingcolecaoEvento = repository
                .findById(id)
                .orElse(null);

        if (existingcolecaoEvento == null)
            return new ResponseEntity<>("{\"message\":\"Evento não existe\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(existingcolecaoEvento, HttpStatus.OK);
    }

    public List<colecaoEventoModel> getColecaoEventosByPeriodoDeTempo(Date dataInicio, Date dataFim) {
        return repository.findByPeriodoDeTempo(dataInicio, dataFim);
    }

    public ResponseEntity<?> updateColecaoEvento(colecaoEventoModel colecaoEvento) {
        colecaoEventoModel existingcolecaoEvento = repository
                .findById(colecaoEvento.getId())
                .orElse(null);

        if (existingcolecaoEvento == null)
            return new ResponseEntity<>("{\"message\":\"colecaoEvento não existe\"}", HttpStatus.NOT_FOUND);

        if (colecaoEvento.getTitle() != null)
            existingcolecaoEvento.setTitle(colecaoEvento.getTitle());

        if (colecaoEvento.getDescription() != null)
            existingcolecaoEvento.setDescription(colecaoEvento.getDescription());

        if (colecaoEvento.getImage() != null)
            existingcolecaoEvento.setImage(colecaoEvento.getImage());

        if (colecaoEvento.getrecursos() != null) {

            //Removing old recursos
            existingcolecaoEvento.getrecursos().forEach(recurso -> {

                ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

                if (existingrecurso.getBody() instanceof RecursoModel) {

                    ((RecursoModel) existingrecurso.getBody()).setcolecao(null);

                    recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

                }

            });

            ArrayList<RecursoModel> newrecursos = new ArrayList<RecursoModel>();

            //Removing old recursos
            colecaoEvento.getrecursos().forEach(recurso -> {

                ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

                if (existingrecurso.getBody() instanceof RecursoModel) {

                    ((RecursoModel) existingrecurso.getBody()).setcolecao(colecaoEvento);

                    recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

                    newrecursos.add((RecursoModel) existingrecurso.getBody());

                }

            });

            existingcolecaoEvento.setrecursos(newrecursos);

        }

        if (colecaoEvento.getdataInicio() != null)
            existingcolecaoEvento.setdataInicio(colecaoEvento.getdataInicio());

        if (colecaoEvento.getdataFim() != null)
            existingcolecaoEvento.setdataFim(colecaoEvento.getdataFim());

        return new ResponseEntity<>(repository.save(existingcolecaoEvento), HttpStatus.OK);

    }

    public ResponseEntity<?> deletecolecaoEvento(Long id) {

        colecaoEventoModel existingcolecaoEvento = repository
                .findById(id)
                .orElse(null);

        if (existingcolecaoEvento == null)
            return new ResponseEntity<>("{\"message\":\"Evento não existe\"}", HttpStatus.NOT_FOUND);

        //Removing relationship
        existingcolecaoEvento.getrecursos().forEach(recurso -> {

            ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

            if (existingrecurso.getBody() instanceof RecursoModel) {

                ((RecursoModel) existingrecurso.getBody()).setcolecao(null);

                recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

            }

        });

        repository.deleteById(id);

        return new ResponseEntity<>("{\"message\":\"Evento Removido\"}", HttpStatus.OK);
    }

}
