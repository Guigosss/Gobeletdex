package org.example.introspringmvc.Jdbc.services;

import org.example.introspringmvc.Jdbc.enums.TypeGobelet;
import org.example.introspringmvc.Jdbc.models.Gobelet;
import org.example.introspringmvc.Jdbc.models.GobeletForm;
import org.example.introspringmvc.Jdbc.models.TypeGobeletView;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GobeletServiceFakeDb implements IGobeletService {

    private final List<Gobelet> gobelets = new ArrayList<>();

    public GobeletServiceFakeDb() {
        Map<TypeGobelet, TypeGobeletView> typesGobelet = Arrays.stream(TypeGobelet.values())
                .collect(Collectors.toMap(
                        type -> type,
                        type -> new TypeGobeletView(type.getId(), type)
                ));

        gobelets.add(new Gobelet(1L, "Gecko", "Petit lézard agile, souvent coloré, vivant dans les régions chaudes.", "gecko.jpg", typesGobelet.get(TypeGobelet.REPTILE)));
        gobelets.add(new Gobelet(2L, "Tortue", "Reptile lent, protégé par une carapace dure.", "tortue.jpg", typesGobelet.get(TypeGobelet.REPTILE)));
        gobelets.add(new Gobelet(3L, "Raie", "Poisson plat aux larges nageoires, vivant dans les fonds marins.", "raie.jpg", typesGobelet.get(TypeGobelet.POISSON)));
        gobelets.add(new Gobelet(4L, "Aigle", "Grand rapace puissant, aux ailes larges et au regard perçant.", "aigle.jpg", typesGobelet.get(TypeGobelet.OISEAU)));
        gobelets.add(new Gobelet(5L, "Serpent", "Reptile rampant, sans pattes, au corps allongé et couvert d’écailles.", "serpent.jpg", typesGobelet.get(TypeGobelet.REPTILE)));
        gobelets.add(new Gobelet(6L, "Suricate", "Petit mammifère curieux, agile, dressé sur ses pattes pour surveiller.", "suricate.jpg", typesGobelet.get(TypeGobelet.MAMMIFERE)));
        gobelets.add(new Gobelet(7L, "Michael Jackson", "Michael Jackson méconnaissable, visage déformé, sourire inquiétant, ambiance cauchemardesque.", "mj.jpg", typesGobelet.get(TypeGobelet.REPTILE)));
    }

    @Override
    public List<Gobelet> getGobelets(TypeGobeletView type) {
        return gobelets.stream()
                .filter(gobelet -> type == null || gobelet.getTypeGobeletView() == type)
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
                gobelet.getTypeGobeletView()
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
        gobeletEntity.setTypeGobeletView(gobelet.getTypeGobeletView());

        return true;
    }
}
