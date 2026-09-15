package org.example.introspringmvc.Jpa.repositories;

import org.example.introspringmvc.Jpa.entities.GobeletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//- Contacte la DB
@Repository
public interface GobeletRepository extends JpaRepository<GobeletEntity, Long> {

    @Query("""
        SELECT g
        FROM GobeletEntity g
        WHERE g.deleted = false
        AND (:typeId IS NULL OR g.type.typeId = :typeId)
    """)
    List<GobeletEntity> findAllNotDeletedByType(@Param("typeId") Long typeId);
}
