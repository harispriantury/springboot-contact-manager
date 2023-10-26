package id.restful.springboot_contact_management.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Objects;

@RestController
public class HealthCheck {
    @GetMapping(
            path = "/health-check",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String healthCheck() {
        System.out.println("server running succes fully");
        return "server running";
    }
}
