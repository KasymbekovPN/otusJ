package ru.otus.kasymbekovPN.HW16F.config;

import introduce.Registrar;
import introduce.RegistrarHandler;
import introduce.RegistrarImpl;
import introduce.SendIAmToMSHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16F.socketHandler.IAmRespSIHandler;
import sockets.Entity;
import sockets.ReqRespType;
import sockets.SocketHandler;

@Configuration
@RequiredArgsConstructor
public class RegistrarConfig {

    private static final Logger logger = LoggerFactory.getLogger(RegistrarConfig.class);

    private final SocketHandler socketHandler;

    @Bean
    public Registrar registrar(){

        //<
        logger.info("---registrar---");

        //< ??? replace 'frontend'
        RegistrarHandler registrarHandler = new SendIAmToMSHandler(socketHandler, Entity.FRONTEND);
        RegistrarImpl registrar = new RegistrarImpl(registrarHandler);
        socketHandler.addHandler(ReqRespType.I_AM_RESPONSE.getValue(), new IAmRespSIHandler(registrar));

        return registrar;
    }
}
