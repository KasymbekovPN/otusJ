package ru.otus.kasymbekovPN.HW16M.socketInputHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MsClient;
import sockets.Entity;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

public class AuthUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserRequestSIH.class);

    private final MessageSystem messageSystem;
    private final SocketHandler socketHandler;

    public AuthUserRequestSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
        this.messageSystem = messageSystem;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserRequestSIH : " + jsonObject);

        JsonObject data = jsonObject.get("data").getAsJsonObject();

        JsonObject from = jsonObject.get("from").getAsJsonObject();
        String fromHost = from.get("host").getAsString();
        String fromEntity = Entity.check(from.get("entity").getAsString());
        int fromPort = from.get("port").getAsInt();
        String fromUrl = fromHost + ":" + String.valueOf(fromPort) + "/" + fromEntity;

        JsonObject to = jsonObject.get("to").getAsJsonObject();
        String toHost = to.get("host").getAsString();
        String toEntity = Entity.check(to.get("entity").getAsString());
        int toPort = to.get("port").getAsInt();
        String toUrl = toHost + ":" + String.valueOf(toPort) + "/" + toEntity;

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
            Message message = fromClient.produceMessage(toUrl, str, ReqRespType.AUTH_USER_REQUEST);
            fromClient.sendMessage(message);
        } else {
            JsonArray users = new JsonArray();
            data.addProperty("status", status);
            data.add("users", users);

            JsonObject resp = new JsonObject();
            resp.addProperty("type", ReqRespType.AUTH_USER_RESPONSE.getValue());
            resp.add("data", data);

            String targetHost = from.get("host").getAsString();
            int targetPort = from.get("port").getAsInt();
            socketHandler.send(resp, targetHost, targetPort, Entity.MESSAGE_SYSTEM.getValue());
        }
    }
}