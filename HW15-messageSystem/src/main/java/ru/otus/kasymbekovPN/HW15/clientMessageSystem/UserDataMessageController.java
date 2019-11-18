package ru.otus.kasymbekovPN.HW15.clientMessageSystem;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageType;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MsClient;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendService;

/**
 * Контроллер, осуществляющий обработку сообщений из GUI.<br><br>
 *
 * При инициализации после (создании инстанса даного класса) {@link UserDataMessageController#init()}
 * происходит инициализация frontend-сервиса {@link UserDataMessageController#frontendService},
 * frontend-клиента системы сообщений {@link MsClient}, получение инстанса системы сообщений
 * {@link MessageSystem} и их связка.
 * Во frontend-клиент добавляются обработчики сообщений (авторизация, добавление, удание
 * пользователя).<br><br>
 *
 * {@link UserDataMessageController#handleAuthUserRequest(OnlineUser)} - обработчик авторизационного
 * запроса от GUI. В систему сообщений {@link MessageSystem} отправляется сообщение типа - {@link MessageType#AUTH_USER}
 * <br>
 * <br>
 * {@link UserDataMessageController#handleAddUserRequest(OnlineUser)} - обработчик запроса на
 * добавление пользователя от GUI. В систему сообщений {@link MessageSystem} отправляется сообщение
 * типа - {@link MessageType#ADD_USER}<br><br>
 *
 * {@link UserDataMessageController#handleDelUserRequest(OnlineUser)} - обработчик запроса на удаления
 * пользователя от GUI. В систему сообщений {@link MessageSystem} отправляется сообщение
 * типа - {@link MessageType#DEL_USER}<br><br>
 *
 * {@link UserDataMessageController#handleAuthUserResponse(OnlineUserPackage)} : обработчик ответа,
 * возвращенного системой сообщений {@link MessageSystem} на запрос типа {@link MessageType#AUTH_USER},
 * перенаправляет в GUI ответ {@link OnlineUserPackage} на соотв. запрос.<br><br>
 *
 * {@link UserDataMessageController#handleAddUserResponse(OnlineUserPackage)} : обработчик ответа,
 * возвращенного системой сообщений {@link MessageSystem} на запрос типа {@link MessageType#ADD_USER},
 * перенаправляет в GUI ответ {@link OnlineUserPackage} на соотв. запрос.<br><br>
 *
 * {@link UserDataMessageController#handleDelUserResponse(OnlineUserPackage)} : обработчик ответа,
 * возвращенного системой сообщений {@link MessageSystem} на запрос типа {@link MessageType#DEL_USER},
 * перенаправляет в GUI ответ {@link OnlineUserPackage} на соотв. запрос.<br><br>
 *
 * @see OnlineUserPackage
 */
@Controller
@RequiredArgsConstructor
public class UserDataMessageController {

    private static final Logger logger = LoggerFactory.getLogger(UserDataMessageController.class);

//    @Autowired
    //<
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Qualifier("userDataMsgCtrlFrontendService")
    private final FrontendService frontendService;

    //<
//    @PostConstruct
//    public void init(){
//        //<
////        MessageSystem messageSystem = MessageSystemImpl.getInstance();
////        MsClient frontendMsClient = new MsClientImpl(MsClientName.FRONTEND.getName(), messageSystem);
////
////        frontendService = new FrontendServiceImpl(frontendMsClient, MsClientName.DATABASE.getName());
////
////        frontendMsClient.addHandler(MessageType.AUTH_USER, new GetCommonUserResponseHandler(frontendService));
////        frontendMsClient.addHandler(MessageType.ADD_USER, new GetCommonUserResponseHandler(frontendService));
////        frontendMsClient.addHandler(MessageType.DEL_USER, new GetCommonUserResponseHandler(frontendService));
////
////        messageSystem.addClient(frontendMsClient);
//    }

    @MessageMapping("/authUserRequest")
    public void handleAuthUserRequest(OnlineUser user){
        logger.info("user data message : {}", user);
        frontendService.authUser(user, this::handleAuthUserResponse);
    }

    @MessageMapping("/addUserRequest")
    public void handleAddUserRequest(OnlineUser user){
        logger.info("handleAddUserMessage, user data : {}", user);
        frontendService.addUser(user, this::handleAddUserResponse);
    }

    @MessageMapping("/delUserRequest")
    public void handleDelUserRequest(OnlineUser user){
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
