package ru.otus.kasymbekovPN.HW16M.messageSystem;

import java.util.Optional;

public interface ReqRespHandler {
    Optional<Message> handle(Message message);
}
