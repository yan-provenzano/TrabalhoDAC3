/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.id.ProjetoDAC_API.repository;

import br.uff.id.ProjetoDAC_API.model.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestrecurso;

/**
 *
 * @autor Yan
 */
@RepositoryRestrecurso(colecaorecursoRel = "colecaocurso", path = "cursos")
public interface CursoRepository extends CrudRepository<Curso, Long>{ 
}
