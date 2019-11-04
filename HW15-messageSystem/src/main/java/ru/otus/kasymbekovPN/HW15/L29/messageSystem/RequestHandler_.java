package ru.otus.kasymbekovPN.HW15.L29.messageSystem;

import java.util.Optional;

public interface RequestHandler_ {
    Optional<Message__> handle(Message__ msg);
}
