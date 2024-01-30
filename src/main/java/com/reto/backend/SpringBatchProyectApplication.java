package com.reto.backend;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing(modular = true)
@SpringBootApplication
public class SpringBatchProyectApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBatchProyectApplication.class, args);
    }

}
