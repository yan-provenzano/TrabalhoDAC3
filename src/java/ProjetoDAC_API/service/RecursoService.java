package ProjetoDAC_API.service;

import ProjetoDAC_API.model.AutorModel;
import ProjetoDAC_API.model.RecursoModel;
import ProjetoDAC_API.repository.recursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository repository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private ColecaoService colecaoService;

    public RecursoModel salvarRecurso(@RequestBody RecursoModel recurso) {

        RecursoModel newrecurso = repository.save(recurso);

        recurso.getautors().forEach(autor -> {

            ResponseEntity<?> existingautor = autorService.getautor(autor.getId());

            if (existingautor.getBody() instanceof AutorModel) {

                ((AutorModel) existingautor.getBody()).getrecursos().add(recurso);

                autorService.updateautor((AutorModel) existingautor.getBody());

            }

        });

        return newrecurso;
    }

    public List<RecursoModel> getRecursos() {
        return (List<RecursoModel>) repository.findAll();
    }

    public ResponseEntity<?> getRecurso(Long id) {
        RecursoModel existingrecurso = repository
                .findById(id)
                .orElse(null);

        if (existingrecurso == null)
            return new ResponseEntity<>("{\"message\":\"recurso não existe\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(existingrecurso, HttpStatus.OK);
    }

    public ResponseEntity<?> getRecursoByautor(Long id) {
        ResponseEntity<?> existingautor = autorService.getautor(id);

        if (existingautor.getStatusCodeValue() == 404)
            return new ResponseEntity<>("{\"message\":\"autor não existe\"}", HttpStatus.NOT_FOUND);

        List<RecursoModel> recursos = repository.findByautor(id);

        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    public ResponseEntity<?> getRecursoBycolecao(Long id) {

        ResponseEntity<?> existingcolecaoEvento = colecaoService.getcolecao(id);

        if (existingcolecaoEvento.getStatusCodeValue() == 404) {

            ResponseEntity<?> existingcolecaoCurso = colecaoService.getcolecao(id);

            if (existingcolecaoCurso.getStatusCodeValue() == 404)
                return new ResponseEntity<>("{\"message\":\"colecao não existe\"}", HttpStatus.NOT_FOUND);

        }

        List<RecursoModel> recursos = repository.findBycolecao(id);

        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    public ResponseEntity<?> updateRecurso(RecursoModel recurso) {
        RecursoModel existingrecurso = repository
                .findById(recurso.getId())
                .orElse(null);

        if (existingrecurso == null)
            return new ResponseEntity<>("{\"message\":\"recurso não existe\"}", HttpStatus.NOT_FOUND);

        if (recurso.getTitle() != null)
            existingrecurso.setTitle(recurso.getTitle());

        if (recurso.getDescription() != null)
            existingrecurso.setDescription(recurso.getDescription());

        if (recurso.getLink() != null)
            existingrecurso.setLink(recurso.getLink());

        if (recurso.getImage() != null)
            existingrecurso.setImage(recurso.getImage());

        if (recurso.getCreatedAt() != null)
            existingrecurso.setCreatedAt(recurso.getCreatedAt());

        if (recurso.getRegisteredAt() != null)
            existingrecurso.setRegisteredAt(recurso.getRegisteredAt());

        if (recurso.getKeyWord() != null)
            existingrecurso.setKeyWord(recurso.getKeyWord());

        if (recurso.getautors() != null) {

            existingrecurso.getautors().forEach(autor -> {

                ResponseEntity<?> existingautor = autorService.getautor(autor.getId());

                if (existingautor.getBody() instanceof AutorModel) {

                    ((AutorModel) existingautor.getBody()).getrecursos().removeIf(r ->
                            r.getId() == existingrecurso.getId()
                    );

                }

            });

            ArrayList<AutorModel> newautores = new ArrayList<AutorModel>();
            
            //adding new autors
            recurso.getautors().forEach(autor -> {

                ResponseEntity<?> existingautor = autorService.getautor(autor.getId());

                if (existingautor.getBody() instanceof AutorModel) {

                    ((AutorModel) existingautor.getBody()).getrecursos().add(recurso);

                    autorService.updateautor((AutorModel) existingautor.getBody());

                    newautors.add((AutorModel) existingautor.getBody());

                }

            });
            
            existingrecurso.setautors(newautores);

        }

        if (recurso.getcolecao() != null)
            existingrecurso.setcolecao(recurso.getcolecao());

        return new ResponseEntity<>(repository.save(existingrecurso), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteRecurso(Long id) {

        RecursoModel existingrecurso = repository
                .findById(id)
                .orElse(null);

        if (existingrecurso == null)
            return new ResponseEntity<>("{\"message\":\"recurso não existe\"}", HttpStatus.NOT_FOUND);

        existingrecurso.getautors().forEach(autor -> {

            ResponseEntity<?> existingautor = autorService.getautor(autor.getId());

            if (existingautor.getBody() instanceof AutorModel) {

                ((AutorModel) existingautor.getBody()).getrecursos().removeIf(r ->
                        r.getId() == existingrecurso.getId()
                );

            }

        });

        repository.deleteById(id);

        return new ResponseEntity<>("{\"message\":\"Recurso removido\"}", HttpStatus.OK);
    }

}
