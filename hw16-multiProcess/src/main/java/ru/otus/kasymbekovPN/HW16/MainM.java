package ru.otus.kasymbekovPN.HW16;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.kasymbekovPN.HW16.config.SocketHandlerConfig;
import ru.otus.kasymbekovPN.HW16.sockets.SocketHandler;
import ru.otus.kasymbekovPN.HW16.sockets.SocketHandlerImpl;

//<
//@SpringBootApplication
@RequiredArgsConstructor
public class MainM {

//    private final MessageSystemImpl ms;

    public static void main(String[] args) throws InterruptedException {

//        MessageSystemImpl ms = new MessageSystemImpl();

        AnnotationConfigApplicationContext cxt =
                new AnnotationConfigApplicationContext(SocketHandlerConfig.class);

        SocketHandler sh = cxt.getBean("messageSystemSocketHandler", SocketHandlerImpl.class);

//        AnnotationConfigApplicationContext context
//                = new AnnotationConfigApplicationContext(SummaryConfig.class);
//        GameSummary gsA
//                = context.getBean("gameSummary", GameSummary.class);

//        SpringApplication.run(MainM.class, args);

//        SocketHandlerImpl socketHandler = SocketHandlerImpl.newInstance(8080);


//        Thread thread = new Thread(() -> {
//            ServerImpl server = ServerImpl.newInstance(8080);
//            assert server != null;
//            server.start();
//        });
//
//        Thread thread1 = new Thread(() -> {
//            ClientImpl client = ClientImpl.newInstance("localhost", 8081);
//            assert client != null;
//            client.start();
//        });
//
//        thread.start();
//        thread1.start();

//        thread.join();
//        thread1.join();

        //<
//        //<
//        System.out.println("111111111111111111");
//        //<
//        SpringApplication.run(Main.class, args);
    }
}
