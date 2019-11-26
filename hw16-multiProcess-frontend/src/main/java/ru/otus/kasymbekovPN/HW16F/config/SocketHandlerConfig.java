package ru.otus.kasymbekovPN.HW16F.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16F.messageController.GuiMessageTransmitter;
import ru.otus.kasymbekovPN.HW16F.socketHandler.AddUserRespSIHandler;
import ru.otus.kasymbekovPN.HW16F.socketHandler.AuthUserRespSIHandler;
import ru.otus.kasymbekovPN.HW16F.socketHandler.DelUserRespSIHandler;
import ru.otus.kasymbekovPN.HW16F.socketHandler.WrongRespSIHandler;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private final GuiMessageTransmitter guiMessageTransmitter;

    @Bean
    public SocketHandler socketHandler(){

        //<
        System.out.println("----------GuiMessageControllerSocketHandler-------------");

        //< !!! replace port value
        SocketHandler socketHandler = SocketHandlerImpl.newInstance("localhost", 8081);
        //< ???
        socketHandler.addHandler(ReqRespType.AUTH_USER_RESPONSE.getValue(), new AuthUserRespSIHandler(guiMessageTransmitter));
        socketHandler.addHandler(ReqRespType.ADD_USER_RESPONSE.getValue(), new AddUserRespSIHandler(guiMessageTransmitter));
        socketHandler.addHandler(ReqRespType.DEL_USER_RESPONSE.getValue(), new DelUserRespSIHandler(guiMessageTransmitter));
        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongRespSIHandler());

        return socketHandler;
    }
}
