package ru.otus.kasymbekovPN.HW16D.config;

import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
import message.MessageType;
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

    private final DBServiceOnlineUser dbService;

    @Bean
    public SocketHandler socketHandler(){
        SocketHandlerImpl socketHandler = new SocketHandlerImpl(new JsonCheckerImpl(), new DBSocketSendingHandler());
        socketHandler.addHandler(MessageType.WRONG_TYPE.getValue(), new WrongTypeSIH());
        socketHandler.addHandler(MessageType.AUTH_USER_REQUEST.getValue(), new AuthUserRequestSIH(dbService, socketHandler));
        socketHandler.addHandler(MessageType.ADD_USER_REQUEST.getValue(), new AddUserRequestSIH(dbService, socketHandler));
        socketHandler.addHandler(MessageType.DEL_USER_REQUEST.getValue(), new DelUserRequestSIH(dbService, socketHandler));

        return socketHandler;
    }
}
