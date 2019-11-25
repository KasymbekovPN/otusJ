package ru.otus.kasymbekovPN.HW16.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16.sockets.ReqRespType;
import ru.otus.kasymbekovPN.HW16.sockets.SocketHandler;
import ru.otus.kasymbekovPN.HW16.sockets.SocketHandlerImpl;
import ru.otus.kasymbekovPN.HW16.sockets.inputHandler.messageSystem.AuthUserReqSIHandler;
import ru.otus.kasymbekovPN.HW16.sockets.inputHandler.messageSystem.WrongReqSIHandler;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

//    private final GuiMessageTransmitter guiMessageTransmitter;
//
//    @Qualifier("GuiMessageControllerSocketHandler")
//    @Bean
//    public SocketHandler socketHandler(){
//
//        //<
//        System.out.println("----------GuiMessageControllerSocketHandler-------------");
//
//        //< !!! replace port value
//        SocketHandler socketHandler = SocketHandlerImpl.newInstance("localhost", 8081);
//        //< ???
//        socketHandler.addHandler(ReqRespType.AUTH_USER_RESPONSE.getValue(), new AuthUserRespSIHandler(guiMessageTransmitter));
//        socketHandler.addHandler(ReqRespType.ADD_USER_RESPONSE.getValue(), new AddUserRespSIHandler(guiMessageTransmitter));
//        socketHandler.addHandler(ReqRespType.DEL_USER_RESPONSE.getValue(), new DelUserRespSIHandler(guiMessageTransmitter));
//        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongRespSIHandler());
//        //<
////        //< !!! replace port value
////        SocketHandler socketHandler = SocketHandlerImpl.newInstance(8081);
////        //< ???
////        socketHandler.addHandler("authUserResponse", new AuthUserRespSIHandler(guiMessageTransmitter));
////        socketHandler.addHandler("addUserResponse", new AddUserRespSIHandler(guiMessageTransmitter));
////        socketHandler.addHandler("delUserResponse", new DelUserRespSIHandler(guiMessageTransmitter));
////        socketHandler.addHandler("wrongType", new WrongRespSIHandler());
//
//        return socketHandler;
//    }

    @Qualifier("MessageSystemSocketHandler")
    @Bean
    public SocketHandler messageSystemSocketHandler(){

        System.out.println("----------------------MessageSystemSocketHandler------------------");

        SocketHandler socketHandler = SocketHandlerImpl.newInstance("localhost", 8082);
        socketHandler.addHandler(ReqRespType.AUTH_USER_REQUEST.getValue(), new AuthUserReqSIHandler());
        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongReqSIHandler());

        return socketHandler;
    }
}
