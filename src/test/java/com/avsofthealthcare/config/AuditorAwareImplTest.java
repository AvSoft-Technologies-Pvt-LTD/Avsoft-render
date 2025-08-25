package com.avsofthealthcare.config;

import com.avsofthealthcare.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

class AuditorAwareImplTest {

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void returnsUserIdWhenPrincipalIsUser() {
        User user = User.builder()
                .id(42L)
                .email("tester@example.com")
                .phone("9999999999")
                .password("secret")
                .build();

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authToken);

        AuditorAwareImpl auditorAware = new AuditorAwareImpl();
        String auditor = auditorAware.getCurrentAuditor().orElse(null);

        assertNotNull(auditor);
        assertEquals("42", auditor);
    }

    @Test
    void returnsEmptyWhenNoAuthentication() {
        SecurityContextHolder.clearContext();
        AuditorAwareImpl auditorAware = new AuditorAwareImpl();
        assertTrue(auditorAware.getCurrentAuditor().isEmpty());
    }
}


