package org.example.introspringmvc.Jdbc.services;

import org.example.introspringmvc.Jdbc.enums.TypeGobelet;
import org.example.introspringmvc.Jdbc.models.Gobelet;
import org.example.introspringmvc.Jdbc.models.GobeletForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class GobeletServiceFakeDb implements IGobeletService {

    private final List<Gobelet> gobelets = new ArrayList<>();

    public GobeletServiceFakeDb() {
        gobelets.add(new Gobelet(1L, "Gecko", "Petit lézard agile, souvent coloré, vivant dans les régions chaudes.", "gecko.jpg", TypeGobelet.REPTILE));
        gobelets.add(new Gobelet(2L, "Tortue", "Reptile lent, protégé par une carapace dure.", "tortue.jpg", TypeGobelet.REPTILE));
        gobelets.add(new Gobelet(3L, "Raie", "Poisson plat aux larges nageoires, vivant dans les fonds marins.", "raie.jpg", TypeGobelet.POISSON));
        gobelets.add(new Gobelet(4L, "Aigle", "Grand rapace puissant, aux ailes larges et au regard perçant.", "aigle.jpg", TypeGobelet.OISEAU));
        gobelets.add(new Gobelet(5L, "Serpent", "Reptile rampant, sans pattes, au corps allongé et couvert d’écailles.", "serpent.jpg", TypeGobelet.REPTILE));
        gobelets.add(new Gobelet(6L, "Suricate", "Petit mammifère curieux, agile, dressé sur ses pattes pour surveiller.", "suricate.jpg", TypeGobelet.MAMMIFERE));
        gobelets.add(new Gobelet(7L, "Michael Jackson", "Michael Jackson méconnaissable, visage déformé, sourire inquiétant, ambiance cauchemardesque.", "mj.jpg", TypeGobelet.REPTILE));
    }

    @Override
    public List<Gobelet> getGobelets(TypeGobelet type) {
        return gobelets.stream()
                .filter(gobelet -> type == null || gobelet.getTypeGobelet() == type)
                .toList();
    }

    @Override
    public Gobelet getGobelet(Long id){
        return gobelets.stream()
                .filter(g -> g.getGobelet_id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addGobelet(GobeletForm gobelet){
        Long id = gobelets.stream()
                .mapToLong(Gobelet::getGobelet_id)
                .max()
                .orElse(0) + 1;

        Gobelet newGobelet = new Gobelet(
                id,
                gobelet.getNom(),
                gobelet.getDescription(),
                gobelet.getImage(),
                gobelet.getTypeGobelet()
        );

        return gobelets.add(newGobelet);
    }

    @Override
    public boolean deleteGobelet(Long id){
        return gobelets.removeIf(gobelet -> Objects.equals(gobelet.getGobelet_id(), id));
    }

    @Override
    public boolean updateGobelet(Long id, GobeletForm gobelet){
        Gobelet gobeletEntity = getGobelet(id);

        if (gobeletEntity == null){
            return false;
        }

        gobeletEntity.setNom(gobelet.getNom());
        gobeletEntity.setDescription(gobelet.getDescription());
        gobeletEntity.setImage(gobelet.getImage());
        gobeletEntity.setTypeGobelet(gobelet.getTypeGobelet());

        return true;
    }
}
