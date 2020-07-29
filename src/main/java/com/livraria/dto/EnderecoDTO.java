package com.livraria.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EnderecoDTO implements Serializable {

    private Long id;

    @NotNull
    @NotEmpty(message = "LOGRADOURO não fornecido")
    private String logradouro;

    @NotNull
    @NotEmpty(message = "NUMERO não fornecido")
    private String numero;

    @NotNull
    @NotEmpty(message = "BAIRRO não fornecido")
    private String bairro;

    @NotNull
    @NotEmpty(message = "CEP não fornecido")
    private String cep;


    public EnderecoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
