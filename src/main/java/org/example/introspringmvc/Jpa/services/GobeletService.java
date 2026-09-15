package org.example.introspringmvc.Jpa.services;

import lombok.RequiredArgsConstructor;
import org.example.introspringmvc.Jpa.entities.GobeletEntity;
import org.example.introspringmvc.Jpa.entities.TypeEntity;
import org.example.introspringmvc.Jpa.models.GobeletForm;
import org.example.introspringmvc.Jpa.repositories.GobeletRepository;
import org.example.introspringmvc.Jpa.repositories.TypeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class GobeletService {

    private final GobeletRepository gobeletRepository;
    private final TypeRepository typeRepository;

    public List<GobeletEntity> getGobelets(Long typeId) { return gobeletRepository.findAllNotDeletedByType(typeId); }

    public List<TypeEntity> getTypes() { return typeRepository.findAll(); }

    public GobeletEntity getGobelet(Long gobeletId) { return gobeletRepository.findById(gobeletId).orElseThrow(); }

    public TypeEntity getType(Long typeId) { return typeRepository.findById(typeId).orElseThrow(() -> new RuntimeException("Type introuvable")); }

    public GobeletEntity addGobelet(GobeletForm form) {

        GobeletEntity gobelet = new GobeletEntity();

        gobelet.setNom(form.getNom());
        gobelet.setDescription(form.getDescription());
        gobelet.setImage(form.getImage());
        gobelet.setType(getType(form.getTypeGobelet()));

        return gobeletRepository.save(gobelet);
    }

    public void deleteGobelet(Long gobeletId) { gobeletRepository.deleteById(gobeletId); }

    public GobeletEntity updateGobelet(Long gobeletId, GobeletForm form) {

        GobeletEntity gobelet = gobeletRepository.findById(gobeletId).orElseThrow(() -> new RuntimeException("Gobelet introuvable"));

        gobelet.setNom(form.getNom());
        gobelet.setDescription(form.getDescription());
        gobelet.setImage(form.getImage());
        gobelet.setType(getType(form.getTypeGobelet()));

        return gobeletRepository.save(gobelet);
    }
}
