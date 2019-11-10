package ru.otus.kasymbekovPN.HW15.messageSystem.server.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.Message;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MessageType;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MsClient;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class FrontendServiceImpl implements FrontendService {

    private static final Logger logger = LoggerFactory.getLogger(FrontendService.class);

    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void getUserData(long userId, Consumer<String> dataConsumer) {
        Message outMessage = msClient.produceMessage(databaseServiceClientName, userId, MessageType.USER_DATA);
        consumerMap.put(outMessage.getId(), dataConsumer);
        msClient.sendMessage(outMessage);
    }

    @Override
    public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
        Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
        if (consumer == null){
            logger.warn("consumer not found for : {}", sourceMessageId);
            return Optional.empty();
        }
        return Optional.of(consumer);
    }
}