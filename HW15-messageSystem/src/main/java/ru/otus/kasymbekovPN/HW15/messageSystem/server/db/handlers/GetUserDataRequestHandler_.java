package ru.otus.kasymbekovPN.HW15.messageSystem.server.db.handlers;

import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.DBService_;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MessageType_;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.Message__;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.RequestHandler_;

import java.util.Optional;

public class GetUserDataRequestHandler_ implements RequestHandler_ {

    private final DBService_ dbService;

    public GetUserDataRequestHandler_(DBService_ dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message__> handle(Message__ msg) {
        long id = Serializers.deserialize(msg.getPayload(), Long.class);
        String data = dbService.getUserData(id);
        return Optional.of(new Message__(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType_.USER_DATA.getValue(), Serializers.serialize(data)));
    }
}
