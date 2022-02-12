/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ProjetoDAC_API;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.URL;

@Entity
public class Recurso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 1024)
    private String titulo;
    
    @Column(length = 4096)
    private String descricao;
    
    @URL(message = "Informe um link válido.")
    private String link;
    
    @Lob
    private byte[] imagem;
    
    @Temporal (TemporalType.DATE)
    private Date dataCriacao;
    
    @Temporal (TemporalType.DATE)
    private Date dataRegistro;
    
    private List<String> palavrasChave;
    
    @OrderBy("nome asc, sobrenome asc")
    @OneToMany
    private List<Autor> autores;
        
    @OneToOne
    private Autor autorCadastrador;
       
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Autor getAutorCadastrador() {
        return autorCadastrador;
    }

    public void setAutorCadastrador(Autor autorCadastrador) {
        this.autorCadastrador = autorCadastrador;
    }


}
