package com.avsofthealthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing(auditorAwareRef = "auditorAware")
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
//I am writing here a code for doing a test to push it.