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
 * Обработчик входящего сообщения типа {@link MessageType#DEL_USER_REQUEST} <br><br>
 *
 * {@link #handle(JsonObject)} - проверяет, переданные логин, в случае успешной проверки удаляет пользователя; отправляет
 * сообщение содержащее данные пользователей.
 */
public class DelUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(DelUserRequestSIH.class);

    private final DBServiceOnlineUser dbService;
    private final SocketHandler socketHandler;

    public DelUserRequestSIH(DBServiceOnlineUser dbService, SocketHandler socketHandler) {
        this.dbService = dbService;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("DelUserRequestSIH : {}", jsonObject);

        JsonObject data = jsonObject.get("data").getAsJsonObject();
        String login = data.get("login").getAsString().trim();
        String status = "";

        if (!login.equals("")){
            List<OnlineUser> onlineUsers = dbService.loadRecord(login);
            if (onlineUsers.size() != 0){
                dbService.deleteRecord(login);
                status = "User '" + login + "' was delete.";
            } else {
                status = "User '" + login + "' doesn't exist.";
            }
        } else {
            status = "Login is empty.";
        }
        logger.info("DelUserRequestSIH : {}", status);

        JsonArray jsonUsers = (JsonArray) new JsonParser().parse(new Gson().toJson(dbService.loadAll()));
        JsonObject responseJsonData = new JsonObject();
        responseJsonData.addProperty("type", MessageType.DEL_USER_RESPONSE.getValue());
        responseJsonData.add("data", JsonHelper.makeData(login, status, jsonUsers));

        socketHandler.send(responseJsonData);
    }
}
