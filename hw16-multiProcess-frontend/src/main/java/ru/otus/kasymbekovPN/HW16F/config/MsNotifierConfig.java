package ru.otus.kasymbekovPN.HW16F.config;

import introduce.IAmNotifierRunner;
import introduce.Notifier;
import introduce.NotifierImpl;
import introduce.NotifierRunner;
import lombok.RequiredArgsConstructor;
import message.MessageType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16F.socket.inputHandler.IAmResponseSIH;
import sockets.SocketHandler;

@Configuration
@RequiredArgsConstructor
public class MsNotifierConfig {

    private final SocketHandler socketHandler;

    @Bean
    public Notifier msNotifier(){
        NotifierRunner notifierRunner = new IAmNotifierRunner(socketHandler);
        NotifierImpl registrar = new NotifierImpl(notifierRunner);
        socketHandler.addHandler(MessageType.I_AM_RESPONSE.getValue(), new IAmResponseSIH(registrar));

        return registrar;
    }
}
