package ru.otus.kasymbekovPN.HW16M.socketInputHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import sockets.SocketInputHandler;

public class AuthUserReqSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserReqSIHandler.class);

    private final MessageSystem messageSystem;

    public AuthUserReqSIHandler(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        //<
        logger.info("AuthUserReqSIHandler : " + jsonObject);
    }
}
