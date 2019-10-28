package ru.otus.kasymbekovPN.HW13.server.repository;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.otus.kasymbekovPN.HW13.db.api.dao.OnlineUserDao;
import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW13.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW13.db.api.service.DBServiceOnlineUserImpl;
import ru.otus.kasymbekovPN.HW13.db.hibernate.HibernateUtils;
import ru.otus.kasymbekovPN.HW13.db.hibernate.dao.OnlineUserDaoHibernate;
import ru.otus.kasymbekovPN.HW13.db.hibernate.sessionManager.SessionManagerHibernate;

/**
 * Класс, реализующий доступ к хранилищу записей OnlineUser
 */
@Repository
@RequiredArgsConstructor
public class OnlineUserRepositoryImpl implements OnlineUserRepository {

    /**
     * Сервис работы с БД для OnlineUser
     */
    @NonFinal
    private
    DBServiceOnlineUser dbService;

    /**
     * Инициализатор.
     *  + Создает сервис работы с БД.
     *  + Создает запись с данными администратора.
     */
    @PostConstruct
    public final void init() {
        this.dbService = createOnlineUserService();
        this.dbService.createRecord(
                new OnlineUser(0, "admin", "qwerty", true)
        );
    }

    /**
     * Поиск запис OnlineUser по login.
     * Возвращает либо инстанс объекта OnlineUser, если запись найдена,
     * либо null, если запись не найдена.
     * @param login Значение поля для поиска
     * @return Результат поиска.
     */
    @Override
    public OnlineUser findByLogin(String login) {
        List<OnlineUser> onlineUsers = dbService.loadRecord(login);
        if (onlineUsers.size() > 0){
            return onlineUsers.get(0);
        } else {
            return null;
        }
    }

    /**
     * Выгрузка всех записей из таблицы
     * @return Список инстансов OnlineUser с данными из таблицы
     */
    @Override
    public List<OnlineUser> loadAll() {
        return dbService.loadAll();
    }

    /**
     * Создание новой записи в таблице
     * @param login значение столбца login
     * @param password значение столбца password
     * @return Идентификатор записи.
     */
    @Override
    public long create(String login, String password) {
        OnlineUser u = new OnlineUser(0, login, password, false);
        dbService.createRecord(u);

        return u.getId();
    }

    /**
     * Удаление запис по значению столбца login
     * @param login Значение столбца login
     * @return Успешность
     */
    @Override
    public boolean delete(String login) {
        return dbService.deleteRecord(login);
    }

    /**
     * Создает сервис работы с БД для OnlineUser
     * @return Сервис
     */
    static private DBServiceOnlineUser createOnlineUserService(){
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                OnlineUser.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        OnlineUserDao dao = new OnlineUserDaoHibernate(sessionManager);
        return new DBServiceOnlineUserImpl(dao);
    }

}
