package org.example.introspringmvc.services;

import org.example.introspringmvc.daos.GobeletDAO;
import org.example.introspringmvc.enums.TypeGobelet;
import org.example.introspringmvc.exceptions.DaoException;
import org.example.introspringmvc.models.GobeletEntity;
import org.example.introspringmvc.models.GobeletForm;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class GobeletServiceJdbc implements GobeletService {

    private final GobeletDAO gobeletDAO;

    public GobeletServiceJdbc(GobeletDAO gobeletDAO) {
        this.gobeletDAO = gobeletDAO;
    }

    @Override
    public List<GobeletEntity> getGobelets(TypeGobelet type) {
        return gobeletDAO.getGobelets(type);
    }

    @Override
    public GobeletEntity getGobelet(Long id) {
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
