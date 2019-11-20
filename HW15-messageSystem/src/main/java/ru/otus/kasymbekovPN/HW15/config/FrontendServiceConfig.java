package ru.otus.kasymbekovPN.HW15.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.*;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendService;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendServiceImpl;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.handlers.GetCommonUserResponseHandler;

@Configuration
@RequiredArgsConstructor
public class FrontendServiceConfig {

    private final MessageSystem messageSystem;

    @Bean
    public FrontendService frontendService(){

        MsClient frontendMsClient = new MsClientImpl(MsClientName.FRONTEND.getName(), messageSystem);

        FrontendService  frontendService = new FrontendServiceImpl(frontendMsClient, MsClientName.DATABASE.getName());

        frontendMsClient.addHandler(MessageType.AUTH_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.ADD_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.DEL_USER, new GetCommonUserResponseHandler(frontendService));

        messageSystem.addClient(frontendMsClient);

        return frontendService;
    }
}
