package ru.otus.kasymbekovPN.HW15.clientMessageSystem;

import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OnlineUserPackage implements Serializable {

    private List<OnlineUser> users;
    //<
//    private boolean valid;
    private String status;

    public List<OnlineUser> getUsers() {
        return users;
    }

    public void setUsers(List<OnlineUser> users) {
        this.users = users;
    }

    //<
//    public boolean isValid() {
//        return valid;
//    }
//
//    public void setValid(boolean valid) {
//        this.valid = valid;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OnlineUserPackage() {
//        this.valid = false;
        //<
        this.status = "";
        this.users = new ArrayList<>();
    }

    //<
//    public OnlineUserPackage(boolean valid, String status) {
//        this.valid = valid;
//        this.status = status;
//        this.users = new ArrayList<>();
//    }
//
//    public OnlineUserPackage(List<OnlineUser> users, String status) {
//        this.users = users;
//        this.status = status;
//        this.valid =
//    }
//
//    public OnlineUserPackage(List<OnlineUser> users, boolean valid, String status) {
//        this.users = users;
//        this.valid = valid;
//        this.status = status;
//    }


    @Override
    public String toString() {
        return "OnlineUserPackage{" +
                "users=" + users +
                //<
//                ", valid=" + valid +
                ", status='" + status + '\'' +
                '}';
    }
}
