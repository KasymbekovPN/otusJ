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

//        frontendMsClient.addHandler(MessageType.CHECK_USER, new GetIsAdminResponseHandler(frontendService));
        //<
        frontendMsClient.addHandler(MessageType.AUTH_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.ADD_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.DEL_USER, new GetCommonUserResponseHandler(frontendService));
//        frontendMsClient.addHandler(MessageType.ADD_USER, new GetAddUserResponseHandler(frontendService));
//        frontendMsClient.addHandler(MessageType.DEL_USER, new GetDelUserResponseHandler(frontendService));
        //<
//        frontendMsClient.addHandler(MessageType.USER_DATA, new GetIsAdminResponseHandler(frontendService));
//        frontendMsClient.addHandler(MessageType.IS_ADMIN, new GetIsAdminResponseHandler(frontendService));
//        frontendMsClient.addHandler(MessageType.WRONG_AUTH_DATA, new GetIsAdminResponseHandler(frontendService));


        messageSystem.addClient(frontendMsClient);
    }

    //< rename
    @MessageMapping("/authorization")
    public void handleMessage(OnlineUser user){
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

//    private void handleCheckUserResponse(List<OnlineUser> data){
//        logger.info("got data : {}", data);
//
//        //< !!! почистить data (для админа убрать ид, для юзера отставить только логин) перед
//
//        if (data.size() > 0){
//            boolean admin = false;
//            for (OnlineUser datum : data) {
//                if (datum.isAdmin()){
//                    admin = true;
//                    break;
//                }
//            }
//
//            if (admin)
//                sendAdminAuthResponse(data);
//            else
//                sendUserAuthResponse(data.get(0).getLogin());
//
//        } else {
//            sendWrongAuthResponse();
//        }
//
//    //<
//        //        sendMessage(data);
//    }

    //< !!! добавить свои "/topic/response????" для редактирования поля управления и строки статуса
//    @SendTo("/topic/response")
//    public void sendMessage(List<OnlineUser> data){
//        System.out.println("SendMessage");
//        this.simpMessagingTemplate.convertAndSend(
//                "/topic/response",
//                data
//                /*new Message_("Hello")*/
//        );
//    }

//    private void sendAdminAuthResponse(List<OnlineUser> users){
//        //<
//        System.out.println("sendAdminAuthResponse");
//        //<
//        this.simpMessagingTemplate.convertAndSend(
//                "/topic/adminAuthResponse",
//                users
//        );
//    }
//
//    private void sendUserAuthResponse(String login){
//        //<
//        System.out.println("sendUserAuthResponse");
//        //<
//        simpMessagingTemplate.convertAndSend(
//                "/topic/userAuthResponse",
//                login
//        );
//    }
//
//    private void sendWrongAuthResponse(){
//        //<
//        System.out.println("sendWrongAuthResponse");
//        //<
//        simpMessagingTemplate.convertAndSend(
//                "/topic/wrongAuthResponse",
//                "Wrong Login and/or Password"
//        );
//    }

    private void handleAuthUserResponse(OnlineUserPackage data){
        //<
        System.out.println("----------------- : " + data);
        //<
        simpMessagingTemplate.convertAndSend(
                "/topic/authResponse",
                data
        );
    }

    private void handleAddUserResponse(OnlineUserPackage data){
        //<
        System.out.println("----------------- : " + data);
        //<
        simpMessagingTemplate.convertAndSend(
                "/topic/addUserResponse",
                data
        );
    }

    private void handleDelUserResponse(OnlineUserPackage data){
        //<
        System.out.println("----------------- : " + data);
        //<
        simpMessagingTemplate.convertAndSend(
                "/topic/delUserResponse",
                data
        );
    }
}
