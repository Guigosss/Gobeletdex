package org.example.introspringmvc.Jdbc.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.introspringmvc.Jdbc.enums.TypeGobelet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(exclude = "image")
public class Gobelet {

    private Long gobelet_id;
    private String nom;
    private String description;
    private String image;
    private TypeGobelet typeGobelet;
}
