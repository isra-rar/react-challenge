package com.livraria.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SexoEnum {

    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino");

    private final int codigo;
    private final String descricao;

    SexoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SexoEnum toEnum(int codigo) {
        return
                Arrays.stream(SexoEnum.values()).filter(cod -> cod.getCodigo() == codigo).collect(Collectors.toList()).get(0);
    }
}
