package org.example.introspringmvc.services;

import org.example.introspringmvc.enums.TypeGobelet;
import org.example.introspringmvc.models.GobeletEntity;
import org.example.introspringmvc.models.GobeletForm;

import java.util.List;

public interface GobeletService {

    List<GobeletEntity> getGobelets(TypeGobelet type);
    GobeletEntity getGobelet(Long id);
    boolean addGobelet(GobeletForm gobelet);
    boolean deleteGobelet(Long id);
    boolean updateGobelet(Long id, GobeletForm gobelet);
}
