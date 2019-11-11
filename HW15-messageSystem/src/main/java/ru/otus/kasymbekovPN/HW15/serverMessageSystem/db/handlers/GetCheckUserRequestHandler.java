package ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers;

import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.Message;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageType;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetCheckUserRequestHandler implements RequestHandler {

    private final DBServiceOnlineUser dbServiceOnlineUser;

    public GetCheckUserRequestHandler(DBServiceOnlineUser dbServiceOnlineUser) {
        this.dbServiceOnlineUser = dbServiceOnlineUser;
    }

    @Override
    public Optional<Message> handle(Message message) {
        OnlineUser user = Serializers.deserialize(message.getPayload(), OnlineUser.class);

        List<OnlineUser> data= new ArrayList<OnlineUser>();
        //<
        MessageType messageType = MessageType.WRONG_AUTH_DATA;
        List<OnlineUser> loadedUsers = dbServiceOnlineUser.loadRecord(user.getLogin());
        if (loadedUsers.size() > 0){
            OnlineUser loadedUser = loadedUsers.get(0);
            if (loadedUser.isAdmin()){
                data = dbServiceOnlineUser.loadAll();
                //<
                messageType = MessageType.IS_ADMIN;
            } else {
//                data = loadedUser;
                //<
                data.add(loadedUser);
                //<
                messageType = MessageType.IS_NOT_ADMIN;
            }
        }

        return Optional.of(
                new Message(
                        message.getTo(), message.getFrom(), Optional.of(message.getId()),
                        messageType.getValue(), Serializers.serialize(data)
                )
        );
    }
}
