package ru.otus.kasymbekovPN.HW13.server.controllers;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW13.db.api.service.DBServiceOnlineUser;

import java.util.List;

/**
 * Контроллер для работы с пользователями
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    /**
     * Сервис работы с БД для OnlineUser
     */
    private final DBServiceOnlineUser dbService;

    /**
     * Обработчик создания пользователя
     * @param model модель
     * @param user инстанс, хранящий логин и пароль, создаваемого
     *             пользователя.
     * @return Имя, возвращаемой страницы
     */
    @PostMapping("/createUser")
    public String handleUserCreation(@NotNull Model model, OnlineUser user){
        String status;
        String login = user.getLogin().trim();
        List<OnlineUser> usersByLogin = dbService.loadRecord(login);

        if (usersByLogin.size() == 0){
            String password = user.getPassword().trim();
            if (!login.equals("") && !password.equals("")){
                dbService.createRecord(new OnlineUser(0, login, password, false));
                status = "User '" + login + " was create.";
            } else {
                status = "Login and/or password are empty.";
            }
        } else {
            status = "User : '" + login + "' already exists.";
        }

        fillModel(model, status);

        return "adminPage.html";
    }

    /**
     * Обработчик удаления пользователя
     * @param model модель
     * @param user инстанс, хранящий логин удаляемого пользователя.
     * @return Имя, возвращаемой страницы
     */
    @PostMapping("/deleteUser")
    public String handleDeleteUser(@NotNull Model model, OnlineUser user){

        String status;
        String login = user.getLogin().trim();
        if (login.equals("admin")){
            status = "Couldn't delete 'admin'";
        } else {
            boolean success = dbService.deleteRecord(login);
            status = success
                    ? "User '" + login + "' was delete."
                    : "User '" + login + "' doesn't exist";
        }

        fillModel(model, status);

        return "adminPage.html";
    }

    /**
     * Заполняет модель
     * @param model модель
     * @param status статус действия
     */
    private void fillModel(@NotNull Model model, String status){
        List<OnlineUser> users = dbService.loadAll();
        model.addAttribute("users", users);
        model.addAttribute("status", status);
        model.addAttribute("user", new OnlineUser());
    }
}
