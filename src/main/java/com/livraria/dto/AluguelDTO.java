package com.livraria.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AluguelDTO implements Serializable {

    private Long id;

    private ClienteDTO cliente;

    private List<LivroDTO> livros = new ArrayList<>();

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

    public Double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }
}
