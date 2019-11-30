package ru.otus.kasymbekovPN.HW16M.socketHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import sockets.Entity;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

public class IAmReqSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmReqSIHandler.class);

    private final SocketHandler socketHandler;
    private final MessageSystem messageSystem;

    public IAmReqSIHandler(MessageSystem messageSystem, SocketHandler socketHandler) {
        this.messageSystem = messageSystem;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {

        //<
        logger.info("IAmReqSIHandler : {}", jsonObject);

        String entity = Entity.check(jsonObject.get("entity").getAsString());

        JsonObject from = jsonObject.get("from").getAsJsonObject();
        String fromHost = from.get("host").getAsString();
        int fromPort = from.get("port").getAsInt();

        //<
//        JsonObject to = jsonObject.get("to").getAsJsonObject();
//        String toHost = to.get("host").getAsString();
//        int toPort = to.get("port").getAsInt();

        String url = fromHost + ":" + String.valueOf(fromPort) + "/" + entity;

        //< create MSClient

        JsonObject respJsonObject = new JsonObject();
        respJsonObject.addProperty("type", ReqRespType.I_AM_RESPONSE.getValue());
        respJsonObject.addProperty("url", url);

        socketHandler.send(respJsonObject, fromHost, fromPort);

    }
}
