package ru.otus.kasymbekovPN.HW15.messageSystem.server.db.handlers;

import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.Message;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MessageType;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.RequestHandler;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.DBService;

import java.util.Optional;

public class GetUserDataRequestHandler implements RequestHandler {

    private final DBService dbService;

    public GetUserDataRequestHandler(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message message) {
        long id = Serializers.deserialize(message.getPayload(), Long.class);
        String data = dbService.getUserData(id);
        return Optional.of(
                new Message(
                       message.getTo(), message.getFrom(), Optional.of(message.getId()),
                       MessageType.USER_DATA.getValue(), Serializers.serialize(data)
                )
        );
    }
}
