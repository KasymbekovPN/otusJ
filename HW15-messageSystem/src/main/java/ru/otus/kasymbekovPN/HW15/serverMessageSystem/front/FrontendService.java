package ru.otus.kasymbekovPN.HW15.serverMessageSystem.front;

import ru.otus.kasymbekovPN.HW15.clientMessageSystem.OnlineUserPackage;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Интерфейс сервиса для отправки сообщений в сервис работы с БД.
 */
public interface FrontendService {
    void authUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer);
    void addUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer);
    void delUser(OnlineUser user, Consumer<OnlineUserPackage> dataConsumer);
    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}
