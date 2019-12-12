package ru.otus.kasymbekovPN.HW16F.messageController;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FrontendMessageTransmitter {

    private static final Logger logger = LoggerFactory.getLogger(FrontendMessageTransmitter.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void handleAuthUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/authResponse",
                data
        );
    }

    public void handleAddUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/addUserResponse",
                data
        );
    }

    public void handleDelUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/delUserResponse",
                data
        );
    }
}
