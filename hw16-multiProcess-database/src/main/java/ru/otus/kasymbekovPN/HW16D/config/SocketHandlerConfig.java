package ru.otus.kasymbekovPN.HW16D.config;

import common.CLArgsParser;
import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
import message.MessageType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16D.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW16D.socket.inputHandler.AddUserRequestSIH;
import ru.otus.kasymbekovPN.HW16D.socket.inputHandler.AuthUserRequestSIH;
import ru.otus.kasymbekovPN.HW16D.socket.inputHandler.DelUserRequestSIH;
import ru.otus.kasymbekovPN.HW16D.socket.inputHandler.WrongTypeSIH;
import ru.otus.kasymbekovPN.HW16D.socket.sendingHandler.DBSocketSendingHandler;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private static final String SELF_HOST = "self.host";
    private static final String SELF_PORT = "self.port";
    private static final String MS_HOST = "ms.host";
    private static final String MS_PORT = "ms.port";
    private static final String TARGET_HOST = "target.host";
    private static final String TARGET_PORT = "target.port";

    private final DBServiceOnlineUser dbService;

    @Bean
    public SocketHandler socketHandler(ApplicationArguments args) throws Exception {

        CLArgsParser clArgsParser = new CLArgsParser(args);
        String selfHost = clArgsParser.extractArgAsString(SELF_HOST);
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
                new DBSocketSendingHandler(msHost, selfHost, targetHost, msPort, selfPort, targetPort)
        );
        socketHandler.addHandler(MessageType.WRONG_TYPE.getValue(), new WrongTypeSIH());
        socketHandler.addHandler(MessageType.AUTH_USER_REQUEST.getValue(), new AuthUserRequestSIH(dbService, socketHandler));
        socketHandler.addHandler(MessageType.ADD_USER_REQUEST.getValue(), new AddUserRequestSIH(dbService, socketHandler));
        socketHandler.addHandler(MessageType.DEL_USER_REQUEST.getValue(), new DelUserRequestSIH(dbService, socketHandler));

        return socketHandler;
    }
}
