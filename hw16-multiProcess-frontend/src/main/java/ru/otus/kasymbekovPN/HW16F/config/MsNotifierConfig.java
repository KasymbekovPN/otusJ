package ru.otus.kasymbekovPN.HW16F.config;

import introduce.Notifier;
import introduce.NotifierRunner;
import introduce.NotifierImpl;
import introduce.IAmNotifierRunner;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.IAmResponseSIH;
import message.MessageType;
import sockets.SocketHandler;

@Configuration
@RequiredArgsConstructor
public class MsNotifierConfig {

    private static final Logger logger = LoggerFactory.getLogger(MsNotifierConfig.class);

    private final SocketHandler socketHandler;

    @Bean
    public Notifier msNotifier(){
        NotifierRunner notifierRunner = new IAmNotifierRunner(socketHandler);
        NotifierImpl registrar = new NotifierImpl(notifierRunner);
        socketHandler.addHandler(MessageType.I_AM_RESPONSE.getValue(), new IAmResponseSIH(registrar));

        return registrar;
    }
}
