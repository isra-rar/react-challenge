package com.livraria.dto;

import com.livraria.enums.SexoEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO implements Serializable {

    private Long id;

    @NotNull
    @NotEmpty(message = "NOME não fornecido")
    @Length(min = 6,max = 40, message = "Nome precisa ter no minimo 6 maximo 40")
    private String nome;

    private SexoEnum sexo;

    @NotNull
    @NotEmpty(message = "CPF não fornecido")
    @Length(min = 11,max = 11, message = "CPF precisa ter 11 caracteres")
    private String cpf;

    @NotNull
    @NotEmpty(message = "EMAIL não fornecido")
    @Email(message = "Formato do EMAIL invalido")
    private String email;


    @Valid
    private List<EnderecoDTO> enderecos = new ArrayList<>();


    public ClienteDTO() {
    }

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

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}
