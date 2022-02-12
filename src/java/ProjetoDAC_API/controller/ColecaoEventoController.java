package ProjetoDAC_API.controller;

import ProjetoDAC_API.model.colecaoEventoModel;
import ProjetoDAC_API.service.colecaoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class ColecaoEventoController {

    @Autowired
    private ColecaoEventoService service;

    @PostMapping("/colecaoEvento")
    public colecaoEventoModel create(@RequestBody colecaoEventoModel colecaoEvento) {
        return service.salvarcolecaoEvento(colecaoEvento);
    }

    @GetMapping("/colecaoEvento/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getcolecaoEvento(id);
    }

    @GetMapping("/colecaoEvento")
    public List<colecaoEventoModel> readAll() {
        return service.getcolecaoEventos();
    }

    @GetMapping("/colecaoEvento/period")
    public List<colecaoEventoModel> readByPeriodoDeTempo(
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim") String dataFim
    ) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return service.getcolecaoEventosByPeriodoDeTempo(formatter.parse(dataInicio), formatter.parse(dataFim));
    }

    @PutMapping("/colecaoEvento")
    public ResponseEntity<?> update(@RequestBody colecaoEventoModel colecaoEvento) {
        return service.updatecolecaoEvento(colecaoEvento);
    }

    @DeleteMapping("/colecaoEvento/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deletecolecaoEvento(id);
    }

}
