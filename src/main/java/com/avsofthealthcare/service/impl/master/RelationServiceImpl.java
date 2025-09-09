package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.RelationRequestDto;
import com.avsofthealthcare.entity.master.Relation;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.RelationMapper;
import com.avsofthealthcare.repository.master.RelationRepository;
import com.avsofthealthcare.service.master.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationRepository relationRepository;


    @Override
    public Relation create(RelationRequestDto dto) {
        Relation relation = RelationMapper.toEntity(dto);
        return relationRepository.save(relation);
    }

    @Override
    public List<Relation> findAllByIsDeletedFalse() {
        return relationRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Relation findById(Integer id) {
        return relationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relation not found with id: " + id));
    }

    @Override
    public Relation findByIdAndIsDeletedFalse(Integer id) {
        return relationRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relation not found with id: " + id));
    }

    @Override
    public Relation update(Integer id, RelationRequestDto dto) {
        Relation relationExisting = findByIdAndIsDeletedFalse(id);
        relationExisting.setRelationName(dto.getRelationName());
        relationExisting.setDescription(dto.getDescription());
        relationExisting.setActive(dto.getActive());

        // Manually set update fields
        relationExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        relationExisting.setUpdatedAt(LocalDateTime.now());
        return relationRepository.save(relationExisting);
    }

    @Override
    public void delete(Integer id) {
        Relation existing = findById(id);
        existing.setIsDeleted(true);
        relationRepository.save(existing);
    }
}






