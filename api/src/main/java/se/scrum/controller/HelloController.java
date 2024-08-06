package se.scrum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    public HelloController() {
        logger.info("HelloController initialized");
    }

    @GetMapping("/hello")
    public String hello() {
        logger.debug("Received request for /hello");
        return "Hello, World!";
    }
}
