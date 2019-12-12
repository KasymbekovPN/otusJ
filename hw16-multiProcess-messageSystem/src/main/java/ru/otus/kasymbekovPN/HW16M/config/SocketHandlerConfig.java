package ru.otus.kasymbekovPN.HW16M.config;

import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.service.MsClientService;
import ru.otus.kasymbekovPN.HW16M.socket.inputHandler.*;
import ru.otus.kasymbekovPN.HW16M.socket.sendingHandler.MSSocketSendingHandler;
import message.MessageType;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SocketHandlerConfig.class);

    private final MessageSystem messageSystem;
    private final MsClientService msClientService;

    @Bean
    public SocketHandler socketHandler(){
        SocketHandlerImpl socketHandler = new SocketHandlerImpl(new JsonCheckerImpl(), new MSSocketSendingHandler());
        socketHandler.addHandler(MessageType.I_AM_REQUEST.getValue(), new IAmRequestSIH(socketHandler, messageSystem, msClientService));
        socketHandler.addHandler(MessageType.AUTH_USER_REQUEST.getValue(), new AuthUserRequestSIH(msClientService, socketHandler));
        socketHandler.addHandler(MessageType.AUTH_USER_RESPONSE.getValue(), new AuthUserResponseSIH(msClientService));
        socketHandler.addHandler(MessageType.ADD_USER_REQUEST.getValue(), new AddUserRequestSIH(msClientService, socketHandler));
        socketHandler.addHandler(MessageType.ADD_USER_RESPONSE.getValue(), new AddUserResponseSIH(msClientService));
        socketHandler.addHandler(MessageType.DEL_USER_REQUEST.getValue(), new DelUserRequestSIH(msClientService, socketHandler));
        socketHandler.addHandler(MessageType.DEL_USER_RESPONSE.getValue(), new DelUserResponseSIH(msClientService));
        socketHandler.addHandler(MessageType.WRONG_TYPE.getValue(), new WrongTypeSIH());

        msClientService.setSocketHandler(socketHandler);

        return socketHandler;
    }
}
