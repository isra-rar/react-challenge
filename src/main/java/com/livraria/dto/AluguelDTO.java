package com.livraria.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AluguelDTO implements Serializable {

    private Long id;

    private ClienteDTO cliente;

    private List<LivroDTO> livros = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-br", timezone = "America/Recife")
    private String diaAlugado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-br", timezone = "America/Recife")
    private String diaDevolucao;

    private Double valorAluguel;

    public AluguelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<LivroDTO> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroDTO> livros) {
        this.livros = livros;
    }

    public String getDiaAlugado() {
        return diaAlugado;
    }

    public void setDiaAlugado(String diaAlugado) {
        this.diaAlugado = diaAlugado;
    }

    public String getDiaDevolucao() {
        return diaDevolucao;
    }

    public void setDiaDevolucao(String diaDevolucao) {
        this.diaDevolucao = diaDevolucao;
    }

    public Double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }
}
