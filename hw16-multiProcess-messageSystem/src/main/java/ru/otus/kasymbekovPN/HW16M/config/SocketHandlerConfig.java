package ru.otus.kasymbekovPN.HW16M.config;

import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.socket.inputHandler.*;
import ru.otus.kasymbekovPN.HW16M.socket.sendingHandler.MSSocketSendingHandler;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SocketHandlerConfig.class);

    private final MessageSystem messageSystem;

    @Bean
    public SocketHandler socketHandler(){

        SocketHandlerImpl socketHandler = new SocketHandlerImpl(new JsonCheckerImpl(), new MSSocketSendingHandler());
        socketHandler.addHandler(ReqRespType.I_AM_REQUEST.getValue(), new IAmRequestSIH(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.AUTH_USER_REQUEST.getValue(), new AuthUserRequestSIH(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.AUTH_USER_RESPONSE.getValue(), new AuthUserResponseSIH(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.ADD_USER_REQUEST.getValue(), new AddUserRequestSIH(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.ADD_USER_RESPONSE.getValue(), new AddUserResponseSIH(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.DEL_USER_REQUEST.getValue(), new DelUserRequestSIH(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.DEL_USER_RESPONSE.getValue(), new DelUserResponseSIH(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongTypeSIH());

        return socketHandler;
    }
}
