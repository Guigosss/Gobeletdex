package org.example.introspringmvc.Jdbc.daos;

import org.example.introspringmvc.Jdbc.enums.TypeGobelet;
import org.example.introspringmvc.Jdbc.exceptions.DaoException;
import org.example.introspringmvc.Jdbc.models.Gobelet;
import org.example.introspringmvc.Jdbc.models.GobeletForm;
import org.example.introspringmvc.Jdbc.models.TypeGobeletView;
import org.example.introspringmvc.Jdbc.utils.ConnectionUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GobeletDAO {

    public void create(GobeletForm gobelet){
        String sql = "INSERT INTO gobelet (nom, description, image, type) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, gobelet.getNom());
            ps.setString(2, gobelet.getDescription());
            ps.setString(3, gobelet.getImage());
            ps.setString(4, gobelet.getTypeGobeletView().getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Erreur lors de la création du gobelet");
        }
    }

    public void delete(Long id){
        String sql = "UPDATE gobelet " +
                "SET deleted = TRUE " +
                "WHERE gobelet_id = ? ";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e){
            throw new DaoException("Erreur lors de la suppression du gobelet");
        }
    }

    public void update(Long id, GobeletForm gobelet){
        String sql = "UPDATE gobelet " +
                "SET nom = ?, " +
                    "description = ?, " +
                    "image = ?, " +
                    "type = ? " +
                "WHERE gobelet_id = ?";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, gobelet.getNom());
            ps.setString(2, gobelet.getDescription());
            ps.setString(3, gobelet.getImage());
            ps.setString(4, gobelet.getTypeGobeletView().getName());
            ps.setLong(5, id);
            ps.executeUpdate();

        } catch (SQLException e){
            throw new DaoException("Erreur lors de la modification du gobelet");
        }
    }

    public List<Gobelet> getGobelets(TypeGobeletView type) {
        String sql = "SELECT * FROM gobelet " +
                "WHERE deleted IS FALSE " +
                "AND (? IS NULL OR type = ?)";

        List<Gobelet> gobelets = new ArrayList<>();

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String typeValue = type != null ? type.getName() : null;

            ps.setString(1, typeValue);
            ps.setString(2, typeValue);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Gobelet gobelet = new Gobelet();
                    gobelet.setGobelet_id(rs.getLong("gobelet_id"));
                    gobelet.setNom(rs.getString("nom"));
                    gobelet.setDescription(rs.getString("description"));
                    gobelet.setImage(rs.getString("image"));
                    gobelet.setTypeGobeletView(
                            new TypeGobeletView(
                                    rs.getLong("type"),
                                    TypeGobelet.fromId(rs.getLong("type"))
                            )
                    );
                    gobelets.add(gobelet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return gobelets;
    }

    public Gobelet getGobelet(Long id){
        String sql = "SELECT * FROM gobelet WHERE gobelet_id = ? AND deleted IS FALSE";
        Gobelet gobelet = new Gobelet();

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                gobelet.setNom(rs.getString("nom"));
                gobelet.setDescription(rs.getString("description"));
                gobelet.setImage(rs.getString("image"));
                gobelet.setTypeGobeletView(
                        new TypeGobeletView(
                                rs.getLong("type"),
                                TypeGobelet.fromId(rs.getLong("type"))
                        )
                );
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return gobelet;
    }

}