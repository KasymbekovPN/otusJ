package ru.otus.kasymbekovPN.HW16D.config;

import introduce.IAmNotifierRunner;
import introduce.Notifier;
import introduce.NotifierImpl;
import introduce.NotifierRunner;
import lombok.RequiredArgsConstructor;
import message.MessageType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW16D.socket.inputHandler.IAmResponseSIH;
import sockets.SocketHandler;

@Configuration
@RequiredArgsConstructor
public class MsNotifierConfig {

    private final SocketHandler socketHandler;

    @Bean
    public Notifier msNotifier(){
        NotifierRunner notifierRunner = new IAmNotifierRunner(socketHandler);
        Notifier notifier = new NotifierImpl(notifierRunner);
        socketHandler.addHandler(MessageType.I_AM_RESPONSE.getValue(), new IAmResponseSIH(notifier));

        return notifier;
    }
}
