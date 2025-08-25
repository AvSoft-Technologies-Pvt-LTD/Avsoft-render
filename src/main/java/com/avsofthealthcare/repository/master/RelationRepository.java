package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RelationRepository extends JpaRepository<Relation, Integer> {
    List<Relation> findAllByIsDeletedFalse();

    Optional<Relation> findByIdAndIsDeletedFalse(Integer id);
}
