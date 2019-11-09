package ru.otus.kasymbekovPN.HW15.messageSystem.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class UserDataMessageController {

    private static final Logger logger = LoggerFactory.getLogger(UserDataMessageController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostConstruct
    public void init(){
        System.out.println("----------------POST-----------------");
    }

    @MessageMapping("/authorization")
    public void handleMessage(UserDataMessage userDataMessage){
        logger.info("user data message : {}", userDataMessage);
    }
}
