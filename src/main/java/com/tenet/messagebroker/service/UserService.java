package com.tenet.messagebroker.service;

import com.tenet.messagebroker.data.UserLoginRequest;
import com.tenet.messagebroker.model.Topic;
import com.tenet.messagebroker.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final TopicService topicService;

    private Map<String, User> users = new HashMap<>(){{
        put("admin", new User("admin", "1234"));
    }};

    public UserService(final TopicService topicService) {
        this.topicService = topicService;
    }

    public Set<String> getAll() {
        return new TreeSet<>(users.keySet());
    }

    public boolean authValidate(String login, String password){
        return Optional.ofNullable(users.get(login)).map(user -> passwordIsValid(password, user)).orElse(false);
    }

    public String auth(UserLoginRequest request ) {
        Optional<User> user = Optional.ofNullable(users.get(request.getLogin()));
        if (user.isPresent()) {
            if (passwordIsValid(request.getPassword(), user.get())){
//                userSessionContext.setUserLogin(user.get().getLogin());
                return "Login success!";
            }else {
                return "Wrong password!";
            }
        }
        users.put(request.getLogin(), new User(request.getLogin(), request.getPassword()));
        return "User added and logged in!";
    }

//    public String useTopic(String name){
//        Optional<Topic> activeTopic = Optional.ofNullable(topicService.get(name));
//        if (activeTopic.isPresent()) {
//            userSessionContext.setActiveTopicName(activeTopic.get().getName());
//            return "Active topic is " + name;
//        }
//        return "Topic: " + name + " does not found!";
//    }
    private boolean passwordIsValid(String passwordFromRequest, User user) {
        return Objects.equals(user.getPassword(), passwordFromRequest);
    }
}
