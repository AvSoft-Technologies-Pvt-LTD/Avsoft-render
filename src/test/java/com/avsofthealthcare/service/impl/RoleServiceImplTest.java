package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.RoleRequestDto;
import com.avsofthealthcare.dto.RoleResponseDto;
import com.avsofthealthcare.dto.RoleUpdateDto;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.repository.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createRole_shouldNotSetUpdatedFields_andNotManuallySetCreated() {
        RoleRequestDto req = new RoleRequestDto();
        req.setName("MANAGER");

        when(roleRepository.existsByName("MANAGER")).thenReturn(false);

        ArgumentCaptor<Role> roleArg = ArgumentCaptor.forClass(Role.class);
        when(roleRepository.save(roleArg.capture())).thenAnswer(inv -> {
            Role r = roleArg.getValue();
            // mimic DB id assignment
            r.setId(100L);
            return r;
        });

        RoleResponseDto resp = roleService.createRole(req);

        assertNotNull(resp);
        assertEquals("MANAGER", resp.getName());
        // created fields are managed by auditing at persistence time; service should not set them
        Role saved = roleArg.getValue();
        assertNull(saved.getUpdatedAt(), "updatedAt must be null during create");
        assertNull(saved.getUpdatedBy(), "updatedBy must be null during create");
        // created fields should not be set by service explicitly
        assertNull(saved.getCreatedAt(), "createdAt should not be set by service");
        assertNull(saved.getCreatedBy(), "createdBy should not be set by service");
    }

    @Test
    void updateRole_shouldSetUpdatedFieldsFromAuthenticatedUser() {
        Role existing = new Role();
        existing.setId(5L);
        existing.setName("USER");
        existing.setCreatedAt(LocalDateTime.now().minusDays(1));
        existing.setCreatedBy("1");

        when(roleRepository.findById(5L)).thenReturn(Optional.of(existing));

        ArgumentCaptor<Role> roleArg = ArgumentCaptor.forClass(Role.class);
        when(roleRepository.save(roleArg.capture())).thenAnswer(inv -> roleArg.getValue());

        // set authenticated principal
        User user = User.builder().id(77L).email("x@y.com").build();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, null, null)
        );

        RoleUpdateDto upd = new RoleUpdateDto();
        upd.setName("POWER_USER");
        RoleResponseDto resp = roleService.updateRole(5L, upd);

        assertEquals("POWER_USER", resp.getName());
        Role saved = roleArg.getValue();
        assertEquals("77", saved.getUpdatedBy());
        assertNotNull(saved.getUpdatedAt());
        // created fields remain intact
        assertEquals("1", saved.getCreatedBy());
        assertNotNull(saved.getCreatedAt());
    }
}


