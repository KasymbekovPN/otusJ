package ru.otus.kasymbekovPN.HW16.db;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {


//        Gson gson = new Gson();
//        JsonObject json = new JsonObject();
//        json.addProperty("field1", 123);
//        json.addProperty("field2", "Hello");
//
//        SocketHandlerImpl socketHandler = SocketHandlerImpl.newInstance(8081);
//        sleep(1);
//        assert socketHandler != null;
//        socketHandler.send(json, "localhost", 8080);


//        socketHandler.send();

//        Thread thread = new Thread(() -> {
//            ClientImpl client = ClientImpl.newInstance("localhost", 8080);
//            assert client != null;
//            client.start();
//        });
//
//        Thread thread1 = new Thread(() -> {
//            ServerImpl server = ServerImpl.newInstance(8081);
//            assert server != null;
//            server.start();
//        });
//
//        thread.start();
//        thread1.start();

//        thread.join();
//        thread1.join();
    }

    private static void sleep(int sec){
        try{
            Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

}
