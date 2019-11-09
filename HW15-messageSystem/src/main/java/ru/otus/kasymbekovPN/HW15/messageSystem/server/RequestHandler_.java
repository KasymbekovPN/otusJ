package ru.otus.kasymbekovPN.HW15.messageSystem.server;

import java.util.Optional;

public interface RequestHandler_ {
    Optional<Message__> handle(Message__ msg);
}
