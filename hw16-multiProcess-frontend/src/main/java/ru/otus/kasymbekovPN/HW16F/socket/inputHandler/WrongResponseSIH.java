package ru.otus.kasymbekovPN.HW16F.socket.inputHandler;

import com.google.gson.JsonObject;
import message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

/**
 * Обработчик входящего сообщения типа {@link MessageType#WRONG_TYPE} <br><br>
 */
public class WrongResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongResponseSIH.class);

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("WrongResponseSIH : {}", jsonObject);
    }
}
