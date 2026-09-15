package org.example.introspringmvc.Jdbc.models;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.example.introspringmvc.Jdbc.enums.TypeGobelet;

@Getter @Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class TypeGobeletView {

    private final Long typeId;
    private final String name;

    public TypeGobeletView(Long typeId, TypeGobelet type) {
        this.typeId = typeId;
        this.name = switch (type) {
            case MAMMIFERE -> "Mammifère";
            case REPTILE -> "Reptile";
            case POISSON -> "Poisson";
            case OISEAU -> "Oiseau";
        };
    }
}