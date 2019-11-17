package ru.otus.kasymbekovPN.HW16.db;

import ru.otus.kasymbekovPN.HW16.sockets.ClientImpl;
import ru.otus.kasymbekovPN.HW16.sockets.ServerImpl;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            ClientImpl client = ClientImpl.newInstance("localhost", 8080);
            assert client != null;
            client.start();
        });

        Thread thread1 = new Thread(() -> {
            ServerImpl server = ServerImpl.newInstance(8081);
            assert server != null;
            server.start();
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }
}
