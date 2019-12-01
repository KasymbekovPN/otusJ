package ru.otus.kasymbekovPN.HW16M.messageSystem.handler.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.ReqRespHandler;
import sockets.SocketHandler;

import java.util.Optional;

public class AuthUserRespFERRHandler implements ReqRespHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserRespFERRHandler.class);

    private final SocketHandler socketHandler;

    public AuthUserRespFERRHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public Optional<Message> handle(Message message) {
        logger.info("AuthUserRespFERRHandler : {}", message);
        return Optional.empty();
    }
}
