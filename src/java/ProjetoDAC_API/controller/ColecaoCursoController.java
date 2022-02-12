package ProjetoDAC_API.controller;

import ProjetoDAC_API.model.colecaoCursoModel;
import ProjetoDAC_API.service.colecaoCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ColecaoCursoController {

    @Autowired
    private ColecaoCursoService service;

    @PostMapping("/colecaoCurso")
    public colecaoCursoModel create(@RequestBody colecaoCursoModel colecaoCurso) {
        return service.salvarColecaoCurso(colecaoCurso);
    }

    @GetMapping("/colecaoCurso/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getcolecaoCurso(id);
    }

    @GetMapping("/colecaoCurso")
    public List<colecaoCursoModel> readAll() {
        return service.getcolecaoCursos();
    }

    @PutMapping("/colecaoCurso")
    public ResponseEntity<?> update(@RequestBody colecaoCursoModel colecaoCurso) {
        return service.updatecolecaoCurso(colecaoCurso);
    }

    @DeleteMapping("/colecaoCurso/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deletecolecaoCurso(id);
    }

}
