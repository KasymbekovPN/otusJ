package ru.otus.kasymbekovPN.HW16D.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16D.socketInputHandler.WrongTypeSIH;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SocketHandlerConfig.class);

    @Bean
    public SocketHandler socketHandler(){
        logger.info("created socketHandler");

        SocketHandler socketHandler = SocketHandlerImpl.newInstance("localhost", 8101);
        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongTypeSIH());

        return socketHandler;
    }
}
