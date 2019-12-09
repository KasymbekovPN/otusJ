package ru.otus.kasymbekovPN.HW16D.socketInputHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16D.db.api.service.DBServiceOnlineUser;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

import java.util.List;

public class AuthUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserRequestSIH.class);

    private final DBServiceOnlineUser dbService;
    private final SocketHandler socketHandler;

    public AuthUserRequestSIH(DBServiceOnlineUser dbService, SocketHandler socketHandler) {
        this.dbService = dbService;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserRequestSIH : {}", jsonObject);

        JsonArray jsonUsers = new JsonArray();
        JsonObject data = jsonObject.get("data").getAsJsonObject();
        String login = data.get("login").getAsString().trim();
        String password = data.get("password") .getAsString().trim();
        String status = "";

        if (!login.equals("") && !password.equals("")){
            List<OnlineUser> onlineUsers = dbService.loadRecord(login);
            if (onlineUsers.size() > 0){
                OnlineUser onlineUser = onlineUsers.get(0);
                if (onlineUser.isAdmin()){
                    List<OnlineUser> allUsers = dbService.loadAll();
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    for (OnlineUser user : allUsers) {
                        jsonUsers.add(
                                parser.parse(
                                        gson.toJson(user)
                                )
                        );
                    }
                    status = "admin";
                } else {
                    JsonObject jsonUser = new JsonObject();
                    jsonUser.addProperty("login", onlineUser.getLogin());
                    jsonUsers.add(jsonUser);
                    status = "user";
                }
            } else {
                status = "Wrong login or/and password.";
            }
        } else {
            status = "Login or/and password is empty.";
        }
        logger.info("AuthUserRequestSIH : {}", status);

//        JsonObject respTo = jsonObject.get("from").getAsJsonObject();
//        JsonObject respFrom = jsonObject.get("to").getAsJsonObject();
        //<
        JsonObject respData = new JsonObject();
        respData.addProperty("login", login);
        respData.addProperty("password", password);
        respData.addProperty("status", status);
        respData.add("users", jsonUsers);

        JsonObject respJson = new JsonObject();
        respJson.addProperty("type", ReqRespType.AUTH_USER_RESPONSE.getValue());
        //<
//        respJson.add("to", respTo);
//        respJson.add("from", respFrom);
        //<
        respJson.add("data", respData);

        //< !!! как получать target...
//        socketHandler.send(respJson, "localhost", 8091, Entity.UNKNOWN.getValue());
        //<
        socketHandler.send(respJson);
    }
}
