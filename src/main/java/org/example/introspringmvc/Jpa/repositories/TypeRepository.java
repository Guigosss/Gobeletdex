package org.example.introspringmvc.Jpa.repositories;

import org.example.introspringmvc.Jpa.entities.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {}
