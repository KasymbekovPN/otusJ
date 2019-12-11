package ru.otus.kasymbekovPN.HW16M.messageSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.CommonMSMessageHandler;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.WrongMSMessageHandler;
import sockets.ReqRespType;
import sockets.SocketHandler;

public class FeMsClientCreator implements MsClientCreator {

    private static final Logger logger = LoggerFactory.getLogger(FeMsClientCreator.class);

    @Override
    public MsClient create(String url, SocketHandler socketHandler, MessageSystem messageSystem) {
        logger.info("Frontend client creation : {}", url);

        MsClientImpl msClient = new MsClientImpl(url, messageSystem);

        msClient.addHandler(ReqRespType.WRONG_TYPE, new WrongMSMessageHandler());
        msClient.addHandler(ReqRespType.AUTH_USER_RESPONSE, new CommonMSMessageHandler(socketHandler));
        msClient.addHandler(ReqRespType.ADD_USER_RESPONSE, new CommonMSMessageHandler(socketHandler));
        msClient.addHandler(ReqRespType.DEL_USER_RESPONSE, new CommonMSMessageHandler(socketHandler));

        return msClient;
    }
}
