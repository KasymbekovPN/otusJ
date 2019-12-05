package ru.otus.kasymbekovPN.HW16F.socketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16F.messageController.GuiMessageTransmitter;
import ru.otus.kasymbekovPN.HW16F.messageController.OnlineUserPackage;
import sockets.SocketInputHandler;

import java.util.ArrayList;
import java.util.List;

public class AuthUserRespSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserRespSIHandler.class);

    private GuiMessageTransmitter guiMessageTransmitter;

    public AuthUserRespSIHandler(GuiMessageTransmitter guiMessageTransmitter) {
        this.guiMessageTransmitter = guiMessageTransmitter;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserRespSIHandler : {}", jsonObject);


        JsonObject data = jsonObject.get("data").getAsJsonObject();
        String status = data.get("status").getAsString();
        JsonArray jsonUsers = data.get("users").getAsJsonArray();

        List<OnlineUser> users = new ArrayList<>();
        Gson gson = new Gson();
        for (JsonElement element : jsonUsers) {
            users.add(
                    gson.fromJson((JsonObject)element, OnlineUser.class)
            );
            //<
//            gson.fromJson((JsonObject)element, OnlineUser.class)
//            JsonObject jsonUser = (JsonObject) element;
//            OnlineUser user = new Gson().fromJson(jsonUser, OnlineUser.class);

            //<
//            logger.info("AuthUserRespSIHandler user : {}", user);

            //<
//            OnlineUser user = new OnlineUser(
//                    jsonUser.get("id").getAsLong(),
//                    jsonUser.get("login").getAsString(),
//                    jsonUser.get("password").getAsString(),
//                    jsonUser.get()
//            );
        }

        OnlineUserPackage onlineUserPackage = new OnlineUserPackage();
        onlineUserPackage.setStatus(status);
        onlineUserPackage.setUsers(users);

        guiMessageTransmitter.handleAuthUserResponse(onlineUserPackage);
    }
}
