package ru.otus.kasymbekovPN.HW16F.messageController;

import com.google.gson.JsonObject;
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

    private final SocketHandler socketHandler;

    @MessageMapping("/authUserRequest")
    public void handleAuthUserRequest(OnlineUser user){

        //<
        logger.info("auth data : {}", user);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", ReqRespType.AUTH_USER_REQUEST.getValue());
        jsonObject.addProperty("login", user.getLogin());
        jsonObject.addProperty("password", user.getPassword());

        socketHandler.send(jsonObject, "localhost", 8091);
    }

    @MessageMapping("/addUserRequest")
    public void handleAddUserRequest(OnlineUser user){
    }

    @MessageMapping("/delUserRequest")
    public void handleDelUserRequest(OnlineUser user){
    }
}
