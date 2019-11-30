package ru.otus.kasymbekovPN.HW16F.socketHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

public class WrongRespSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongRespSIHandler.class);

    @Override
    public void handle(JsonObject jsonObject) {
        //<
        logger.info("WrongRespSIHandler : {}", jsonObject);
    }
}
