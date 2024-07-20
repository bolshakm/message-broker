package com.tenet.messagebroker.controller;


import com.tenet.messagebroker.data.request.MessageRequest;
import com.tenet.messagebroker.service.MessageService;
import com.tenet.messagebroker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/v1/message")
public class MessageController {
    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    private final UserService userService;
    private final MessageService messageService;

    public MessageController(final UserService userService,
                             final MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getMessage(@RequestParam String topic, @RequestParam LocalDateTime lastRead) {
        return ResponseEntity.ok(messageService.get(topic, lastRead));
    }

    @PostMapping
    public ResponseEntity<String> postMessage(@RequestBody MessageRequest request) {
        if (!userService.authValidate(request.getLogin(), request.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        LOG.info("Message from {}, text: {}", request.getLogin(), request.getText());
        return ResponseEntity.ok(messageService.post(request));

    }
}
