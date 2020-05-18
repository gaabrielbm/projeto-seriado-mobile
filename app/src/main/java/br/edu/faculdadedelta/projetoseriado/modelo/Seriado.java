package br.edu.faculdadedelta.projetoseriado.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seriado implements Serializable {

    private Long id;
    private String genero;
    private String status;
    private String nome;
    private String comentario;
    private int nota;
    private Date lancamento;

    public Seriado() {
    }

    public Seriado(Long id, String genero, String status, String nome, String comentario, int nota, Date lancamento) {
        this.id = id;
        this.genero = genero;
        this.status = status;
        this.nome = nome;
        this.comentario = comentario;
        this.nota = nota;
        this.lancamento = lancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seriado)) return false;
        Seriado seriado = (Seriado) o;
        return Objects.equals(getId(), seriado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
