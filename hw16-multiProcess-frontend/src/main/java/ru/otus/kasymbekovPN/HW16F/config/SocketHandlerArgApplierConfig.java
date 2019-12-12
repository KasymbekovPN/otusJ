package ru.otus.kasymbekovPN.HW16F.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import entity.Entity;
import sockets.SocketHandler;
import sockets.SocketHandlerArgApplier;
import sockets.SocketHandlerArgApplierImpl;

@Configuration
@RequiredArgsConstructor
public class SocketHandlerArgApplierConfig {

    private final SocketHandler socketHandler;

    @Bean
    public SocketHandlerArgApplier socketHandlerArgApplier(){
        return new SocketHandlerArgApplierImpl(Entity.FRONTEND, socketHandler);
    }
}
