package ru.otus.kasymbekovPN.HW16.messageSystem;

import ru.otus.kasymbekovPN.HW16.sockets.ClientImpl;
import ru.otus.kasymbekovPN.HW16.sockets.ServerImpl;

//<
//@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            ServerImpl server = ServerImpl.newInstance(8080);
            assert server != null;
            server.start();
        });

        Thread thread1 = new Thread(() -> {
            ClientImpl client = ClientImpl.newInstance("localhost", 8081);
            assert client != null;
            client.start();
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        //<
//        //<
//        System.out.println("111111111111111111");
//        //<
//        SpringApplication.run(Main.class, args);
    }
}
