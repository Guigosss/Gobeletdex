package org.example.introspringmvc.Jdbc.services;

import org.example.introspringmvc.Jdbc.daos.GobeletDAO;
import org.example.introspringmvc.Jdbc.enums.TypeGobelet;
import org.example.introspringmvc.Jdbc.exceptions.DaoException;
import org.example.introspringmvc.Jdbc.models.Gobelet;
import org.example.introspringmvc.Jdbc.models.GobeletForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @Primary
public class GobeletServiceJdbc implements IGobeletService {

    private final GobeletDAO gobeletDAO;

    public GobeletServiceJdbc(GobeletDAO gobeletDAO) {
        this.gobeletDAO = gobeletDAO;
    }

    @Override
    public List<Gobelet> getGobelets(TypeGobelet type) {
        return gobeletDAO.getGobelets(type);
    }

    @Override
    public Gobelet getGobelet(Long id) {
        return gobeletDAO.getGobelet(id);
    }

    @Override
    public boolean addGobelet(GobeletForm gobelet) {
        try {
            gobeletDAO.create(gobelet);
            return true;
        } catch(DaoException e) {
            return false;
        }
    }

    @Override
    public boolean deleteGobelet(Long id) {
        try {
            gobeletDAO.delete(id);
            return true;
        } catch(DaoException e) {
            return false;
        }
    }

    @Override
    public boolean updateGobelet(Long id, GobeletForm gobelet) {
        try {
            gobeletDAO.update(id, gobelet);
            return true;
        } catch(DaoException e) {
            return false;
        }
    }
}
