package ru.otus.kasymbekovPN.HW15.clientMessageSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.*;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendService;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendServiceImpl;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.handlers.GetUserDataResponseHandler;

import javax.annotation.PostConstruct;

@Controller
public class UserDataMessageController {

    private static final Logger logger = LoggerFactory.getLogger(UserDataMessageController.class);

    //< !!! sync const name between classes
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private FrontendService frontendService;

    @PostConstruct
    public void init(){
        //<
        System.out.println("----------------POST-----------------");
        //<

        MessageSystem messageSystem = MessageSystemImpl.getInstance();
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
        frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
        frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
        messageSystem.addClient(frontendMsClient);
    }

    @MessageMapping("/authorization")
    public void handleMessage(UserDataMessage userDataMessage){
        logger.info("user data message : {}", userDataMessage);

        //<
        frontendService.getUserData(1, data -> logger.info("got data : {}", data));
        //<
    }

    //<
//    @SendTo("/topic/response")
//    public void sendMessage(Message_ message){
//        log.info("sendMessage");
//        this.simpMessagingTemplate.convertAndSend(
//                "/topic/response",
//                new Message_(message.getMessageStr())
//        );
//    }

}
