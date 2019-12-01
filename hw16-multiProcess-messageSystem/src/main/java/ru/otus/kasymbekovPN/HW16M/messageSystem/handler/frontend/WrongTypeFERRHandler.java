package ru.otus.kasymbekovPN.HW16M.messageSystem.handler.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.ReqRespHandler;

import java.util.Optional;

public class WrongTypeFERRHandler implements ReqRespHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongTypeFERRHandler.class);

    public WrongTypeFERRHandler() {
    }

    @Override
    public Optional<Message> handle(Message message) {
        logger.info("WrongTypeFERRHandler : {}", message);
        return Optional.empty();
    }
}
