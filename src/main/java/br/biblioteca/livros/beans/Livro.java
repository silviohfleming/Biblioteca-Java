package br.biblioteca.livros.beans;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Livro {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String nome;

    @NotNull
    @Min(10)
    private int quantidadePaginas;

    private String capa;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQuantidadePaginas() {
        return quantidadePaginas;
    }
    public void setQuantidadePaginas(int quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }
    public String getCapa() {
        return capa;
    }
    public void setCapa(String capa) {
        this.capa = capa;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", nome=" + nome + ", quantidadePaginas=" + quantidadePaginas + "]";
    }
}
