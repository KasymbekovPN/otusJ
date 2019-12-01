package ru.otus.kasymbekovPN.HW16M.messageSystem.handler.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.ReqRespHandler;
import sockets.SocketHandler;

import java.util.Optional;

public class AuthUserReqDBRRHandler implements ReqRespHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserReqDBRRHandler.class);

    private final SocketHandler socketHandler;

    public AuthUserReqDBRRHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public Optional<Message> handle(Message message) {
        logger.info("AuthUserReqDBRRHandler : {}", message);

        return Optional.empty();
    }
}
