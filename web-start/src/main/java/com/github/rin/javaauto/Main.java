package com.github.rin.javaauto;

/**
 * Main class to start the Spring Boot application.
 */
public class Main {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        WebApplication app = new WebApplication();
        app.start(args);
    }

    /**
     * Constructor.
     */
    private Main() { }
}
