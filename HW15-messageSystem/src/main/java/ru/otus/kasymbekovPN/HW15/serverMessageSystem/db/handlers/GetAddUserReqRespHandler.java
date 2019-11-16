package ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers;

import ru.otus.kasymbekovPN.HW15.clientMessageSystem.OnlineUserPackage;
import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.Message;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageType;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.ReqRespHandler;

import java.util.List;
import java.util.Optional;

public class GetAddUserReqRespHandler implements ReqRespHandler {

    private final DBServiceOnlineUser dbServiceOnlineUser;

    public GetAddUserReqRespHandler(DBServiceOnlineUser dbServiceOnlineUser) {
        this.dbServiceOnlineUser = dbServiceOnlineUser;
    }

    @Override
    public Optional<Message> handle(Message message) {

        OnlineUserPackage data = new OnlineUserPackage();

        OnlineUser receivedUserData = Serializers.deserialize(message.getPayload(), OnlineUser.class);

        String login = receivedUserData.getLogin().trim();
        String password = receivedUserData.getPassword().trim();

        if (!login.equals("") && !password.equals("")){
            List<OnlineUser> loadedUsers = dbServiceOnlineUser.loadRecord(login);
            if (loadedUsers.size() == 0){
                dbServiceOnlineUser.createRecord(new OnlineUser(0, login, password, false));
//                data.setValid(true);
                //<
                data.setStatus("User '" + login + "' was add.");
            } else {
//                data.setValid(false);
                //<
                data.setStatus("User '"+login+"' already exists.");
            }
        } else {
//            data.setValid(false);
            //<
            data.setStatus("Login or/and password is empty.");
        }

        data.setUsers(dbServiceOnlineUser.loadAll());

        return Optional.of(
                new Message(
                        message.getTo(), message.getFrom(), Optional.of(message.getId()),
                        MessageType.ADD_USER.getValue(), Serializers.serialize(data)
                )
        );
    }
}
