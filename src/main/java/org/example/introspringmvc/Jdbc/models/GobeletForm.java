package org.example.introspringmvc.Jdbc.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.example.introspringmvc.Jdbc.enums.TypeGobelet;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GobeletForm {

    @NotBlank
    @Size(min = 1, max = 20)
    private String nom;

    private String description;

    private String image;

    @NotNull
    private TypeGobelet typeGobelet;
}
