package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.service.MsClientService;
import message.MessageType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

public class AuthUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserRequestSIH.class);

    private final MsClientService msClientService;
    //<
//    private final MessageSystem messageSystem;
    private final SocketHandler socketHandler;

    public AuthUserRequestSIH(MsClientService msClientService, SocketHandler socketHandler) {
        this.msClientService = msClientService;
        this.socketHandler = socketHandler;
    }

    //<
//    public AuthUserRequestSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
//        this.messageSystem = messageSystem;
//        this.socketHandler = socketHandler;
//    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserRequestSIH : " + jsonObject);

        String fromUrl = JsonHelper.extractUrl(jsonObject.get("from").getAsJsonObject());
        String toUrl = JsonHelper.extractUrl(jsonObject.get("to").getAsJsonObject());

        String status = "";
        MsClient fromClient = msClientService.get(fromUrl);
        MsClient toClient = msClientService.get(toUrl);

        if (fromClient == null){
            status += "Client '" + fromUrl + "' doesn't exist; ";
        }
        if (toClient == null){
            status += "Client '" + toUrl + "' doesn't exist; ";
        }

        if (status.equals("")){
            String str = jsonObject.toString();
            Message message = fromClient.produceMessage(toUrl, str, MessageType.AUTH_USER_REQUEST);
            fromClient.sendMessage(message);
        } else {
            JsonArray users = new JsonArray();
            JsonObject data = jsonObject.get("data").getAsJsonObject();
            data.addProperty("status", status);
            data.add("users", users);

            JsonObject from = jsonObject.get("from").getAsJsonObject();

            JsonObject resp = new JsonObject();
            resp.addProperty("type", MessageType.AUTH_USER_RESPONSE.getValue());
            resp.add("data", data);
            resp.add("to", from);

            //< change
//            resp.add("from", from);

            socketHandler.send(resp);
        }
    }
}
