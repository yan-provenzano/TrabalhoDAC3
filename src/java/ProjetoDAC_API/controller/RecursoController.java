package ProjetoDAC_API.controller;

import ProjetoDAC_API.model.AutorModel;
import ProjetoDAC_API.model.RecursoModel;
import ProjetoDAC_API.service.recursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class recursocontroller {

    @Autowired
    private RecursoService service;

    @PostMapping("/recurso")
    public RecursoModel create(@RequestBody RecursoModel recurso) {
        return service.salvarrecurso(recurso);
    }

    @GetMapping("/recurso")
    public List<RecursoModel> readAll() {
        return service.getrecursos();
    }

    @GetMapping("/recurso/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getrecurso(id);
    }

    @GetMapping("/recurso/autor/{id}")
    public ResponseEntity<?> readByautor(@PathVariable("id") Long id) {
        return service.getrecursoByautor(id);
    }

    @GetMapping("/recurso/colecao/{id}")
    public ResponseEntity<?> readBycolecao(@PathVariable("id") Long id) {
        return service.getrecursoBycolecao(id);
    }

    @PutMapping("/recurso")
    public ResponseEntity<?> update(@RequestBody RecursoModel recurso) {
        return service.updaterecurso(recurso);
    }

    @DeleteMapping("/recurso/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deleterecurso(id);
    }

}
