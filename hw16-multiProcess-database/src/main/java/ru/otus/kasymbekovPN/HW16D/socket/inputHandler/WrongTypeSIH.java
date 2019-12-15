package ru.otus.kasymbekovPN.HW16D.socket.inputHandler;

import com.google.gson.JsonObject;
import message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

/**
 * Обработчик входящего сообщения типа {@link MessageType#WRONG_TYPE} <br><br>
 */
public class WrongTypeSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongTypeSIH.class);

    public WrongTypeSIH() {
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.warn("WrongTypeSIH : {}", jsonObject);
    }
}
