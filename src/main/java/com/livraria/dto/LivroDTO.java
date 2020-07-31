package com.livraria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.livraria.enums.CategoriaEnum;

import java.io.Serializable;

public class LivroDTO implements Serializable {

    private Long id;
    private String titulo;
    private CategoriaEnum categoria;

    private Double valor;
    private Integer quantidade;

    private Integer unidsReserva;

    private AutorDTO autor;

    public LivroDTO() {
    }

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

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    public Integer getUnidsReserva() {
        return unidsReserva;
    }

    public void setUnidsReserva(Integer unidsReserva) {
        this.unidsReserva = unidsReserva;
    }
}
