package ProjetoDAC_API.controller;

import ProjetoDAC_API.service.colecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ColecaoController {

    @Autowired
    private ColecaoService service;

    @GetMapping("/colecao/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getcolecao(id);
    }

}
