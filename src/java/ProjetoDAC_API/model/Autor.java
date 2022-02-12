/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ProjetoDAC_API;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

@Entity
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;
    
    @Column (length=64)
    private String nome;
    
    @Column (length=64)
    private String sobrenome;
    
    @Column (length=256)
    private String afiliacao;
    
    @Pattern (regexp = "^(?=.{19}$)[a-zA-Z0-9]+(?:-[a-zA-Z0-9]+){3}$", 
    message="OrcID  no formato XXXX-XXXX-XXXX-XXXX")
    private String orcId;

	@ManyToMany
	private List<Recurso> recursos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    public String getOrcId() {
        return orcId;
    }

    public void setOrcId(String orcId) {
        this.orcId = orcId;
    }
    
	public List<Recurso> getRecurso() {
        return recursos;
    }

    public void setRecurso(List<Recurso> recursos) {
        this.recursos = recursos;
    }

}
