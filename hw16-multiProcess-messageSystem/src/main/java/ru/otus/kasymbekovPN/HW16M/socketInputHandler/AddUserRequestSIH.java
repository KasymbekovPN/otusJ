package ru.otus.kasymbekovPN.HW16M.socketInputHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MsClient;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

public class AddUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AddUserRequestSIH.class);

    private final MessageSystem messageSystem;
    private final SocketHandler socketHandler;

    public AddUserRequestSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
        this.messageSystem = messageSystem;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AddUserRequestSIH : {}", jsonObject);

//        JsonObject data = jsonObject.get("data").getAsJsonObject();
//        JsonObject from = jsonObject.get("from").getAsJsonObject();
        //<
        String fromUrl = JsonHelper.extractUrl(jsonObject.get("from").getAsJsonObject());
        //<
//        String fromHost = from.get("host").getAsString();
//        String fromEntity = Entity.check(from.get("entity").getAsString());
//        int fromPort = from.get("port").getAsInt();
//        String fromUrl = fromHost + ":" + String.valueOf(fromPort) + "/" + fromEntity;

        String toUrl = JsonHelper.extractUrl(jsonObject.get("to").getAsJsonObject());
        //<
//        JsonObject to = jsonObject.get("to").getAsJsonObject();
//        String toHost = to.get("host").getAsString();
//        String toEntity = Entity.check(to.get("entity").getAsString());
//        int toPort = to.get("port").getAsInt();
//        String toUrl = toHost + ":" + String.valueOf(toPort) + "/" + toEntity;

        //<
        logger.info("fromUrl : {}", fromUrl);
        logger.info("toUrl : {}", toUrl);
        //<

        String status = "";
        MsClient fromClient = messageSystem.getClient(fromUrl);
        MsClient toClient = messageSystem.getClient(toUrl);

        if (fromClient == null){
            status += "Client '" + fromUrl + "' doesn't exist; ";
        }
        if (toClient == null){
            status += "Client '" + toUrl + "' doesn't exist; ";
        }

        if (status.equals("")){
            String str = jsonObject.toString();
            Message message = fromClient.produceMessage(toUrl, str, ReqRespType.ADD_USER_REQUEST);
            fromClient.sendMessage(message);
        } else {
            JsonArray users = new JsonArray();
            JsonObject data = jsonObject.get("data").getAsJsonObject();
            data.addProperty("status", status);
            data.add("users", users);

            JsonObject to = jsonObject.get("from").getAsJsonObject();

            JsonObject resp = new JsonObject();
            resp.addProperty("type", ReqRespType.ADD_USER_RESPONSE.getValue());
            resp.add("data", data);
            resp.add("to", to);

//            String targetHost = from.get("host").getAsString();
//            int targetPort = from.get("port").getAsInt();
//            socketHandler.send(resp, targetHost, targetPort, Entity.MESSAGE_SYSTEM.getValue());
            //<
            socketHandler.send(resp);
        }
    }
}
