package ru.otus.kasymbekovPN.HW16F.config;

import common.CLArgsParser;
import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16F.messageController.FrontendMessageTransmitter;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.AddUserResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.AuthUserResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.DelUserResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.WrongResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.sendingHandler.FESocketSendingHandler;
import message.MessageType;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private final FrontendMessageTransmitter frontendMessageTransmitter;

    private static final String SELF_PORT = "self.port";
    private static final String MS_HOST = "ms.host";
    private static final String MS_PORT = "ms.port";
    private static final String TARGET_HOST = "target.host";
    private static final String TARGET_PORT = "target.port";

    @Bean
    public SocketHandler socketHandler(ApplicationArguments args) throws Exception {

        CLArgsParser clArgsParser = new CLArgsParser(args);
        int selfPort = clArgsParser.extractArgAsInt(SELF_PORT);
        String msHost = clArgsParser.extractArgAsString(MS_HOST);
        int msPort = clArgsParser.extractArgAsInt(MS_PORT);
        String targetHost = clArgsParser.extractArgAsString(TARGET_HOST);
        int targetPort = clArgsParser.extractArgAsInt(TARGET_PORT);

        if (!clArgsParser.argsIsValid()){
            throw new Exception(clArgsParser.getStatus());
        }

        SocketHandlerImpl socketHandler = new SocketHandlerImpl(
                selfPort,
                new JsonCheckerImpl(),
                new FESocketSendingHandler(msHost, targetHost, msPort, selfPort, targetPort)
        );
        socketHandler.addHandler(MessageType.AUTH_USER_RESPONSE.getValue(), new AuthUserResponseSIH(frontendMessageTransmitter));
        socketHandler.addHandler(MessageType.ADD_USER_RESPONSE.getValue(), new AddUserResponseSIH(frontendMessageTransmitter));
        socketHandler.addHandler(MessageType.DEL_USER_RESPONSE.getValue(), new DelUserResponseSIH(frontendMessageTransmitter));
        socketHandler.addHandler(MessageType.WRONG_TYPE.getValue(), new WrongResponseSIH());

        return socketHandler;
    }
}
