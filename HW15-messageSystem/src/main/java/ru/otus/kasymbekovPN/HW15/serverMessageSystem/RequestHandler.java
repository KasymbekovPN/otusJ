package ru.otus.kasymbekovPN.HW15.serverMessageSystem;

import java.util.Optional;

public interface RequestHandler {
    Optional<Message> handle(Message message);
}
