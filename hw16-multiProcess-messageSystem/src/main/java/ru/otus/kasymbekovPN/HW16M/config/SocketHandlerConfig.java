package ru.otus.kasymbekovPN.HW16M.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.socketHandler.AuthUserReqSIHandler;
import ru.otus.kasymbekovPN.HW16M.socketHandler.IAmReqSIHandler;
import ru.otus.kasymbekovPN.HW16M.socketHandler.WrongSIHandler;
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
        //<
        logger.info("--------------MessageSystemSocketHandler---------------------");
        //<
        SocketHandlerImpl socketHandler = SocketHandlerImpl.newInstance("localhost", 8091);

        socketHandler.addHandler(ReqRespType.I_AM_REQUEST.getValue(), new IAmReqSIHandler(messageSystem, socketHandler));
        socketHandler.addHandler(ReqRespType.AUTH_USER_REQUEST.getValue(), new AuthUserReqSIHandler(messageSystem));
        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongSIHandler());

        return socketHandler;
    }

    //<
//        @Bean
//        public SocketHandler socketHandler(){
//
//            //<
//            System.out.println("----------GuiMessageControllerSocketHandler-------------");
//
//            //< !!! replace port value
//            SocketHandler socketHandler = SocketHandlerImpl.newInstance("localhost", 8081);
//            //< ???
//            socketHandler.addHandler(ReqRespType.AUTH_USER_RESPONSE.getValue(), new AuthUserRespSIHandler(guiMessageTransmitter));
//            socketHandler.addHandler(ReqRespType.ADD_USER_RESPONSE.getValue(), new AddUserRespSIHandler(guiMessageTransmitter));
//            socketHandler.addHandler(ReqRespType.DEL_USER_RESPONSE.getValue(), new DelUserRespSIHandler(guiMessageTransmitter));
//            socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongRespSIHandler());
//
//            return socketHandler;
//        }
//    }

}
