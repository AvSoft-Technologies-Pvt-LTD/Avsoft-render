package com.avsofthealthcare.avsofthealthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.avsofthealthcare.avsofthealthcare",
    "com.avsofthealthcare.avsofthealthcare.controller",
    "com.avsofthealthcare.avsofthealthcare.service",
    "com.avsofthealthcare.avsofthealthcare.config"
})
public class AvsofthealthcareApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvsofthealthcareApplication.class, args);
    }
}
