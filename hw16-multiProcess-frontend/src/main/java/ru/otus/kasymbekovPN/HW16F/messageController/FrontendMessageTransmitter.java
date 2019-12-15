package ru.otus.kasymbekovPN.HW16F.messageController;

import lombok.RequiredArgsConstructor;
import message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Сервис, осуществляющий отправку сообщений в GUI.<br><br>
 *
 * {@link FrontendMessageTransmitter#handleAuthUserResponse(OnlineUserPackage)} : обработчик ответа, возвращенного системой
 * сообщений на запрос типа {@link MessageType#AUTH_USER_REQUEST}, перенаправляет в GUI ответ {@link OnlineUserPackage}
 * на соотв. запрос.<br><br>
 *
 * {@link FrontendMessageTransmitter#handleAddUserResponse(OnlineUserPackage)} : обработчик ответа,
 * возвращенного системой сообщений на запрос типа {@link MessageType#ADD_USER_REQUEST}, перенаправляет в GUI ответ
 * {@link OnlineUserPackage} на соотв. запрос.<br><br>
 *
 * {@link FrontendMessageTransmitter#handleDelUserResponse(OnlineUserPackage)} : обработчик ответа, возвращенного системой
 * сообщений на запрос типа {@link MessageType#DEL_USER_REQUEST}, перенаправляет в GUI ответ {@link OnlineUserPackage}
 * на соотв. запрос.<br><br>
 */
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
