package com.livraria.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CategoriaEnum {

    ACAO(1, "AÇÃO"),
    TERROR(2, "TERROR"),
    ROMANCE(3, "ROMANCE"),
    DRAMA(4, "DRAMA"),
    FICCAO(5, "FICÇCÃO");

    private final int codigo;
    private final String descricao;

    CategoriaEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CategoriaEnum toEnum(Integer codigo) {
        return
                Arrays.stream(CategoriaEnum.values()).filter(cod -> cod.getCodigo() == codigo).collect(Collectors.toList()).get(0);
    }

}
