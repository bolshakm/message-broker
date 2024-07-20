package com.tenet.messagebroker.controller;

import com.tenet.messagebroker.data.UserLoginRequest;
import com.tenet.messagebroker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private ResponseEntity<Set<String>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(userService.auth(request));
    }

//    @PutMapping
//    public ResponseEntity<String> useTopic(@RequestBody String topicName) {
//        return ResponseEntity.ok(userService.useTopic(topicName));
//    }
}
