package ru.otus.kasymbekovPN.HW16.sockets.inputHandler.messageSystem;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16.sockets.inputHandler.SocketInputHandler;

public class WrongReqSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongReqSIHandler.class);

    public WrongReqSIHandler() {
    }

    @Override
    public void handle(JsonObject jsonObject) {
        //<
        logger.info("MS WrongReqSIHandler : {}", jsonObject);
        //<
    }
}
