package ru.otus.kasymbekovPN.HW16F;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sockets.SocketHandlerArgApplier;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    private final SocketHandlerArgApplier socketHandlerArgApplier;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        socketHandlerArgApplier.init(args);
    }
}
