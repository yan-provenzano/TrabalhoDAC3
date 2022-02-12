package ProjetoDAC_API.controller;

import ProjetoDAC_API.model.AutorModel;
import ProjetoDAC_API.service.autorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping("/autor")
    public AutorModel create(@RequestBody AutorModel autor) {
        return service.salvarautor(autor);
    }

    @GetMapping("/autor/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getautor(id);
    }

    @GetMapping("/autor")
    public List<AutorModel> readAll() {
        return service.getautores();
    }

    @GetMapping("/autor/sobrenome/{sobrenome}")
    public List<AutorModel> readBysobrenome(@PathVariable("sobrenome") String sobrenome) {
        return service.getautorsBysobrenome(sobrenome);
    }

    @PutMapping("/autor")
    public ResponseEntity<?> update(@RequestBody AutorModel autor) {
        return service.updateautor(autor);
    }

    @DeleteMapping("/autor/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deletautor(id);
    }

}
