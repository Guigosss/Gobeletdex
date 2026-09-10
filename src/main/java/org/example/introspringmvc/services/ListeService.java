package org.example.introspringmvc.services;

import org.example.introspringmvc.enums.TypeAnimal;
import org.example.introspringmvc.models.GobeletEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListeService {

    private final List<GobeletEntity> gobelets = new ArrayList<>();

    public ListeService() {
        gobelets.add(new GobeletEntity(1, "Gecko", "Petit lézard agile, souvent coloré, vivant dans les régions chaudes.", "gecko.jpg", TypeAnimal.REPTILE));
        gobelets.add(new GobeletEntity(2, "Tortue", "Reptile lent, protégé par une carapace dure.", "tortue.jpg", TypeAnimal.REPTILE));
        gobelets.add(new GobeletEntity(3, "Raie", "Poisson plat aux larges nageoires, vivant dans les fonds marins.", "raie.jpg", TypeAnimal.POISSON));
        gobelets.add(new GobeletEntity(4, "Aigle", "Grand rapace puissant, aux ailes larges et au regard perçant.", "aigle.jpg", TypeAnimal.OISEAU));
        gobelets.add(new GobeletEntity(5, "Serpent", "Reptile rampant, sans pattes, au corps allongé et couvert d’écailles.", "serpent.jpg", TypeAnimal.REPTILE));
        gobelets.add(new GobeletEntity(6, "Suricate", "Petit mammifère curieux, agile, dressé sur ses pattes pour surveiller.", "suricate.jpg", TypeAnimal.MAMMIFERE));
    }

    public List<GobeletEntity> getGobelets(TypeAnimal type) {
        if (type == null){
            return gobelets;
        }

        return gobelets.stream()
                .filter(gobelet -> gobelet.getTypeAnimal() == type)
                .toList();
    }

    public GobeletEntity getGobeletByName(String name){
        return gobelets.stream()
                .filter(gobelet -> gobelet.getNom().equals(name))
                .findFirst()
                .orElse(null);
    }
}
