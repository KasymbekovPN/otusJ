package ru.otus.kasymbekovPN.HW15.L29.front;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService_ {
    void getUserData(long userId, Consumer<String> dataConsumer);
    <T>Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}
