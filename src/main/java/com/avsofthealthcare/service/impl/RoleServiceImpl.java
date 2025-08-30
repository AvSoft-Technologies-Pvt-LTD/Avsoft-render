package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.*;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.repository.RoleRepository;
import com.avsofthealthcare.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        if (roleRepository.existsByName(roleRequestDto.getName())) {
            throw new RuntimeException("Role already exists: " + roleRequestDto.getName());
        }

        Role role = new Role();
        role.setName(roleRequestDto.getName());
        // createdBy and createdAt are handled by JPA auditing

        Role savedRole = roleRepository.save(role);
        return mapToResponse(savedRole);
    }

    @Override
    public RoleResponseDto updateRole(Long id, RoleUpdateDto roleUpdateDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        if (roleUpdateDto.getName() != null) {
            role.setName(roleUpdateDto.getName());
        }
        // Set updatedBy from authenticated user id; updatedAt set explicitly
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof com.avsofthealthcare.entity.User principal) {
            role.setUpdatedBy(String.valueOf(principal.getId()));
        }
        role.setUpdatedAt(LocalDateTime.now());

        Role updatedRole = roleRepository.save(role);
        return mapToResponse(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
    }

    @Override
    public RoleResponseDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return mapToResponse(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RoleResponseDto mapToResponse(Role role) {
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setCreatedBy(role.getCreatedBy());
        dto.setCreatedAt(role.getCreatedAt());
        dto.setUpdatedBy(role.getUpdatedBy());
        dto.setUpdatedAt(role.getUpdatedAt());
        return dto;
    }
}
