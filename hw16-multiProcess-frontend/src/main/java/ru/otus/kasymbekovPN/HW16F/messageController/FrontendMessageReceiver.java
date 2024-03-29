package ru.otus.kasymbekovPN.HW16F.messageController;

import com.google.gson.JsonObject;
import json.JsonHelper;
import lombok.RequiredArgsConstructor;
import model.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import message.MessageType;
import sockets.SocketHandler;

/**
 * Контроллер, осуществляющий обработку сообщений из GUI.<br><br>
 *
 * {@link FrontendMessageReceiver#handleAuthUserRequest(OnlineUser)} - обработчик авторизационного
 * запроса от GUI. В систему сообщений отправляется сообщение типа - {@link MessageType#AUTH_USER_REQUEST}
 * <br>
 * <br>
 * {@link FrontendMessageReceiver#handleAddUserRequest(OnlineUser)} - обработчик запроса на
 * добавление пользователя от GUI. В систему сообщений отправляется сообщение типа - {@link MessageType#ADD_USER_REQUEST}<br><br>
 *
 * {@link FrontendMessageReceiver#handleDelUserRequest(OnlineUser)} - обработчик запроса на удаления
 * пользователя от GUI. В систему сообщений отправляется сообщение типа - {@link MessageType#DEL_USER_REQUEST}<br><br>
 */
@Controller
@RequiredArgsConstructor
public class FrontendMessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FrontendMessageReceiver.class);

    private final SocketHandler socketHandler;

    @MessageMapping("/authUserRequest")
    public void handleAuthUserRequest(OnlineUser user){
        logger.info("handleAuthUserRequest : {}", user);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", MessageType.AUTH_USER_REQUEST.getValue());
        jsonObject.add("data", JsonHelper.makeData(user.getLogin(), user.getPassword()));

        socketHandler.send(jsonObject);
    }

    @MessageMapping("/addUserRequest")
    public void handleAddUserRequest(OnlineUser user){
        logger.info("handleAddUserRequest : {}", user);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", MessageType.ADD_USER_REQUEST.getValue());
        jsonObject.add("data", JsonHelper.makeData(user.getLogin(), user.getPassword()));

        socketHandler.send(jsonObject);
    }

    @MessageMapping("/delUserRequest")
    public void handleDelUserRequest(OnlineUser user){
        logger.info("handleDelUserRequest : {}", user);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", MessageType.DEL_USER_REQUEST.getValue());
        jsonObject.add("data", JsonHelper.makeData(user.getLogin()));

        socketHandler.send(jsonObject);
    }
}
