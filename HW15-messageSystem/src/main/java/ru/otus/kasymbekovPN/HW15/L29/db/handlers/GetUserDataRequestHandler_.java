package ru.otus.kasymbekovPN.HW15.L29.db.handlers;

import ru.otus.kasymbekovPN.HW15.L29.app.common.Serializers_;
import ru.otus.kasymbekovPN.HW15.L29.db.DBService_;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.MessageType_;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.Message__;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.RequestHandler_;

import java.util.Optional;

public class GetUserDataRequestHandler_ implements RequestHandler_ {

    private final DBService_ dbService;

    public GetUserDataRequestHandler_(DBService_ dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message__> handle(Message__ msg) {
        long id = Serializers_.deserialize(msg.getPayload(), Long.class);
        String data = dbService.getUserData(id);
        return Optional.of(new Message__(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType_.USER_DATA.getValue(), Serializers_.serialize(data)));
    }
}
