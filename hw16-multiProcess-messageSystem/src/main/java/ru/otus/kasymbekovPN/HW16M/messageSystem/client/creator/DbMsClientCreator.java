package ru.otus.kasymbekovPN.HW16M.messageSystem.client.creator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClientImpl;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.CommonMSMessageHandler;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.WrongMSMessageHandler;
import message.MessageType;
import sockets.SocketHandler;

/**
 * Класс, создающий {@link MsClient} для обмена сообщениями с программой-клиентом, работающим с БД
 */
public class DbMsClientCreator implements MsClientCreator {

    private static final Logger logger = LoggerFactory.getLogger(DbMsClientCreator.class);

    @Override
    public MsClient create(String url, SocketHandler socketHandler, MessageSystem messageSystem) {
        logger.info("DB client creation : {}", url);

        MsClientImpl msClient = new MsClientImpl(url, messageSystem);

        msClient.addHandler(MessageType.WRONG_TYPE, new WrongMSMessageHandler());
        msClient.addHandler(MessageType.AUTH_USER_REQUEST, new CommonMSMessageHandler(socketHandler));
        msClient.addHandler(MessageType.ADD_USER_REQUEST, new CommonMSMessageHandler(socketHandler));
        msClient.addHandler(MessageType.DEL_USER_REQUEST, new CommonMSMessageHandler(socketHandler));

        return msClient;
    }
}
