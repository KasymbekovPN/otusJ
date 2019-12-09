package ru.otus.kasymbekovPN.HW16M;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sockets.SocketHandlerArgApplier;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner{

    private final SocketHandlerArgApplier socketHandlerArgApplier;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        socketHandlerArgApplier.setArgs(args);
    }
}

//<
//@SpringBootApplication
//public class Main{
//    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(Main.class);
//        app.setWebApplicationType(WebApplicationType.NONE);
//        app.run(args);
//    }
//}