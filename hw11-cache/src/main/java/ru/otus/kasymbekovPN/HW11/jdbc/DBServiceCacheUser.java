package ru.otus.kasymbekovPN.HW11.jdbc;

import ru.otus.kasymbekovPN.HW09.api.dao.DaoUser;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceUser;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.jdbc.service.DBServiceJDBCUser;
import ru.otus.kasymbekovPN.HW11.cache.Cache;

import java.util.Optional;

/**
 * Реализация сервиса для работы класса User с БД
 */
public class DBServiceCacheUser extends DBServiceJDBCUser implements DBServiceUser {
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
        Optional<User> record = super.createRecord(user);
        record.ifPresent(value -> cache.put(value.getId(), value));
        return record;
    }

    /**
     * Обновление записи в БД
     * @param user Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    @Override
    public Optional<User> updateRecord(User user) {
        Optional<User> record = super.updateRecord(user);
        record.ifPresent(value -> cache.put(value.getId(), value));
        return record;
    }

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    @Override
    public Optional<User> loadRecord(long id) {
        User user = cache.get(id);
        if (user == null){
            Optional<User> record = super.loadRecord(id);
            record.ifPresent(value -> cache.put(value.getId(), value));
            return record;
        } else {
            return Optional.of(user);
        }
    }
}
