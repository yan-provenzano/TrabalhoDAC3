/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.id.ProjetoDAC_API.repository;

import br.uff.id.ProjetoDAC_API.model.colecaoEventoo;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestrecurso;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @autor Yan
 */
@RepositoryRestrecurso(colecaorecursoRel = "colecaocolecaoEventoo", path = "colecaoEventoos")
public interface colecaoEventooRepository extends CrudRepository<colecaoEventoo, Long>{
    
    @Query("SELECT e FROM colecaoEventoo e WHERE e.inicio >= :inicio AND e.fim <= :fim")
    List<colecaoEventoo> findBycolecaoEventoosEntreDatas(@Param("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Calendar inicio,@Param("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Calendar fim);
    
}
