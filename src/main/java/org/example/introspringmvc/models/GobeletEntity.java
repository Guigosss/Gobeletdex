package org.example.introspringmvc.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.introspringmvc.enums.TypeAnimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(exclude = "image")
public class GobeletEntity {

    private int id;
    private String nom;
    private String description;
    private String image;
    private TypeAnimal typeAnimal;
}
