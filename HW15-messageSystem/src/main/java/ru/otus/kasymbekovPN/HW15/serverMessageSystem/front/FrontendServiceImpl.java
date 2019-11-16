package ru.otus.kasymbekovPN.HW15.serverMessageSystem.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.clientMessageSystem.OnlineUserPackage;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.Message;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageType;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MsClient;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Реализация сервиса отправки сообщений в сервис работы с БД. <br>
 *
 * {@link #takeConsumer(UUID, Class)} - геттер, возвращающий обработчик
 * соответствующего сообщения-ответа <br><br>
 *
 * {@link #sendUserMessage(OnlineUser, Consumer, MessageType)} - метод отправляющий
 * сообщение. <br><br>
 *
 * {@link #authUser(OnlineUser, Consumer)} - обёртка над {@link #sendUserMessage(OnlineUser, Consumer, MessageType)}
 * для отправки сообщения типа {@link MessageType#AUTH_USER} <br><br>
 *
 * {@link #addUser(OnlineUser, Consumer)} - обёртка над {@link #sendUserMessage(OnlineUser, Consumer, MessageType)}
 * для отправки сообщения типа {@link MessageType#ADD_USER} <br><br>
 *
 * {@link #delUser(OnlineUser, Consumer)} - обёртка над {@link #sendUserMessage(OnlineUser, Consumer, MessageType)}
 * для отправки сообщения типа {@link MessageType#DEL_USER} <br><br>
 */
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
    public void authUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer) {
        sendUserMessage(user, dataConsumer, MessageType.AUTH_USER);
    }

    @Override
    public void addUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer) {
        sendUserMessage(user, dataConsumer, MessageType.ADD_USER);
    }

    @Override
    public void delUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer) {
        sendUserMessage(user, dataConsumer, MessageType.DEL_USER);
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

    private void sendUserMessage(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer, MessageType messageType){
        Message outMessage = msClient.produceMessage(databaseServiceClientName, user, messageType);
        consumerMap.put(outMessage.getId(), dataConsumer);
        msClient.sendMessage(outMessage);
    }
}
