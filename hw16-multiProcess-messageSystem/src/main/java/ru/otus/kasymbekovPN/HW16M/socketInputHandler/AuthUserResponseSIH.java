package ru.otus.kasymbekovPN.HW16M.socketInputHandler;

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

public class AuthUserResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserResponseSIH.class);

    private final MessageSystem messageSystem;
    private final SocketHandler socketHandler;

    public AuthUserResponseSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
        this.messageSystem = messageSystem;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserResponseSIH : {}", jsonObject);

        JsonObject data = jsonObject.get("data").getAsJsonObject();

        JsonObject from = jsonObject.get("from").getAsJsonObject();
        String fromHost = from.get("host").getAsString();
        String fromEntity = Entity.check(Entity.check(from.get("entity").getAsString()));
        int fromPort = from.get("port").getAsInt();
        String fromUrl = fromHost + ":" + String.valueOf(fromPort) + "/" + fromEntity;

        JsonObject to = jsonObject.get("to").getAsJsonObject();
        String toHost = to.get("host").getAsString();
        String toEntity = Entity.check(to.get("entity").getAsString());
        int toPort = to.get("port").getAsInt();
        String toUrl = toHost + ":" + String.valueOf(toPort) + "/" + toEntity;

        //<
        logger.info("AuthUserResponseSIH fromUrl : {}", fromUrl);
        logger.info("AuthUserResponseSIH toUrl : {}", toUrl);
        //<

        MsClient fromClient = messageSystem.getClient(fromUrl);
        MsClient toClient = messageSystem.getClient(toUrl);

        if (fromClient != null && toClient != null) {
            String str = jsonObject.toString();
            Message message = fromClient.produceMessage(toUrl, str, ReqRespType.AUTH_USER_RESPONSE);
            fromClient.sendMessage(message);
        }
        else {
            logger.error("AuthUserResponseSIH : client not found.");
        }
    }
}
