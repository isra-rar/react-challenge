package com.livraria.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDTO implements Serializable {

    private Long id;

    private ClienteDTO cliente;

    private List<LivroDTO> livros = new ArrayList<>();

    private Double valorTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-br", timezone = "America/Recife")
    private LocalDateTime diaReserva;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-br", timezone = "America/Recife")
    private LocalDateTime diaRetirada;

    private Boolean retirado;

    private Boolean cancelado;

    private Long idAluguel;

    public ReservaDTO() {
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDiaReserva() {
        return diaReserva;
    }

    public void setDiaReserva(LocalDateTime diaReserva) {
        this.diaReserva = diaReserva;
    }

    public LocalDateTime getDiaRetirada() {
        return diaRetirada;
    }

    public void setDiaRetirada(LocalDateTime diaRetirada) {
        this.diaRetirada = diaRetirada;
    }

    public Boolean getRetirado() {
        return retirado;
    }

    public void setRetirado(Boolean retirado) {
        this.retirado = retirado;
    }

    public Long getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(Long idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }
}
