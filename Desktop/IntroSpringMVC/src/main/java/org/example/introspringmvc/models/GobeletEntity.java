package org.example.introspringmvc.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.introspringmvc.enums.TypeGobelet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(exclude = "image")
public class GobeletEntity {

    private Long id;
    private String nom;
    private String description;
    private String image;
    private TypeGobelet typeGobelet;
}
