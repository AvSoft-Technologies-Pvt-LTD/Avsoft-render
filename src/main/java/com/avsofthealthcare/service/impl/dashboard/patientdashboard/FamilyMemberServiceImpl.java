package com.avsofthealthcare.service.impl.dashboard.patientdashboard;

import java.util.List;
import com.avsofthealthcare.dto.dashboard.patientdashboard.FamilyMemberRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.FamilyMemberResponseDTO;
import com.avsofthealthcare.entity.dashboard.patientdashboard.FamilyMemberDetails;
import com.avsofthealthcare.entity.dashboard.patientdashboard.FamilyMemberHealthCondition;
import com.avsofthealthcare.entity.master.HealthConditions;
import com.avsofthealthcare.entity.master.Relation;
import com.avsofthealthcare.mapper.dashboard.patientdashboard.FamilyMemberMapper;
import com.avsofthealthcare.repository.dashboard.patientdashboard.FamilyMemberDetailsRepository;
import com.avsofthealthcare.repository.dashboard.patientdashboard.FamilyMemberHealthConditionRepository;
import com.avsofthealthcare.repository.master.HealthConditionsRepository;
import com.avsofthealthcare.repository.master.RelationRepository;
import com.avsofthealthcare.service.dashboard.patientdashboard.FamilyMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyMemberDetailsRepository memberRepo;
    private final FamilyMemberHealthConditionRepository conditionRepo;
    private final RelationRepository relationRepo;
    private final HealthConditionsRepository healthConditionRepo;

    /**
     * Create a new family member with health conditions
     */
    @Transactional
    @Override
    public FamilyMemberResponseDTO create(FamilyMemberRequestDTO dto) {
        Relation relation = relationRepo.findById(dto.getRelationId().intValue())
                .orElseThrow(() -> new RuntimeException("Relation not found"));

        FamilyMemberDetails member = FamilyMemberDetails.builder()
                .patientId(dto.getPatientId())
                .relation(relation)
                .memberName(dto.getMemberName())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        List<FamilyMemberHealthCondition> conditions = dto.getHealthConditionIds().stream()
                .map(id -> {
                    HealthConditions hc = healthConditionRepo.findById(id.intValue())
                            .orElseThrow(() -> new RuntimeException("HealthCondition not found"));
                    return FamilyMemberHealthCondition.builder()
                            .familyMember(member)
                            .healthCondition(hc)
                            .build();
                }).collect(Collectors.toList());

        member.setHealthConditions(conditions);
        FamilyMemberDetails saved = memberRepo.save(member);
        return FamilyMemberMapper.toDTO(saved);
    }

    /**
     * Get all members for a patient ID
     */
    @Override
    public List<FamilyMemberResponseDTO> getByPatientId(String patientId) {
        return memberRepo.findByPatientId(patientId).stream()
                .map(FamilyMemberMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a single member by ID
     */
    @Override
    public FamilyMemberResponseDTO getById(Long id) {
        FamilyMemberDetails member = memberRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FamilyMember not found"));
        return FamilyMemberMapper.toDTO(member);
    }

    /**
     * Update family member and their conditions
     */
    @Transactional
    @Override
    public FamilyMemberResponseDTO update(Long id, FamilyMemberRequestDTO dto) {
        FamilyMemberDetails member = memberRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FamilyMember not found"));

        Relation relation = relationRepo.findById(dto.getRelationId().intValue())
                .orElseThrow(() -> new RuntimeException("Relation not found"));

        member.setPatientId(dto.getPatientId());
        member.setRelation(relation);
        member.setMemberName(dto.getMemberName());
        member.setPhoneNumber(dto.getPhoneNumber());

        // Clear previous conditions
        conditionRepo.deleteByFamilyMemberId(id);

        List<FamilyMemberHealthCondition> updatedConditions = dto.getHealthConditionIds().stream()
                .map(cid -> {
                    HealthConditions hc = healthConditionRepo.findById(cid.intValue())
                            .orElseThrow(() -> new RuntimeException("HealthCondition not found"));
                    return FamilyMemberHealthCondition.builder()
                            .familyMember(member)
                            .healthCondition(hc)
                            .build();
                }).collect(Collectors.toList());

        member.setHealthConditions(updatedConditions);
        FamilyMemberDetails updated = memberRepo.save(member);
        return FamilyMemberMapper.toDTO(updated);
    }

    /**
     * Delete member and their conditions
     */
    @Transactional
    @Override
    public void delete(Long id) {
        conditionRepo.deleteByFamilyMemberId(id);
        memberRepo.deleteById(id);
    }
}
