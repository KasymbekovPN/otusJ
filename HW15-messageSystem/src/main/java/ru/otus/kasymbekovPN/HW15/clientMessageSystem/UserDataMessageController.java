package ru.otus.kasymbekovPN.HW15.clientMessageSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.*;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendService;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendServiceImpl;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.handlers.GetIsAdminResponseHandler;

import javax.annotation.PostConstruct;
import java.util.List;

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

        frontendMsClient.addHandler(MessageType.CHECK_USER, new GetIsAdminResponseHandler(frontendService));
        //<
//        frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
//        frontendMsClient.addHandler(MessageType.IS_ADMIN, new GetIsAdminResponseHandler(frontendService));
//        frontendMsClient.addHandler(MessageType.WRONG_AUTH_DATA, new GetIsAdminResponseHandler(frontendService));


        messageSystem.addClient(frontendMsClient);
    }

    @MessageMapping("/authorization")
    public void handleMessage(OnlineUser user){
        logger.info("user data message : {}", user);

        //<
//        frontendService.getUserData(1, data -> logger.info("got data : {}", data));
        //<
//        frontendService.getUserData(1, this::h);
        //<
        frontendService.checkUser(user, this::handleCheckUserResponse);
    }

    private void handleCheckUserResponse(List<OnlineUser> data){
        logger.info("got data : {}", data);

        //< !!! почистить data (для админа убрать ид, для юзера отставить только логин) перед

        sendMessage(data);
    }

    //< !!! добавить свои "/topic/response????" для редактирования поля управления и строки статуса
    @SendTo("/topic/response")
    public void sendMessage(List<OnlineUser> data){
        System.out.println("SendMessage");
        this.simpMessagingTemplate.convertAndSend(
                "/topic/response",
                data
                /*new Message_("Hello")*/
        );
    }

}
