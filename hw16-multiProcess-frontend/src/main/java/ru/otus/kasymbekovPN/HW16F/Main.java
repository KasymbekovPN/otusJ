package ru.otus.kasymbekovPN.HW16F;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sockets.SocketHandlerArgApplier;

import java.util.Collections;

/**
 * Для запуска командная строка должна содержать 7 агруметов, например, localhost 9081 localhost 8091 localhost 9101 9080 <br><br>
 *
 * <ol>
 *     <li>Собственный хост</li>
 *     <li>Собственный порт</li>
 *     <li>Хост системы сообщений</li>
 *     <li>Порт системы сообщений</li>
 *     <li>Хост frontend-клиента</li>
 *     <li>Порт frontend-клиента</li>
 *     <li>Порт веб-сервера</li>
 * </ol>
 *
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    private final SocketHandlerArgApplier socketHandlerArgApplier;

    public static void main(String[] args) throws Exception {
        String serverPort = getServerPort(args);
        SpringApplication application = new SpringApplication(Main.class);
        application.setDefaultProperties(
                Collections.singletonMap("server.port", serverPort)
        );
        application.run(args);
    }

    private static String getServerPort(String... args) throws Exception{
        String serverPort = "";

        String msg = "";
        if (args.length == 7){
            serverPort = args[6];
            try{
                int iRawServerPort = Integer.parseInt(args[6]);
                if (0 > iRawServerPort || 65535 < iRawServerPort){
                    msg = "6th arg out of range [0 ... 65535]";
                }
            } catch (Exception ex){
                msg = "Invalid 6th arg : " + serverPort;
            }
        } else {
            msg = "Wrong args number - need 7.";
        }

        if (!msg.equals("")){
            throw new Exception(msg);
        }

        return serverPort;
    }

    @Override
    public void run(String... args) throws Exception {
        socketHandlerArgApplier.apply(args);
    }
}
