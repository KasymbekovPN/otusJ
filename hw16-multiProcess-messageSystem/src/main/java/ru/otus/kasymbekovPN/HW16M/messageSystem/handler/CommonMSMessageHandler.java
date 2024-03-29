package ru.otus.kasymbekovPN.HW16M.messageSystem.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import sockets.SocketHandler;

/**
 * Обработчик сообщений верного типа. <br><br>
 */
public class CommonMSMessageHandler implements MSMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonMSMessageHandler.class);

    private final SocketHandler socketHandler;

    public CommonMSMessageHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(Message message) {
        logger.info("CommonMSMessageHandler type : {} message : {}", message.getType(), message);

        JsonObject jsonObject = (JsonObject) new JsonParser().parse(
                Serializers.deserialize(message.getPayload(), String.class)
        );
        socketHandler.send(jsonObject);
    }
}
