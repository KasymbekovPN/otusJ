package ru.otus.kasymbekovPN.HW15.clientMessageSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.*;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendService;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendServiceImpl;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.handlers.GetCommonUserResponseHandler;

import javax.annotation.PostConstruct;

@Controller
public class UserDataMessageController {

    private static final Logger logger = LoggerFactory.getLogger(UserDataMessageController.class);

    //< !!! sync const name between classes
//    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
//    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private FrontendService frontendService;

    @PostConstruct
    public void init(){
        MessageSystem messageSystem = MessageSystemImpl.getInstance();
        MsClient frontendMsClient = new MsClientImpl(MsClientName.FRONTEND.getName(), messageSystem);
        frontendService = new FrontendServiceImpl(frontendMsClient, MsClientName.DATABASE.getName());
        //<
//        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
//        frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);

        frontendMsClient.addHandler(MessageType.AUTH_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.ADD_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.DEL_USER, new GetCommonUserResponseHandler(frontendService));

        messageSystem.addClient(frontendMsClient);
    }

    @MessageMapping("/authorization")
    public void handleAuthorization(OnlineUser user){
        logger.info("user data message : {}", user);
        frontendService.authUser(user, this::handleAuthUserResponse);
    }

    @MessageMapping("/addUser")
    public void handleAddUserMessage(OnlineUser user){
        logger.info("handleAddUserMessage, user data : {}", user);
        frontendService.addUser(user, this::handleAddUserResponse);
    }

    @MessageMapping("/delUser")
    public void handleDelUserMessage(OnlineUser user){
        logger.info("handleDelUserMessage, user data : {}", user);
        frontendService.delUser(user, this::handleDelUserResponse);
    }

    private void handleAuthUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/authResponse",
                data
        );
    }

    private void handleAddUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/addUserResponse",
                data
        );
    }

    private void handleDelUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/delUserResponse",
                data
        );
    }
}
