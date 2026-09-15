package org.example.introspringmvc.Jdbc.services;

import org.example.introspringmvc.Jdbc.enums.TypeGobelet;
import org.example.introspringmvc.Jdbc.models.Gobelet;
import org.example.introspringmvc.Jdbc.models.GobeletForm;

import java.util.List;

//- Définit un comportement
public interface IGobeletService {

    List<Gobelet> getGobelets(TypeGobelet type);
    Gobelet getGobelet(Long id);
    boolean addGobelet(GobeletForm gobelet);
    boolean deleteGobelet(Long id);
    boolean updateGobelet(Long id, GobeletForm gobelet);
}
