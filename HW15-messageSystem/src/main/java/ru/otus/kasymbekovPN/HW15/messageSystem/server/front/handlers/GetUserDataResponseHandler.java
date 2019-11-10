package ru.otus.kasymbekovPN.HW15.messageSystem.server.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.Message;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.RequestHandler;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.front.FrontendService;

import java.util.Optional;
import java.util.UUID;

public class GetUserDataResponseHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

    private final FrontendService frontendService;

    public GetUserDataResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message message) {
        logger.info("new message : {}", message);
        try{
            String userData = Serializers.deserialize(message.getPayload(), String.class);
            UUID sourceMessageId = message.getSourceMessageId().orElseThrow(
                    () -> new RuntimeException("Not found sourceMessage for message : {}" + message.getId())
            );
            frontendService.takeConsumer(sourceMessageId, String.class).ifPresent(
                    consumer -> consumer.accept(userData)
            );
        } catch(Exception ex){
            logger.error("message : {}, {}", message, ex);
        }

        return Optional.empty();
    }
}
