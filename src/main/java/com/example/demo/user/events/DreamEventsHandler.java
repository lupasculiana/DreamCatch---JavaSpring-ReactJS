package com.example.demo.user.events;

import com.example.demo.dream.Dream;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DreamEventsHandler {
    private UserService userService;

    public DreamEventsHandler(UserService userService) {
        this.userService = userService;
    }

    @EventHandler
    public void on(DreamCreatedEvent event){
        Dream dream =
                new Dream();
        BeanUtils.copyProperties(event,dream);
        userService.updateUser(dream.getUserId(),dream);
    }

}
