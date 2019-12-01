package ru.otus.kasymbekovPN.HW16M.messageSystem.handler.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.ReqRespHandler;

import java.util.Optional;

public class WrongTypeDBRRHandler implements ReqRespHandler {

    private static final Logger logger = LoggerFactory.getLogger(WrongTypeDBRRHandler.class);

    public WrongTypeDBRRHandler() {
    }

    @Override
    public Optional<Message> handle(Message message) {
        logger.info("WrongTypeDBRRHandler : {}", message);
        return Optional.empty();
    }
}
