package ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers;

import ru.otus.kasymbekovPN.HW15.clientMessageSystem.OnlineUserPackage;
import ru.otus.kasymbekovPN.HW15.common.Serializers;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.Message;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MessageType;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.ReqRespHandler;

import java.util.Optional;

/**
 * Класс-обработчик запроса на удаление пользователя. <br><br>
 *
 * Обрабатывает сообщение типа {@link MessageType#DEL_USER} <br><br>
 *
 * Проверяет валидность логина удаляемого пользователя - не должен быть равен
 * пустой строке или "admin" и производит удаление <br><br>
 *
 * Поле users ответа {@link OnlineUserPackage} содержит данные пользователей из БД.
 */
public class GetDelUserRequestHandler implements ReqRespHandler {

    private final DBServiceOnlineUser dbServiceOnlineUser;

    public GetDelUserRequestHandler(DBServiceOnlineUser dbServiceOnlineUser) {
        this.dbServiceOnlineUser = dbServiceOnlineUser;
    }

    @Override
    public Optional<Message> handle(Message message) {
        OnlineUserPackage data = new OnlineUserPackage();

        OnlineUser receivedUserData = Serializers.deserialize(message.getPayload(), OnlineUser.class);

        String login = receivedUserData.getLogin().trim();
        if (login.equals("")){
            data.setStatus("Login is empty.");
        } else if (login.equals("admin")){
            data.setStatus("Couldn't delete admin");
        } else {
            boolean success = dbServiceOnlineUser.deleteRecord(login);
            data.setStatus(
                    success
                    ? "User '" + login + "' was delete"
                    : "User '" + login + "' doesn't exist"
            );
        }
        data.setUsers(dbServiceOnlineUser.loadAll());

        return Optional.of(
                new Message(
                        message.getTo(), message.getFrom(), Optional.of(message.getId()),
                        MessageType.DEL_USER.getValue(), Serializers.serialize(data)
                )
        );
    }
}
