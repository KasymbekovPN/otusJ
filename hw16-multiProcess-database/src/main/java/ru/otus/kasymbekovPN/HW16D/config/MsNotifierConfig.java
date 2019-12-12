package ru.otus.kasymbekovPN.HW16D.config;

import introduce.MsNotifier;
import introduce.MsNotifierHandler;
import introduce.MsNotifierImpl;
import introduce.IAmMsNotifierHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16D.socket.inputHandler.IAmResponseSIH;
import message.MessageType;
import sockets.SocketHandler;

@Configuration
@RequiredArgsConstructor
public class MsNotifierConfig {

    private static final Logger logger = LoggerFactory.getLogger(MsNotifierConfig.class);

    private final SocketHandler socketHandler;

    @Bean
    public MsNotifier msNotifier(){
        MsNotifierHandler msNotifierHandler = new IAmMsNotifierHandler(socketHandler);
        MsNotifier msNotifier = new MsNotifierImpl(msNotifierHandler);
        socketHandler.addHandler(MessageType.I_AM_RESPONSE.getValue(), new IAmResponseSIH(msNotifier));

        return msNotifier;
    }
}
