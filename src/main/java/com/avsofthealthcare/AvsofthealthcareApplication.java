package com.avsofthealthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(basePackages = "com.avsofthealthcare.repository")
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.avsofthealthcare",
    "com.avsofthealthcare.controller",
    "com.avsofthealthcare.service",
    "com.avsofthealthcare.config"
})
public class AvsofthealthcareApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvsofthealthcareApplication.class, args);
    }
}

