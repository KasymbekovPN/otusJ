package ru.otus.kasymbekovPN.HW15.L29.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.L29.app.common.Serializers_;
import ru.otus.kasymbekovPN.HW15.L29.front.FrontendService_;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.Message__;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.RequestHandler_;

import java.util.Optional;
import java.util.UUID;

public class GetUserDataResponseHandler_ implements RequestHandler_ {

    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler_.class);

    private final FrontendService_ frontendService;

    public GetUserDataResponseHandler_(FrontendService_ frontendService) {
            this.frontendService = frontendService;
        }

    @Override
    public Optional<Message__> handle(Message__ msg) {
        logger.info("new message:{}", msg);
        try {
            String userData = Serializers_.deserialize(msg.getPayload(), String.class);
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
            frontendService.takeConsumer(sourceMessageId, String.class).ifPresent(consumer -> consumer.accept(userData));

        } catch (Exception ex) {
            logger.error("msg:" + msg, ex);
        }
        return Optional.empty();
    }
}
