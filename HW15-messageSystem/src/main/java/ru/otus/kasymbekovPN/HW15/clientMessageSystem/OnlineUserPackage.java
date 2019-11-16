package ru.otus.kasymbekovPN.HW15.clientMessageSystem;

import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс инкапсулирует в себе статус {@link OnlineUserPackage#status} действия (авторизация,
 * добавление, удаления пользователя) и результат действия {@link OnlineUserPackage#users},
 * представляющий собой список инстансов {@link OnlineUser}, хранящих пользовательские данные.
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
