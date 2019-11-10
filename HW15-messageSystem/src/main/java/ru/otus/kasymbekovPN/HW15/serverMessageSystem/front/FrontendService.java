package ru.otus.kasymbekovPN.HW15.serverMessageSystem.front;

import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {
    void getUserData(long userId, Consumer<String> dataConsumer);
    void checkUser(OnlineUser user, Consumer<List<OnlineUser>> dataConsumer);
    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}
