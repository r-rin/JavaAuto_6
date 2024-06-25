package com.github.rin.javaauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the web application.
 */
@SpringBootApplication
public class WebApplication {

    /**
     * Starts the Spring Boot application.
     *
     * @param args Command line arguments
     */
    public static void start(final String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    private WebApplication() { }

}
