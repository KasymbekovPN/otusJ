package ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.Message;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.RequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.front.FrontendService;

import java.util.Optional;
import java.util.UUID;

public class GetIsAdminResponseHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetIsAdminResponseHandler.class);

    private final FrontendService frontendService;

    public GetIsAdminResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message message) {
        logger.info("new message : {}", message);
        try{
//            String userData = Serializers.deserialize(message.getPayload(), String.class);
            //<
            Object userData = Serializers.deserialize(message.getPayload(), Object.class);
            UUID sourceMessageId = message.getSourceMessageId().orElseThrow(
                    () -> new RuntimeException("Not found sourceMessage for message : {}" + message.getId())
            );

            frontendService.takeConsumer(sourceMessageId, Object.class).ifPresent(
                    consumer -> consumer.accept(userData)
            );
            //<
//            frontendService.takeConsumer(sourceMessageId, String.class).ifPresent(
//                    consumer -> consumer.accept(userData)
//            );
        } catch(Exception ex){
            logger.error("message : {}, {}", message, ex);
        }

        return Optional.empty();
    }
}
