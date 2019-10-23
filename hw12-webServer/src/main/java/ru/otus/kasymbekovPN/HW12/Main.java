package ru.otus.kasymbekovPN.HW12;

import ru.otus.kasymbekovPN.HW12.server.ServerStarter;

/*
    admin :
        login : admin
        password : qwerty
 */
public class Main{
    public static void main(String[] args) throws Exception {
        new ServerStarter().start();
    }
}

