/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.id.ProjetoDAC_API.repository;

import br.uff.id.ProjetoDAC_API.model.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestrecurso;

/**
 *
 * @autor Yan
 */
@RepositoryRestrecurso(colecaorecursoRel = "autor", path = "autores")
public interface AutorRepository extends CrudRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.sobrenome LIKE CONCAT('%',:sobrenome,'%')")
    List<Autor> findBySobrenome(@Param("sobrenome") String sobrenome);

}
