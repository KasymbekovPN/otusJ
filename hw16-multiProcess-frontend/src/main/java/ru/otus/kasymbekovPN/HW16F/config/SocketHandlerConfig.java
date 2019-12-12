package ru.otus.kasymbekovPN.HW16F.config;

import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
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

    @Bean
    public SocketHandler socketHandler(){
        SocketHandlerImpl socketHandler = new SocketHandlerImpl(new JsonCheckerImpl(), new FESocketSendingHandler());
        socketHandler.addHandler(MessageType.AUTH_USER_RESPONSE.getValue(), new AuthUserResponseSIH(frontendMessageTransmitter));
        socketHandler.addHandler(MessageType.ADD_USER_RESPONSE.getValue(), new AddUserResponseSIH(frontendMessageTransmitter));
        socketHandler.addHandler(MessageType.DEL_USER_RESPONSE.getValue(), new DelUserResponseSIH(frontendMessageTransmitter));
        socketHandler.addHandler(MessageType.WRONG_TYPE.getValue(), new WrongResponseSIH());

        return socketHandler;
    }
}
