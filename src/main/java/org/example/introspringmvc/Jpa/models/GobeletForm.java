package org.example.introspringmvc.Jpa.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

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
    private Long typeGobelet;
}
