package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

/**
 * Обработчик невалидный сообщений.
 */
public class WrongTypeSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongTypeSIH.class);

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("WrongTypeSIH : {}", jsonObject);
    }
}
