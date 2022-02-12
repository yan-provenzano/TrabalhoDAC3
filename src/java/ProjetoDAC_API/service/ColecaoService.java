package ProjetoDAC_API.service;

import ProjetoDAC_API.model.colecaoModel;
import ProjetoDAC_API.model.RecursoModel;
import ProjetoDAC_API.repository.colecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ColecaoService {

    @Autowired
    private ColecaoRepository repository;

    public ResponseEntity<?> getColecao(Long id) {
        colecaoModel existingcolecao = repository
                .findById(id)
                .orElse(null);

        if (existingcolecao == null)
            return new ResponseEntity<>("{\"message\":\"colecao não existe\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(existingcolecao, HttpStatus.OK);
    }

}
