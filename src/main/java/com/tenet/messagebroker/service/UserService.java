package com.tenet.messagebroker.service;

import com.tenet.messagebroker.data.request.UserLoginRequest;
import com.tenet.messagebroker.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;

@Service
public class UserService {

    private final TopicService topicService;

    private Map<String, User> users = new HashMap<>() {{
        put("admin", new User("admin", "1234"));
    }};

    public UserService(final TopicService topicService) {
        this.topicService = topicService;
    }

    public Set<String> getAll() {
        return new TreeSet<>(users.keySet());
    }

    public boolean authValidate(String login, String password) {
        return Optional.ofNullable(users.get(login)).map(user -> passwordIsValid(password, user)).orElse(false);
    }

    public ResponseEntity<String> auth(UserLoginRequest request) {
        return Optional.ofNullable(users.get(request.getLogin()))
                .map(user -> validatePassword(request, user))
                .orElseGet(() -> {
                    users.put(request.getLogin(), new User(request.getLogin(), request.getPassword()));
                    return ResponseEntity.created(URI.create("")).body("User added and logged in!");
                });
    }

    private ResponseEntity<String> validatePassword(UserLoginRequest request, User user) {
        if (user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok("Logged in successfully!");
        } else {
            return ResponseEntity.status(401).body("Invalid password!");
        }
    }

    private boolean passwordIsValid(String passwordFromRequest, User user) {
        return Objects.equals(user.getPassword(), passwordFromRequest);
    }
}
