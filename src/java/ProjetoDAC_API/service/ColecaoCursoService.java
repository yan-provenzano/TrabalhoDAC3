package ProjetoDAC_API.service;

import ProjetoDAC_API.model.colecaoCursoModel;
import ProjetoDAC_API.model.RecursoModel;
import ProjetoDAC_API.repository.colecaoCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColecaoCursoService {

    @Autowired
    private ColecaoCursoRepository repository;

    @Autowired
    private recursoService recursoService;

    public colecaoCursoModel saalvarColecaoCurso(colecaoCursoModel colecaoCurso) {

        colecaoCursoModel newColecaoCurso = repository.save(colecaoCurso);

        colecaoCurso.getrecursos().forEach(recurso -> {

            ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

            if (existingrecurso.getBody() instanceof RecursoModel) {

                ((RecursoModel) existingrecurso.getBody()).setcolecao(colecaoCurso);

                recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

            }

        });

        return newColecaoCurso;
    }

    public List<colecaoCursoModel> getColecaoCursos() {
        return (List<colecaoCursoModel>) repository.findAll();
    }

    public ResponseEntity<?> getColecaoCurso(Long id) {
        colecaoCursoModel existingcolecaoCurso = repository
                .findById(id)
                .orElse(null);

        if (existingcolecaoCurso == null)
            return new ResponseEntity<>("{\"message\":\"Curso não existe\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(existingcolecaoCurso, HttpStatus.OK);
    }

    public ResponseEntity<?> updateColecaoCurso(colecaoCursoModel colecaoCurso) {
        colecaoCursoModel existingcolecaoCurso = repository
                .findById(colecaoCurso.getId())
                .orElse(null);

        if (existingcolecaoCurso == null)
            return new ResponseEntity<>("{\"message\":\"ColecaoCurso não existe\"}", HttpStatus.NOT_FOUND);

        if (colecaoCurso.getTitle() != null)
            existingcolecaoCurso.setTitle(colecaoCurso.getTitle());

        if (colecaoCurso.getDescription() != null)
            existingcolecaoCurso.setDescription(colecaoCurso.getDescription());

        if (colecaoCurso.getImage() != null)
            existingcolecaoCurso.setImage(colecaoCurso.getImage());

        if (colecaoCurso.getrecursos() != null) {

            //Removing old recursos
            existingcolecaoCurso.getrecursos().forEach(recurso -> {

                ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

                if (existingrecurso.getBody() instanceof RecursoModel) {

                    ((RecursoModel) existingrecurso.getBody()).setcolecao(null);

                    recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

                }

            });

            ArrayList<RecursoModel> newrecursos = new ArrayList<RecursoModel>();

            //Removing old recursos
            colecaoCurso.getrecursos().forEach(recurso -> {

                ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

                if (existingrecurso.getBody() instanceof RecursoModel) {

                    ((RecursoModel) existingrecurso.getBody()).setcolecao(colecaoCurso);

                    recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

                    newrecursos.add((RecursoModel) existingrecurso.getBody());

                }

            });

            existingcolecaoCurso.setrecursos(newrecursos);

        }

        if (colecaoCurso.getRegisterDate() != null)
            existingcolecaoCurso.setRegisterDate(colecaoCurso.getRegisterDate());

        return new ResponseEntity<>(repository.save(existingcolecaoCurso), HttpStatus.OK);

    }

    public ResponseEntity<?> deletecolecaoCurso(Long id) {

        colecaoCursoModel existingcolecaoCurso = repository
                .findById(id)
                .orElse(null);

        if (existingcolecaoCurso == null)
            return new ResponseEntity<>("{\"message\":\"colecaoCurso não existe\"}", HttpStatus.NOT_FOUND);

        //Removing relationship
        existingcolecaoCurso.getrecursos().forEach(recurso -> {

            ResponseEntity<?> existingrecurso = recursoService.getrecurso(recurso.getId());

            if (existingrecurso.getBody() instanceof RecursoModel) {

                ((RecursoModel) existingrecurso.getBody()).setcolecao(null);

                recursoService.updaterecurso((RecursoModel) existingrecurso.getBody());

            }

        });

        repository.deleteById(id);

        return new ResponseEntity<>("{\"message\":\"removed colecaoCurso\"}", HttpStatus.OK);
    }


}
