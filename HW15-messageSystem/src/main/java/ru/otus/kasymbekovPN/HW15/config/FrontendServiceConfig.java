package ru.otus.kasymbekovPN.HW15.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW15.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.*;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetAddUserRequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetAuthUserRequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetDelUserRequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendService;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendServiceImpl;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.handlers.GetCommonUserResponseHandler;

@Configuration
@RequiredArgsConstructor
public class FrontendServiceConfig {

    private final DBServiceOnlineUser dbService;
    private final MessageSystem messageSystem;

    @Qualifier("userDataMsgCtrlFrontendService")
    @Bean
    public FrontendService frontendService(){

        MsClientImpl databaseMsClient = new MsClientImpl(MsClientName.DATABASE.getName(), messageSystem);

        databaseMsClient.addHandler(MessageType.AUTH_USER, new GetAuthUserRequestHandler(dbService));
        databaseMsClient.addHandler(MessageType.ADD_USER, new GetAddUserRequestHandler(dbService));
        databaseMsClient.addHandler(MessageType.DEL_USER, new GetDelUserRequestHandler(dbService));

        messageSystem.addClient(databaseMsClient);

        MsClient frontendMsClient = new MsClientImpl(MsClientName.FRONTEND.getName(), messageSystem);

        FrontendService  frontendService = new FrontendServiceImpl(frontendMsClient, MsClientName.DATABASE.getName());

        frontendMsClient.addHandler(MessageType.AUTH_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.ADD_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.DEL_USER, new GetCommonUserResponseHandler(frontendService));

        messageSystem.addClient(frontendMsClient);

        return frontendService;
    }
}
