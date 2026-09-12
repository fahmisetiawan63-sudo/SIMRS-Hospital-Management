package com.otten32.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.otten32"})
public class SimrsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimrsApiApplication.class, args);
    }

}
