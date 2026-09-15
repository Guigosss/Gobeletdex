package org.example.introspringmvc.Jpa.initializers;

import lombok.RequiredArgsConstructor;
import org.example.introspringmvc.Jpa.entities.GobeletEntity;
import org.example.introspringmvc.Jpa.entities.TypeEntity;
import org.example.introspringmvc.Jpa.repositories.GobeletRepository;
import org.example.introspringmvc.Jpa.repositories.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Seed implements CommandLineRunner {

    private final GobeletRepository gobeletRepository;
    private final TypeRepository typeRepository;

    public void run(String... args) throws Exception {

        if (gobeletRepository.count() == 0) {

            TypeEntity type1 = new TypeEntity("Mammifère");
            TypeEntity type2 = new TypeEntity("Oiseau");
            TypeEntity type3 = new TypeEntity("Poisson");
            TypeEntity type4 = new TypeEntity("Reptile");

            type1 = typeRepository.save(type1);
            type2 = typeRepository.save(type2);
            type3 = typeRepository.save(type3);
            type4 = typeRepository.save(type4);

            List<GobeletEntity> gobelets = List.of(
                    new GobeletEntity(
                            "Gecko",
                            "Petit lézard agile, souvent coloré, vivant dans les régions chaudes.",
                            "gecko.jpg",
                            type4
                    ),
                    new GobeletEntity(
                            "Tortue",
                            "Reptile lent, protégé par une carapace dure.",
                            "tortue.jpg",
                            type4
                    ),
                    new GobeletEntity(
                            "Raie",
                            "Poisson plat aux larges nageoires, vivant dans les fonds marins.",
                            "raie.jpg",
                            type3
                    ),
                    new GobeletEntity(
                            "Aigle",
                            "Grand rapace puissant, aux ailes larges et au regard perçant.",
                            "aigle.jpg",
                            type2
                    ),
                    new GobeletEntity(
                            "Serpent",
                            "Reptile rampant, sans pattes, au corps allongé et couvert d’écailles.",
                            "serpent.jpg",
                            type4
                    ),
                    new GobeletEntity(
                            "Suricate",
                            "Petit mammifère curieux, agile, dressé sur ses pattes pour surveiller.",
                            "suricate.jpg",
                            type1
                    ),
                    new GobeletEntity(
                            "Michael Jackson",
                            "Michael Jackson méconnaissable, visage déformé, sourire inquiétant, ambiance cauchemardesque.",
                            "mj.jpg",
                            type4
                    )
            );

            gobeletRepository.saveAll(gobelets);
        }
    }

}
