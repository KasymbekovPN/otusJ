package ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.creator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import sockets.SocketHandler;

public class WrongMsClientCreator implements MsClientCreator {

    private static final Logger logger = LoggerFactory.getLogger(WrongMsClientCreator.class);

    @Override
    public MsClient create(String url, SocketHandler socketHandler, MessageSystem messageSystem) {
        logger.warn("The attempt client creation with wrong entity : {}", url);
        return null;
    }
}
