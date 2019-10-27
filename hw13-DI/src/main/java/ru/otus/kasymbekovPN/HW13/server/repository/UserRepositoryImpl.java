package ru.otus.kasymbekovPN.HW13.server.repository;

import java.util.ArrayList;
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
//<
import ru.otus.kasymbekovPN.HW13.server.domain.User;
import ru.otus.kasymbekovPN.HW13.server.generators.UserIdGenerator;
//<
//import ru.otus.domain.User;
//import ru.otus.generators.UserIdGenerator;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

//    List<User> users = new ArrayList<>();
//    UserIdGenerator userIdGenerator;
    //<

    /**
     * Сервис работы с БД для OnlineUser
     */
    @NonFinal
    private
    DBServiceOnlineUser dbService;

    @PostConstruct
    public final void init() {

//        if (dbService != null){
//            dbService.createRecord(
//                    new OnlineUser(0, "admin", "qwerty", true)
//            );
//        }

        this.dbService = createOnlineUserService();
        this.dbService.createRecord(
                new OnlineUser(0, "admin", "qwerty", true)
        );


//        if (dbService != null)
//        {
//            dbService.createRecord(
//                    new OnlineUser(0, "admin", "qwerty", true)
//            );
//        }

//        //<
//        System.out.println(this.userIdGenerator);
//        //<
//        users.add(new User(this.userIdGenerator.getUserId(), "Ivan", "123"));
//        users.add(new User(this.userIdGenerator.getUserId(), "Petr", "123"));
    }

    @Override
    public OnlineUser findByLogin(String login) {
        List<OnlineUser> onlineUsers = dbService.loadRecord(login);
        if (onlineUsers.size() > 0){
            return onlineUsers.get(0);
        } else {
            return null;
        }

//        return dbService.loadRecord(login);
        //<
//        return null;
    }

    @Override
    public List<OnlineUser> findAll() {
        return dbService.loadAll();
        //<
//        return users;
    }

    @Override
    public long create(String login, String password) {
        OnlineUser u = new OnlineUser(0, login, password, false);
        dbService.createRecord(u);

        return u.getId();
        //<
//        long userId = this.userIdGenerator.getUserId();
//        this.users.add(new User(userId, name, "123"));
//        return userId;
    }

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


//<
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import ru.otus.kasymbekovPN.HW13.server.domain.User;
//import ru.otus.kasymbekovPN.HW13.server.generators.UserIdGenerator;
//import ru.otus.kasymbekovPN.HW13.server.generators.UserIdGeneratorImpl;
//
//@Repository
//@RequiredArgsConstructor
//public class UserRepositoryImpl implements UserRepository {
//
//    List<User> users = new ArrayList<>();
//    UserIdGenerator userIdGenerator;
////    UserIdGenerator userIdGenerator = new UserIdGeneratorImpl();
//
//    @PostConstruct
//    public final void init() {
//        users.add(new User(this.userIdGenerator.getUserId(), "Ivan"));
//        users.add(new User(this.userIdGenerator.getUserId(), "Petr"));
//    }
//
//
//
//    @Override
//    public List<User> findAll() {
//        return users;
//    }
//
//    @Override
//    public long create(String name) {
//        long userId = this.userIdGenerator.getUserId();
//        this.users.add(new User(userId, name));
//        return userId;
//    }
//}
//
////import lombok.RequiredArgsConstructor;
////import org.springframework.stereotype.Repository;
////import ru.otus.kasymbekovPN.HW13.server.domain.User;
////import ru.otus.kasymbekovPN.HW13.server.generators.UserIdGenerator;
////
////import javax.annotation.PostConstruct;
////import java.util.ArrayList;
////import java.util.List;
////
////@Repository
////@RequiredArgsConstructor
////public class UserRepositoryImpl implements UserRepository {
////
////    List<User> users = new ArrayList<>();
////    UserIdGenerator userIdGenerator;
////
////    @PostConstruct
////    public final void init(){
////        users.add(new User(this.userIdGenerator.getUserId(), "Ivan"));
////        users.add(new User(this.userIdGenerator.getUserId(), "Petr"));
////    }
////
////    @Override
////    public List<User> findAll() {
////        return users;
////    }
////
////    @Override
////    public long create(String name) {
////        long userId = this.userIdGenerator.getUserId();
////        this.users.add(new User(userId, name));
////        return userId;
////    }
////}
