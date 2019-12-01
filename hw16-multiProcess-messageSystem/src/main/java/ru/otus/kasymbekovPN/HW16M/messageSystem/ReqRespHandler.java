package ru.otus.kasymbekovPN.HW16M.messageSystem;

import java.util.Optional;

public interface ReqRespHandler {
    //< !!! return type change with 'void'
    Optional<Message> handle(Message message);
}
