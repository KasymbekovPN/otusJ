package ru.otus.kasymbekovPN.HW11.jdbc;

import ru.otus.kasymbekovPN.HW09.api.dao.DaoUser;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceUser;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.jdbc.service.DBServiceJDBCUser;
import ru.otus.kasymbekovPN.HW11.cache.Cache;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Реализация сервиса для работы класса User с БД
 */
public class DBServiceCacheUser extends DBServiceJDBCUser implements DBServiceUser {

    private static final int DELAY = 1;

    /**
     * Кэш
     */
    private Cache<Long, User> cache;

    /**
     * Конструктор
     * @param cache кэш
     * @param dao DAO
     */
    public DBServiceCacheUser(Cache<Long, User> cache, DaoUser dao) {
        super(dao);
        this.cache = cache;
    }

    /**
     * Создание записи в БД
     * @param user Записываемый инстанс
     * @return Записанный инстанс
     */
    @Override
    public Optional<User> createRecord(User user) {
        return super.createRecord(user);
    }

    /**
     * Обновление записи в БД
     * @param user Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    @Override
    public Optional<User> updateRecord(User user) {
        return super.updateRecord(user);
    }

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    @Override
    public Optional<User> loadRecord(long id) {
        try {
            User user = cache.get(id);
            if (user == null){
                Thread.sleep(TimeUnit.SECONDS.toMillis(DELAY));
                Optional<User> record = super.loadRecord(id);
                record.ifPresent(value -> cache.put(value.getId(), value));
                return record;
            } else {
                return Optional.of(user);
            }
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }

        return Optional.empty();
    }
}
