package com.tenet.messagebroker.service;

import com.tenet.messagebroker.model.Message;
import com.tenet.messagebroker.model.Topic;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicService {

    private Map<String, Topic> topics = new HashMap<>(){{
        put("default", new Topic("default"));
        put("programming", new Topic("programming"));
        put("friends", new Topic("friends"));
    }};

    public Set<String> create(String name) {
        if (Optional.ofNullable(topics.get(name)).isEmpty()) {
            throw new IllegalArgumentException("Topic name already exist");
        }
        topics.put(name, new Topic(name));
        return new TreeSet<>(topics.keySet());
    }

    public Set<String> getAll() {
        if (topics.isEmpty()){
            throw new RuntimeException("Topics is Empty");
        }
        return new TreeSet<>(topics.keySet());

    }

    public Topic get(String name) {
        return topics.get(name);
    }

    public String postMessage(String activeTopicName, Message message) {
        topics.get(activeTopicName).getMessages().add(message);
        return "success!";
    }
}
