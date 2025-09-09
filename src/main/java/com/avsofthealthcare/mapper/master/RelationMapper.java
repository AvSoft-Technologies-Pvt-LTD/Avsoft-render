package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.RelationRequestDto;
import com.avsofthealthcare.dto.master.RelationResponseDto;
import com.avsofthealthcare.entity.master.Relation;

public class RelationMapper {

    public static Relation toEntity(RelationRequestDto dto) {
        return Relation.builder()
                .relationName(dto.getRelationName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static RelationResponseDto toDto(Relation gender) {
        RelationResponseDto dto = new RelationResponseDto();
        dto.setId(gender.getId());
        dto.setRelationName(gender.getRelationName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}
