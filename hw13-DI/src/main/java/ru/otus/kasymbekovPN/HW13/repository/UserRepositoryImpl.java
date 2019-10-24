package ru.otus.kasymbekovPN.HW13.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.kasymbekovPN.HW13.domain.User;
import ru.otus.kasymbekovPN.HW13.generators.UserIdGenerator;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    List<User> users = new ArrayList<>();
    UserIdGenerator userIdGenerator;

    @PostConstruct
    public final void init(){
        users.add(new User(this.userIdGenerator.getUserId(), "Ivan"));
        users.add(new User(this.userIdGenerator.getUserId(), "Petr"));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public long create(String name) {
        long userId = this.userIdGenerator.getUserId();
        this.users.add(new User(userId, name));
        return userId;
    }
}
