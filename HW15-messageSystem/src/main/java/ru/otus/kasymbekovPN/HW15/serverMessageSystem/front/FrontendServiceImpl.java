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

public class FrontendServiceImpl implements FrontendService {

    private static final Logger logger = LoggerFactory.getLogger(FrontendService.class);

    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    //<
//    @Override
//    public void getUserData(long userId, Consumer<String> dataConsumer) {
//        Message outMessage = msClient.produceMessage(databaseServiceClientName, userId, MessageType.USER_DATA);
//        consumerMap.put(outMessage.getId(), dataConsumer);
//        msClient.sendMessage(outMessage);
//    }

//    //< in one method
//    @Override
//    public void checkUser(OnlineUser user, Consumer<List<OnlineUser>> dataConsumer) {
//        Message outMessage = msClient.produceMessage(databaseServiceClientName, user, MessageType.CHECK_USER);
//        consumerMap.put(outMessage.getId(), dataConsumer);
//        msClient.sendMessage(outMessage);
//    }

    //< in one method
    @Override
    public void authUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer) {
        sendUserMessage(user, dataConsumer, MessageType.AUTH_USER);
        //<
//        Message outMessage = msClient.produceMessage(databaseServiceClientName, user, MessageType.AUTH_USER);
//        consumerMap.put(outMessage.getId(), dataConsumer);
//        msClient.sendMessage(outMessage);
    }

    //< in one method
    @Override
    public void addUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer) {
        sendUserMessage(user, dataConsumer, MessageType.ADD_USER);
        //<
//        Message outMessage = msClient.produceMessage(databaseServiceClientName, user, MessageType.ADD_USER);
//        consumerMap.put(outMessage.getId(), dataConsumer);
//        msClient.sendMessage(outMessage);
    }

    //< in one method
    @Override
    public void delUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer) {
        sendUserMessage(user, dataConsumer, MessageType.DEL_USER);
        //<
//        Message outMessage = msClient.produceMessage(databaseServiceClientName, user, MessageType.DEL_USER);
//        consumerMap.put(outMessage.getId(), dataConsumer);
//        msClient.sendMessage(outMessage);
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

    //<
//    private void sendMessage(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer, MessageType messageType){
//        Message outMessage = msClient.produceMessage(databaseServiceClientName, user, messageType);
//        consumerMap.put(outMessage.getId(), dataConsumer);
//        msClient.sendMessage(outMessage);
//    }
}
