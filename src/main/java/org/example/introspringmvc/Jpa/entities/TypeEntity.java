package org.example.introspringmvc.Jpa.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @ToString
@Table(name = "type")
public class TypeEntity {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Getter @Setter
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public TypeEntity(String name) {
        this();
        this.name = name;
    }
}
