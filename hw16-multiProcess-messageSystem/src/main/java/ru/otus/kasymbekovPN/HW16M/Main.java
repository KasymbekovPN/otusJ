package ru.otus.kasymbekovPN.HW16M;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Для запуска командная строка должна содержать 2 именованых агрумета, например, --ms.host=localhost --ms.port=8091 <br><br>
 *
 * <ol>
 *     <li>ms.host - собственный хост</li>
 *     <li>ms.port - собственный порт</li>
 * </ol>
 *
 */
@SpringBootApplication
public class Main{

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
