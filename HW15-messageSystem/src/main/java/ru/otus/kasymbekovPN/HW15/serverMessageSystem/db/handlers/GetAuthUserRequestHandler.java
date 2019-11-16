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

/**
 * Класс-обработчик запроса на авторизации пользователя. <br><br>
 *
 * Обрабатывает сообщение типа {@link MessageType#AUTH_USER} <br><br>
 *
 * При успешном добавлении администрирующего пользователя поле status ответа
 * {@link OnlineUserPackage} содержит "admin", поле users содержит информацию
 * о пользователях из БД. <br><br>
 *
 * При успешном добавлении неадминистрирующего пользователя поле status ответа
 * {@link OnlineUserPackage} содержит "user", поле users содержит инстанс {@link OnlineUser}
 * с информацией о данном пользователе (без пароля и индификатора в БД). <br><br>
 *
 * При неуспешной попытке авторизации поле status ответа {@link OnlineUserPackage} содержит
 * сообщение об ошибке, поле users пустое. <br><br>
 */
public class GetAuthUserRequestHandler implements ReqRespHandler {

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
                    user.setId(0);
                    user.setPassword("");
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
