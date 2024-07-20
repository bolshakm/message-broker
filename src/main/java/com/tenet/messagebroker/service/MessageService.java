package com.tenet.messagebroker.service;

import com.tenet.messagebroker.data.MessageRequest;
import com.tenet.messagebroker.model.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final TopicService topicService;

    public MessageService(final TopicService topicService) {
        this.topicService = topicService;
    }

    public List<String> get(String topicName, LocalDateTime lastReadTime) {
        return topicService.get(topicName)
                .getMessages()
                .stream()
                .filter(message -> message.getTime().isAfter(lastReadTime))
                .map(message -> message.getFrom() + ": " + message.getMessage())
                .collect(Collectors.toList());
    }

    public String post(MessageRequest request) {
        Message message = new Message();
        message.setFrom(request.getLogin());
        message.setMessage(request.getText());
        message.setTime(LocalDateTime.now());
        return topicService.postMessage(request.getTopicName(), message);
    }
}
