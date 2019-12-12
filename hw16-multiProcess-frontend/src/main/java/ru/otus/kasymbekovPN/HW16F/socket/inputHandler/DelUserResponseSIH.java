package ru.otus.kasymbekovPN.HW16F.socket.inputHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16F.messageController.FrontendMessageTransmitter;
import ru.otus.kasymbekovPN.HW16F.messageController.OnlineUserPackage;
import sockets.SocketInputHandler;

import java.util.ArrayList;
import java.util.List;

public class DelUserResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(DelUserResponseSIH.class);

    private FrontendMessageTransmitter frontendMessageTransmitter;

    public DelUserResponseSIH(FrontendMessageTransmitter frontendMessageTransmitter) {
        this.frontendMessageTransmitter = frontendMessageTransmitter;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("DelUserResponseSIH : {}", jsonObject);

        JsonObject data = jsonObject.get("data").getAsJsonObject();
        String status = data.get("status").getAsString();
        JsonArray jsonUsers = data.get("users").getAsJsonArray();

        List<OnlineUser> users = new ArrayList<>();
        Gson gson = new Gson();
        for (JsonElement element : jsonUsers) {
            users.add(
                    gson.fromJson((JsonObject)element, OnlineUser.class)
            );
        }

        OnlineUserPackage onlineUserPackage = new OnlineUserPackage();
        onlineUserPackage.setStatus(status);
        onlineUserPackage.setUsers(users);

        frontendMessageTransmitter.handleDelUserResponse(onlineUserPackage);
    }
}
