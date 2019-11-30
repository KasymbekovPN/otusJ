package ru.otus.kasymbekovPN.HW16M.socketHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

public class WrongSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongSIHandler.class);

    @Override
    public void handle(JsonObject jsonObject) {
        //<
        logger.info("WrongSIHandler : {}", jsonObject);
    }
}
