package org.example.introspringmvc.Jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @ToString
@Table(name = "gobelet")
public class GobeletEntity {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gobeletId;

    @Getter @Setter
    @Column(name = "nom", unique = true, nullable = false, length = 50,
        columnDefinition = "VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(nom) >= 2 AND LENGTH(nom) <= 50)")
    private String nom;

    @Getter @Setter
    private String description;

    @Getter @Setter
    @Column(length = 500)
    private String image;

    @Getter @Setter
    @Column(name = "type_id", nullable = false, insertable = false, updatable = false)
    private Long typeId;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted;

    public GobeletEntity(String nom, String description, String image, TypeEntity type) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.type = type;
    }
}
