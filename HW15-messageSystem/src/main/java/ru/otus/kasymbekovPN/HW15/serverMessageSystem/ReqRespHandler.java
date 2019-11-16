package ru.otus.kasymbekovPN.HW15.serverMessageSystem;

import java.util.Optional;

public interface ReqRespHandler {
    Optional<Message> handle(Message message);
}
