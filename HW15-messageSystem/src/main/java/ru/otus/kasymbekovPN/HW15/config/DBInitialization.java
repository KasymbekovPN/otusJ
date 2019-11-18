package ru.otus.kasymbekovPN.HW15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.kasymbekovPN.HW15.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW15.db.api.service.DBServiceOnlineUser;

import javax.annotation.PostConstruct;


/**
 * Служебный класс-инициализатор БД. <br><br>
 *
 * {@link #init} - метод, записывающий данные администратора в БД.
 */
@Component
@RequiredArgsConstructor
public class DBInitialization {

    private final DBServiceOnlineUser dbService;

    @PostConstruct
    public void init(){
        dbService.createRecord(new OnlineUser(0, "admin", "qwerty", true));
    }
}
