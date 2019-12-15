package ru.otus.kasymbekovPN.HW16M.messageSystem.handler;

import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;

/**
 * Интерфейс, служащий для создания обработчика сообщений системы сообщений {@link ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem}
 */
public interface MSMessageHandler {
    void handle(Message message);
}
