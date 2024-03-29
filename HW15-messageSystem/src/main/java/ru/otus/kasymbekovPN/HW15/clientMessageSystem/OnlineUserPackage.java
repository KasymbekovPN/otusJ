package ru.otus.kasymbekovPN.HW15.clientMessageSystem;

import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetAddUserRequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetAuthUserRequestHandler;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.db.handlers.GetDelUserRequestHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс инкапсулирует в себе статус {@link OnlineUserPackage#status} действия (авторизация,
 * добавление, удаления пользователя) и результат действия {@link OnlineUserPackage#users},
 * представляющий собой список инстансов {@link OnlineUser}, хранящих пользовательские данные.<br><br>
 *
 * При успешной авторизации администрирующего пользователя поле {@link #status} содержит строку "admin",
 * поле {@link #users} содержит набор инстансов {@link OnlineUser}, в которые была выгружена информация о
 * пользоветелях из БД.<br><br>
 *
 * При успешной авторизации неадминистрирующего пользователя поле {@link #status} содержит строку "user",
 * поле {@link #users} содержит один инстанс {@link OnlineUser}, в котором заполнено только поле login<br><br>
 *
 * При неуспешной попытке авторизации поле {@link #status} содержит сообщение об ошибке, поле {@link #users}
 * пустое<br><br>
 *
 * При запросе на добавление или удаление пользователя поле {@link #status} содержит статус действия, поле
 * users {@link #users} содержит информацию о пользователях, содержащихся в БД.<br><br>
 *
 * @see GetAuthUserRequestHandler
 * @see GetAddUserRequestHandler
 * @see GetDelUserRequestHandler
 */
public class OnlineUserPackage implements Serializable {

    private List<OnlineUser> users;
    private String status;

    public List<OnlineUser> getUsers() {
        return users;
    }

    public void setUsers(List<OnlineUser> users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OnlineUserPackage() {
        this.status = "";
        this.users = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "OnlineUserPackage{" +
                "users=" + users +
                ", status='" + status + '\'' +
                '}';
    }
}
