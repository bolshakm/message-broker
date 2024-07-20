package com.tenet.messagebroker.model;

import java.util.LinkedList;
import java.util.List;

public class Topic {
    private String name;

    private List<Message> messages;

    public Topic(String name) {
        this.name = name;
        this.messages = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
