package ru.otus.kasymbekovPN.HW13.db.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

import javax.persistence.*;

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
}

