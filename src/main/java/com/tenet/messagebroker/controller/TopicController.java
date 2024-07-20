package com.tenet.messagebroker.controller;

import com.tenet.messagebroker.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/api/v1/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getAll(){
        return ResponseEntity.ok(topicService.getAll());
    }

    @PostMapping
    public ResponseEntity<Set<String>> create(String name){
        return ResponseEntity.ok(topicService.create(name));
    }


}
