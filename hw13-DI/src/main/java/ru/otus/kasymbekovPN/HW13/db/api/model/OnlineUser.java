package ru.otus.kasymbekovPN.HW13.db.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

import javax.persistence.*;
//<
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tOnlineUser")
public class OnlineUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @NonFinal
    private long id;

    @Column(name = "login")
    @NonFinal
    private String login;

    @Column(name = "password")
    @NonFinal
    private String password;

    @Column(name = "admin")
    @NonFinal
    private boolean admin;

    //<
//    public OnlineUser() {
//    }
//
//    public OnlineUser(long id, String login, String password, boolean admin) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.admin = admin;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(boolean admin) {
//        this.admin = admin;
//    }
//
//    public String getAdminStatus(){
//        return String.valueOf(admin);
//    }
//
//    @Override
//    public String toString() {
//        return "OnlineUser{" +
//                "id=" + id +
//                ", name='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", admin=" + admin +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        OnlineUser that = (OnlineUser) o;
//        return id == that.id &&
//                admin == that.admin &&
//                Objects.equals(login, that.login) &&
//                Objects.equals(password, that.password);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, login, password, admin);
//    }
}

