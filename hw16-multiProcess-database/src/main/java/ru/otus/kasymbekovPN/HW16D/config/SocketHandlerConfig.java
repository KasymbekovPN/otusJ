package ru.otus.kasymbekovPN.HW16D.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16D.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW16D.socketInputHandler.AuthUserRequestSIH;
import ru.otus.kasymbekovPN.HW16D.socketInputHandler.WrongTypeSIH;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketHandlerImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SocketHandlerConfig.class);

    private final DBServiceOnlineUser dbService;

    @Bean
    public SocketHandler socketHandler(){
        logger.info("created socketHandler");

        SocketHandler socketHandler = SocketHandlerImpl.newInstance("localhost", 8101);
        socketHandler.addHandler(ReqRespType.WRONG_TYPE.getValue(), new WrongTypeSIH());
        socketHandler.addHandler(ReqRespType.AUTH_USER_REQUEST.getValue(), new AuthUserRequestSIH(dbService, socketHandler));

        return socketHandler;
    }
}
