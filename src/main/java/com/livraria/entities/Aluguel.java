package com.livraria.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Aluguel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Livro> livrosList = new ArrayList<>();

    private LocalDateTime diaAlugado;

    private LocalDateTime diaDevolucao;

    private Double valorAluguel;

    public Aluguel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Livro> getLivros() {
        return livrosList;
    }

    public void setLivros(List<Livro> livrosList) {
        this.livrosList = livrosList;
    }

    public LocalDateTime getDiaAlugado() {
        return diaAlugado;
    }

    public void setDiaAlugado(LocalDateTime diaAlugado) {
        this.diaAlugado = diaAlugado;
    }

    public LocalDateTime getDiaDevolucao() {
        return diaDevolucao;
    }

    public void setDiaDevolucao(LocalDateTime diaDevolucao) {
        this.diaDevolucao = diaDevolucao;
    }

    public Double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public void setValorAlugel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluguel)) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(getId(), aluguel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
