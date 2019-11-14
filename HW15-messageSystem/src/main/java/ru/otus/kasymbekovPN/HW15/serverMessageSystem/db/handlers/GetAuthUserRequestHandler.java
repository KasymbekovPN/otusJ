package ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers;

import ru.otus.kasymbekovPN.HW15.clientMessageSystem.OnlineUserPackage;
import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.Message;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageType;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.RequestHandler;

import java.util.List;
import java.util.Optional;

public class GetAuthUserRequestHandler implements RequestHandler {

    private final DBServiceOnlineUser dbServiceOnlineUser;

    public GetAuthUserRequestHandler(DBServiceOnlineUser dbServiceOnlineUser) {
        this.dbServiceOnlineUser = dbServiceOnlineUser;
    }

    @Override
    public Optional<Message> handle(Message message) {
        OnlineUserPackage data = new OnlineUserPackage();
        OnlineUser receivedUser = Serializers.deserialize(message.getPayload(), OnlineUser.class);

        String login = receivedUser.getLogin().trim();
        String password = receivedUser.getPassword().trim();

        List<OnlineUser> users = dbServiceOnlineUser.loadRecord(login);

        if (users.size() > 0 ){
            OnlineUser user = users.get(0);
            if (user.getPassword().equals(password)){
                if (user.isAdmin()) {
                    data.setUsers(dbServiceOnlineUser.loadAll());
                    data.setStatus("admin");
                } else {
                    data.getUsers().add(user);
                    data.setStatus("user");
                }
            } else {
                data.setStatus("Wrong Login and/or Password");
            }
        } else {
            data.setStatus("Wrong Login and/or Password");
        }

        return Optional.of(
                new Message(
                        message.getTo(), message.getFrom(), Optional.of(message.getId()),
                        MessageType.AUTH_USER.getValue(), Serializers.serialize(data)
                )
        );
    }
}
