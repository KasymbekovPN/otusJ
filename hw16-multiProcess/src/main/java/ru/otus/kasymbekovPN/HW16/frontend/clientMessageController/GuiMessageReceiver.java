package ru.otus.kasymbekovPN.HW16.frontend.clientMessageController;

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

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.otus.kasymbekovPN.HW16.common.OnlineUser;
import ru.otus.kasymbekovPN.HW16.sockets.ReqRespType;
import ru.otus.kasymbekovPN.HW16.sockets.SocketHandler;

@Controller
@RequiredArgsConstructor
public class GuiMessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(GuiMessageReceiver.class);

    //<
//    private final SimpMessagingTemplate simpMessagingTemplate;

    @Qualifier("GuiMessageControllerSocketHandler")
    private final SocketHandler socketHandler;

    @MessageMapping("/authUserRequest")
    public void handleAuthUserRequest(OnlineUser user){
        //<
        logger.info("auth user : {}", user);
        //<

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", ReqRespType.AUTH_USER_REQUEST.getValue());
        jsonObject.addProperty("login", user.getLogin());
        jsonObject.addProperty("password", user.getPassword());

        //<
//        logger.info("json : {}", jsonObject);
        //<

//        socketHandler.send(jsonObject, "localhost", 8082);


//        {
//            "type" : "authUserRequest",
//                "to" : {
//            "host" : "...",
//                    "port" : 8080
//        },
//            "from" : {
//            "host" : "...",
//                    "port" : 8081
//        },
//            "login" : "...",
//                "password" : "..."
//        }


        //<
//        logger.info("user data message : {}", user);
//        frontendService.authUser(user, this::handleAuthUserResponse);
    }

    @MessageMapping("/addUserRequest")
    public void handleAddUserRequest(OnlineUser user){
        //<
//        logger.info("handleAddUserMessage, user data : {}", user);
//        frontendService.addUser(user, this::handleAddUserResponse);
    }

    @MessageMapping("/delUserRequest")
    public void handleDelUserRequest(OnlineUser user){
        //<
//        logger.info("handleDelUserMessage, user data : {}", user);
//        frontendService.delUser(user, this::handleDelUserResponse);
    }

//                #### authUserRequest
//```json
//{
//  "type" : "authUserRequest",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "login" : "...",
//  "password" : "..."
//}
//```
//
//        #### addUserRequest
//```json
//{
//  "type" : "authUserRequest",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "login" : "...",
//  "password" : "..."
//}
//```
//
//        #### delUserRequest
//```json
//{
//  "type" : "authUserRequest",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "login" : "..."
//}
//```
//
//        #### authUserResponse - admin, success
//```json
//{
//  "type" : "authUserResponse",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "status" : "admin-success",
//  "users" : [
//    {
//      "id" : 1,
//      "login" : "...",
//      "password" : "...",
//      "admin" : true
//    },
//    {
//      "id" : 2,
//      "login" : "...",
//      "password" : "...",
//      "admin" : false
//    }
//  ]
//}
//```
//    Поле 'users' содержит информацию о пользователях в БД
//
//#### authUserResponse - user, success
//```json
//{
//  "type" : "authUserResponse",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "status" : "user-success",
//  "login" : "..."
//}
//```
//
//        #### authUserResponse - wrong login or/and password
//```json
//{
//  "type" : "authUserResponse",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "status" : "wrong-login-password"
//}
//```
//
//        #### addUserResponse
//```json
//{
//  "type" : "addUserResponse",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "status" : "...",
//  "users" : []
//}
//```
//        ##### status
//* User ??? was added
//* Wrong login/password
//* User ??? already exists
//
//##### users
//* Array of json-objects with users data
//
//#### delUserResponse
//```json
//{
//  "type" : "delUserResponse",
//  "to" : {
//    "host" : "...",
//    "port" : 8080
//  },
//  "from" : {
//    "host" : "...",
//    "port" : 8081
//  },
//  "status" : "...",
//  "users" : []
//}
//```
//        ##### status
//* User ??? was deleted
//* Wrong login, it is empty
//* User ??? doesn't exist
//
//            ##### users
//* Array of json-objects with users data

}
