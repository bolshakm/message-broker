package com.tenet.messagebroker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/healthcheck")
public class HealthcheckController {

    @GetMapping
    public ResponseEntity<Object> healthcheck() {
        return ResponseEntity.ok().build();
    }
}
