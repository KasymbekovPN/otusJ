package ru.otus.kasymbekovPN.HW15.messageSystem.server;

import java.util.Optional;

public interface RequestHandler {
    Optional<Message> handle(Message message);
}
