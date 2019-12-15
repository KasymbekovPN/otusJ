package ru.otus.kasymbekovPN.HW16D;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sockets.SocketHandlerArgApplier;

/**
 * Для запуска командная строка должна содержать 6 агруметов, например, localhost 8101 localhost 8091 localhost 8081 <br><br>
 *
 * <ol>
 *     <li>Собственный хост</li>
 *     <li>Собственный порт</li>
 *     <li>Хост системы сообщений</li>
 *     <li>Порт системы сообщений</li>
 *     <li>Хост frontend-клиента</li>
 *     <li>Порт frontend-клиента</li>
 * </ol>
 *
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    private final SocketHandlerArgApplier socketHandlerArgApplier;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        socketHandlerArgApplier.apply(args);
    }
}
