package ru.otus.kasymbekovPN.HW16D.socket.inputHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import json.JsonHelper;
import model.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16D.db.api.service.DBServiceOnlineUser;
import message.MessageType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

import java.util.List;

/**
 * Обработчик входящего сообщения типа {@link MessageType#ADD_USER_REQUEST} <br><br>
 *
 * {@link #handle(JsonObject)} - проверяет, переданные логин и пароль, в случае успешной проверки добавляет нового
 * пользователя; отправляет сообщение содержащее данные пользователей.
 */
public class AddUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AddUserRequestSIH.class);

    private final DBServiceOnlineUser dbService;
    private final SocketHandler socketHandler;

    public AddUserRequestSIH(DBServiceOnlineUser dbService, SocketHandler socketHandler) {
        this.dbService = dbService;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AddUserRequestSIH : {}", jsonObject);

        JsonArray jsonUsers = new JsonArray();
        JsonObject data = jsonObject.get("data").getAsJsonObject();
        String login = data.get("login").getAsString().trim();
        String password = data.get("password") .getAsString().trim();
        String status = "";

        if (!login.equals("") && !password.equals("")){
            List<OnlineUser> onlineUsers = dbService.loadRecord(login);
            if (onlineUsers.size() == 0){
                dbService.createRecord(
                        new OnlineUser(0, login, password, false)
                );
                status = "User '" + login + "' was create.";
            } else {
                status = "User '" + login + "' already exists.";
            }
        } else {
            status = "Login or/and password is empty.";
        }
        logger.info("AddUserRequestSIH : {}", status);

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        List<OnlineUser> allUsers = dbService.loadAll();
        for (OnlineUser user : allUsers) {
            jsonUsers.add(
                    parser.parse(gson.toJson(user))
            );
        }

        JsonObject responseJsonObject = new JsonObject();
        responseJsonObject.addProperty("type", MessageType.ADD_USER_RESPONSE.getValue());
        responseJsonObject.add("data", JsonHelper.makeData(login, password, status, jsonUsers));

        socketHandler.send(responseJsonObject);
    }
}
