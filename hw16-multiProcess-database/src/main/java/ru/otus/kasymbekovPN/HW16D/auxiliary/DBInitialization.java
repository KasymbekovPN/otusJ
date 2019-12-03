package ru.otus.kasymbekovPN.HW16D.auxiliary;

import lombok.RequiredArgsConstructor;
import model.OnlineUser;
import org.springframework.stereotype.Component;
import ru.otus.kasymbekovPN.HW16D.db.api.service.DBServiceOnlineUser;

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
