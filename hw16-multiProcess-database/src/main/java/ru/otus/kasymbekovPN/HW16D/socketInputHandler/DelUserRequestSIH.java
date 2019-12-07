package ru.otus.kasymbekovPN.HW16D.socketInputHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import json.JsonHelper;
import model.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16D.db.api.service.DBServiceOnlineUser;
import sockets.Entity;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

import java.util.List;

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

        JsonArray jsonUsers = new JsonArray();
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

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        List<OnlineUser> allUsers = dbService.loadAll();
        for (OnlineUser user : allUsers) {
            jsonUsers.add(
                    parser.parse(gson.toJson(user))
            );
        }

        JsonObject respTo = jsonObject.get("from").getAsJsonObject();
        JsonObject respFrom = jsonObject.get("to").getAsJsonObject();
        JsonObject respData = JsonHelper.makeData(login, status, jsonUsers);

        JsonObject respJson = new JsonObject();
        respJson.addProperty("type", ReqRespType.DEL_USER_RESPONSE.getValue());
        respJson.add("to", respTo);
        respJson.add("from", respFrom);
        respJson.add("data", respData);

        //< !!! как получать target...
        socketHandler.send(respJson, "localhost", 8091, Entity.UNKNOWN.getValue());
    }
}
