package ru.otus.kasymbekovPN.HW16F.messageController;

import com.google.gson.JsonObject;
import json.JsonHelper;
import lombok.RequiredArgsConstructor;
import model.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import sockets.ReqRespType;
import sockets.SocketHandler;

//<
//    /**
//     * Контроллер, осуществляющий обработку сообщений из GUI.<br><br>
//     *
//     * {@link UserDataMessageController#handleAuthUserRequest(OnlineUser)} - обработчик авторизационного
//     * запроса от GUI. В систему сообщений {@link MessageSystem} отправляется сообщение типа - {@link MessageType#AUTH_USER}
//     * <br>
//     * <br>
//     * {@link UserDataMessageController#handleAddUserRequest(OnlineUser)} - обработчик запроса на
//     * добавление пользователя от GUI. В систему сообщений {@link MessageSystem} отправляется сообщение
//     * типа - {@link MessageType#ADD_USER}<br><br>
//     *
//     * {@link UserDataMessageController#handleDelUserRequest(OnlineUser)} - обработчик запроса на удаления
//     * пользователя от GUI. В систему сообщений {@link MessageSystem} отправляется сообщение
//     * типа - {@link MessageType#DEL_USER}<br><br>
//     *
//     * {@link UserDataMessageController#handleAuthUserResponse(OnlineUserPackage)} : обработчик ответа,
//     * возвращенного системой сообщений {@link MessageSystem} на запрос типа {@link MessageType#AUTH_USER},
//     * перенаправляет в GUI ответ {@link OnlineUserPackage} на соотв. запрос.<br><br>
//     *
//     * {@link UserDataMessageController#handleAddUserResponse(OnlineUserPackage)} : обработчик ответа,
//     * возвращенного системой сообщений {@link MessageSystem} на запрос типа {@link MessageType#ADD_USER},
//     * перенаправляет в GUI ответ {@link OnlineUserPackage} на соотв. запрос.<br><br>
//     *
//     * {@link UserDataMessageController#handleDelUserResponse(OnlineUserPackage)} : обработчик ответа,
//     * возвращенного системой сообщений {@link MessageSystem} на запрос типа {@link MessageType#DEL_USER},
//     * перенаправляет в GUI ответ {@link OnlineUserPackage} на соотв. запрос.<br><br>
//     *
//     * @see OnlineUserPackage
//     */
@Controller
@RequiredArgsConstructor
public class GuiMessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(GuiMessageReceiver.class);

    //< вынести
    private static final String TO_HOST = "localhost";
    private static int TO_PORT = 8101;

    private final SocketHandler socketHandler;

    @MessageMapping("/authUserRequest")
    public void handleAuthUserRequest(OnlineUser user){
        logger.info("handleAuthUserRequest : {}", user);

        JsonObject data = new JsonObject();
        data.addProperty("login", user.getLogin());
        data.addProperty("password", user.getPassword());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", ReqRespType.AUTH_USER_REQUEST.getValue());
        jsonObject.add("data", data);

        socketHandler.sendF(jsonObject);
        //<
//        JsonObject to = new JsonObject();
//        to.addProperty("host", TO_HOST);
//        to.addProperty("port", TO_PORT);
//        to.addProperty("entity", Entity.DATABASE.getValue());
//
//        JsonObject data = new JsonObject();
//        data.addProperty("login", user.getLogin());
//        data.addProperty("password", user.getPassword());
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("type", ReqRespType.AUTH_USER_REQUEST.getValue());
//        jsonObject.add("data", data);
//
//        jsonObject.add("to", to);
//
//        //< target ???
//        socketHandler.send(jsonObject, "localhost", 8091, Entity.FRONTEND.getValue());
    }

    @MessageMapping("/addUserRequest")
    public void handleAddUserRequest(OnlineUser user){
        logger.info("handleAddUserRequest : {}", user);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", ReqRespType.ADD_USER_REQUEST.getValue());
        jsonObject.add("data", JsonHelper.makeData(user.getLogin(), user.getPassword()));

        socketHandler.sendF(jsonObject);
        //<
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("type", ReqRespType.ADD_USER_REQUEST.getValue());
//        jsonObject.add("to", JsonHelper.makeUrl(TO_HOST, TO_PORT, Entity.DATABASE));
//        jsonObject.add("data", JsonHelper.makeData(user.getLogin(), user.getPassword()));
//
//        socketHandler.send(jsonObject, "localhost", 8091, Entity.FRONTEND.getValue());
    }

    @MessageMapping("/delUserRequest")
    public void handleDelUserRequest(OnlineUser user){
        logger.info("handleDelUserRequest : {}", user);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", ReqRespType.DEL_USER_REQUEST.getValue());
        jsonObject.add("data", JsonHelper.makeData(user.getLogin()));

        socketHandler.sendF(jsonObject);
        //<
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("type", ReqRespType.DEL_USER_REQUEST.getValue());
//        jsonObject.add("to", JsonHelper.makeUrl(TO_HOST, TO_PORT, Entity.DATABASE));
//        jsonObject.add("data", JsonHelper.makeData(user.getLogin()));
//
//        socketHandler.send(jsonObject, "localhost", 8091, Entity.FRONTEND.getValue());
    }
}
