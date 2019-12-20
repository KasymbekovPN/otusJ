package ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.creator;

import message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClientImpl;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.CommonMSMessageHandler;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.WrongMSMessageHandler;
import sockets.SocketHandler;

public class CmnMsClientCreator implements MsClientCreator {

    private static final Logger logger = LoggerFactory.getLogger(CmnMsClientCreator.class);

    private final MessageType auth;
    private final MessageType add;
    private final MessageType del;

    public CmnMsClientCreator(MessageType auth, MessageType add, MessageType del) {
        this.auth = auth;
        this.add = add;
        this.del = del;
    }

    @Override
    public MsClient create(String url, SocketHandler socketHandler, MessageSystem messageSystem) {
        logger.info("Client creation : {}", url);

        MsClientImpl msClient = new MsClientImpl(url, messageSystem);

        msClient.addHandler(MessageType.WRONG_TYPE, new WrongMSMessageHandler());
        msClient.addHandler(auth, new CommonMSMessageHandler(socketHandler));
        msClient.addHandler(add, new CommonMSMessageHandler(socketHandler));
        msClient.addHandler(del, new CommonMSMessageHandler(socketHandler));

        return msClient;
    }
}
