package ru.otus.kasymbekovPN.HW16M.messageSystem.handler.frontend;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.ReqRespHandler;
import sockets.Entity;
import sockets.SocketHandler;

import java.util.Optional;

public class DelUserRespFERRHandler implements ReqRespHandler {

    private static final Logger logger = LoggerFactory.getLogger(DelUserRespFERRHandler.class);

    private final SocketHandler socketHandler;

    public DelUserRespFERRHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public Optional<Message> handle(Message message) {
        logger.info("DelUserRespFERRHandler : {}", message);

        String line = Serializers.deserialize(message.getPayload(), String.class);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(line);

        JsonObject to = jsonObject.get("to").getAsJsonObject();
        String targetHost = to.get("host").getAsString();
        int targetPort = to.get("port").getAsInt();
        socketHandler.send(jsonObject, targetHost, targetPort, Entity.MESSAGE_SYSTEM.getValue());

        return Optional.empty();
    }
}
