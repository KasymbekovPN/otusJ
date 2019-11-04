package ru.otus.kasymbekovPN.HW15.L29.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.MessageType_;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.Message__;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.MsClient_;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class FrontendServiceImpl_ implements FrontendService_ {

    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl_.class);

    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
    private final MsClient_ msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl_(MsClient_ msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void getUserData(long userId, Consumer<String> dataConsumer) {
        Message__ outMsg = msClient.produceMessage(databaseServiceClientName, userId, MessageType_.USER_DATA);
        consumerMap.put(outMsg.getId(), dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
        Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
        if (consumer == null) {
            logger.warn("consumer not found for:{}", sourceMessageId);
            return Optional.empty();
        }
        return Optional.of(consumer);
    }
}
