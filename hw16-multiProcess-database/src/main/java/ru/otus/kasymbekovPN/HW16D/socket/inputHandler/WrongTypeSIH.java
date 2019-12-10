package ru.otus.kasymbekovPN.HW16D.socket.inputHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

public class WrongTypeSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongTypeSIH.class);

    public WrongTypeSIH() {
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.warn("WrongTypeSIH : {}", jsonObject);
    }
}
