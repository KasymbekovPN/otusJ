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

public class AddUserRespSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AddUserRespSIHandler.class);

    private GuiMessageTransmitter guiMessageTransmitter;

    public AddUserRespSIHandler(GuiMessageTransmitter guiMessageTransmitter) {
        this.guiMessageTransmitter = guiMessageTransmitter;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AddUserRespSIHandler : {}", jsonObject);

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

        guiMessageTransmitter.handleAddUserResponse(onlineUserPackage);
        //<
//        AddUserRespSIHandler :
//        {"type":"addUserResponse",
//        "to":{"host":"localhost","port":8081,"entity":"frontend"},
//        "from":{"host":"localhost","port":8101,"entity":"database"},
//        "data":{
//        "login":"user1",
//        "password":"user1pass",
//        "status":"User 'user1' was create.",
//        "users":[{"id":1,"login":"admin","password":"qwerty","admin":true},{"id":2,"login":"user1","password":"user1pass","admin":false}]}}
    }
}
