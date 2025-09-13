package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.RelationRequestDto;
import com.avsofthealthcare.entity.master.Relation;

import java.util.List;

public interface RelationService {
    Relation create(RelationRequestDto dto);
    List<Relation> findAllByIsDeletedFalse();
    Relation findById(Integer id);
    Relation findByIdAndIsDeletedFalse(Integer id);
    Relation update(Integer id, RelationRequestDto dto);
    void delete(Integer id);
}