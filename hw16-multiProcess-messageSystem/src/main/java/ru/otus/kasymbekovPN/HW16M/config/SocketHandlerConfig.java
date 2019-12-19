package ru.otus.kasymbekovPN.HW16M.config;

import common.CLArgsParser;
import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
import message.MessageType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.service.MsClientService;
import ru.otus.kasymbekovPN.HW16M.socket.inputHandler.CommonUserRequestSIH;
import ru.otus.kasymbekovPN.HW16M.socket.inputHandler.CommonUserResponseSIH;
import ru.otus.kasymbekovPN.HW16M.socket.inputHandler.IAmRequestSIH;
import ru.otus.kasymbekovPN.HW16M.socket.inputHandler.WrongTypeSIH;
import ru.otus.kasymbekovPN.HW16M.socket.sendingHandler.MSSocketSendingHandler;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private static final String MS_HOST_ARG_NAME = "ms.host";
    private static final String MS_PORT_ARG_NAME = "ms.port";

    private final MessageSystem messageSystem;
    private final MsClientService msClientService;

    @Bean
    public SocketHandler socketHandler(ApplicationArguments args) throws Exception {

        CLArgsParser clArgsParser = new CLArgsParser(args);
        String msHost = clArgsParser.extractArgAsString(MS_HOST_ARG_NAME);
        int msPort = clArgsParser.extractArgAsInt(MS_PORT_ARG_NAME);

        if (!clArgsParser.argsIsValid()){
            throw new Exception(clArgsParser.getStatus());
        }

        SocketHandlerImpl socketHandler = new SocketHandlerImpl(msPort, new JsonCheckerImpl(), new MSSocketSendingHandler(msHost, msPort));

        socketHandler.addHandler(MessageType.I_AM_REQUEST.getValue(), new IAmRequestSIH(socketHandler, messageSystem, msClientService));

        socketHandler.addHandler(MessageType.AUTH_USER_REQUEST.getValue(), new CommonUserRequestSIH(msClientService, socketHandler));
        socketHandler.addHandler(MessageType.ADD_USER_REQUEST.getValue(), new CommonUserRequestSIH(msClientService, socketHandler));
        socketHandler.addHandler(MessageType.DEL_USER_REQUEST.getValue(), new CommonUserRequestSIH(msClientService, socketHandler));

        socketHandler.addHandler(MessageType.AUTH_USER_RESPONSE.getValue(), new CommonUserResponseSIH(msClientService));
        socketHandler.addHandler(MessageType.DEL_USER_RESPONSE.getValue(), new CommonUserResponseSIH(msClientService));
        socketHandler.addHandler(MessageType.ADD_USER_RESPONSE.getValue(), new CommonUserResponseSIH(msClientService));

        socketHandler.addHandler(MessageType.WRONG_TYPE.getValue(), new WrongTypeSIH());

        msClientService.setSocketHandler(socketHandler);

        return socketHandler;
    }
}
