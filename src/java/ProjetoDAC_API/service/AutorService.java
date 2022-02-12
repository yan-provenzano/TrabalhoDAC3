package ProjetoDAC_API.service;

import ProjetoDAC_API.model.AutorModel;
import ProjetoDAC_API.repository.autorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorModel salvarAutor(AutorModel autor) {
        return repository.save(autor);
    }

    public List<AutorModel> getAutores() {
        return (List<AutorModel>) repository.findAll();
    }

    public ResponseEntity<?> getAutor(Long id) {
        AutorModel existingautor = repository
                .findById(id)
                .orElse(null);

        if (existingautor == null)
            return new ResponseEntity<>("{\"message\":\"autor não existe\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(existingautor, HttpStatus.OK);
    }

    public List<AutorModel> getAutoresBySobrenome(String sobrenome) {
        return repository.findBysobrenome(sobrenome);
    }

    public ResponseEntity<?> updateAutor(AutorModel autor) {
        AutorModel existingautor = repository
                .findById(autor.getId())
                .orElse(null);

        if (existingautor == null)
            return new ResponseEntity<>("{\"message\":\"autor não existe\"}", HttpStatus.NOT_FOUND);

        if (autor.getEmail() != null)
            existingautor.setEmail(autor.getEmail());

        if (autor.getNome() != null)
            existingautor.setNome(autor.getNome());

        if (autor.getSobrenome() != null)
            existingautor.setsobrenome(autor.getSobrenome());

        if (autor.getAfiliacao() != null)
            existingautor.setAfiliation(autor.getAfiliacao());

        if (autor.getOrcid() != null)
            existingautor.setOrcid(autor.getOrcid());

        return new ResponseEntity<>(repository.save(existingautor), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteautor(Long id) {
        AutorModel existingautor = repository
                .findById(id)
                .orElse(null);

        if (existingautor == null)
            return new ResponseEntity<>("{\"message\":\"autor não existe\"}", HttpStatus.NOT_FOUND);

        repository.deleteById(id);
        return new ResponseEntity<>("{\"message\":\"removed autor\"}", HttpStatus.OK);
    }

}
