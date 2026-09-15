package org.example.introspringmvc.Jdbc.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TypeGobelet {

    MAMMIFERE(1L),
    REPTILE(2L),
    POISSON(3L),
    OISEAU(4L);

    private final Long id;

    TypeGobelet(Long id) {
        this.id = id;
    }

    public static TypeGobelet fromId(Long id) {
        return Arrays.stream(values())
                .filter(type -> type.id.equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Type inconnu : " + id)
                );
    }
}