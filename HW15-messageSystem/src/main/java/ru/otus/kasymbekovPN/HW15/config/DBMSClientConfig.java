package ru.otus.kasymbekovPN.HW15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW15.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageType;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MsClientImpl;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MsClientName;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetAddUserRequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetAuthUserRequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetDelUserRequestHandler;

/**
 * Служебный класс, создающий клиент системы обмена сообщениями для
 * БД {@link MsClientImpl}.<br><br>
 *
 * {@link #databaseMsClient()} - здесть создается клиент системы обмена сообщениями {@link MessageSystem}
 * для БД. В него добавляются обработчики для различных типов сообщений
 * {@link MessageType}. Сам клиент добавляется в систему обмена сообщений.
 */
@Configuration
@RequiredArgsConstructor
public class DBMSClientConfig {

    private final DBServiceOnlineUser dbService;
    private final MessageSystem messageSystem;

    @Bean
    public MsClientImpl databaseMsClient(){
        MsClientImpl databaseMsClient = new MsClientImpl(MsClientName.DATABASE.getName(), messageSystem);

        databaseMsClient.addHandler(MessageType.AUTH_USER, new GetAuthUserRequestHandler(dbService));
        databaseMsClient.addHandler(MessageType.ADD_USER, new GetAddUserRequestHandler(dbService));
        databaseMsClient.addHandler(MessageType.DEL_USER, new GetDelUserRequestHandler(dbService));

        messageSystem.addClient(databaseMsClient);

        return databaseMsClient;
    }
}
