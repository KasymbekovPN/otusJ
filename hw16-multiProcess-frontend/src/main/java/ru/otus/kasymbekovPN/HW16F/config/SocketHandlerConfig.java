package ru.otus.kasymbekovPN.HW16F.config;

import json.JsonCheckerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16F.messageController.GuiMessageTransmitter;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.AddUserResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.AuthUserResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.DelUserResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.WrongResponseSIH;
import ru.otus.kasymbekovPN.HW16F.socket.sendingHandler.FESocketSendingHandler;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private final GuiMessageTransmitter guiMessageTransmitter;

    @Bean
    public SocketHandler socketHandler(){
        SocketHandlerImpl socketHandler = new SocketHandlerImpl(new JsonCheckerImpl(), new FESocketSendingHandler());
        socketHandler.addHandler(ReqRespType.AUTH_USER_RESPONSE.getValue(), new AuthUserResponseSIH(guiMessageTransmitter));
        socketHandler.addHandler(ReqRespType.ADD_USER_RESPONSE.getValue(), new AddUserResponseSIH(guiMessageTransmitter));
        socketHandler.addHandler(ReqRespType.DEL_USER_RESPONSE.getValue(), new DelUserResponseSIH(guiMessageTransmitter));
        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongResponseSIH());

        return socketHandler;
    }
}
