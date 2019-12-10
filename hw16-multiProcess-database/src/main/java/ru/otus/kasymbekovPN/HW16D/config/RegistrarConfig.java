package ru.otus.kasymbekovPN.HW16D.config;

import introduce.Registrar;
import introduce.RegistrarHandler;
import introduce.RegistrarImpl;
import introduce.SendIAmToMSHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16D.socket.inputHandler.IAmResponseSIH;
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

        RegistrarHandler registrarHandler = new SendIAmToMSHandler(socketHandler, Entity.DATABASE);
        Registrar registrar = new RegistrarImpl(registrarHandler);
        socketHandler.addHandler(ReqRespType.I_AM_RESPONSE.getValue(), new IAmResponseSIH(registrar));

        return registrar;
    }
}
