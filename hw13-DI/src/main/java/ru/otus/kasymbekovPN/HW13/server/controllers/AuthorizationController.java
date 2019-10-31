package ru.otus.kasymbekovPN.HW13.server.controllers;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW13.db.api.service.DBServiceOnlineUser;

import java.util.List;

/**
 * Констроллер авторизации
 */
@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    /**
     * Сервис работы с БД для OnlineUser
     */
    private final DBServiceOnlineUser dbService;

    /**
     * Обработка get-запроса для стартовой страницы
     * @param model модель
     * @return Имя стартовой станицы
     */
    @GetMapping("/")
    public String showAuthorization(@NotNull Model model){
        model.addAttribute("user", new OnlineUser());
        return "authorization.html";
    }

    /**
     * Обработчик авторизации
     * @param model модель
     * @param user инстанс, содержащий логин и пароль для авторизации
     * @return Имя, возвращаемой станицы
     */
    @PostMapping("/authorization")
    public String handleAuthorization(@NotNull Model model, OnlineUser user){

        List<OnlineUser> usersByLogin = dbService.loadRecord(user.getLogin().trim());

        if (usersByLogin.size() > 0){
            OnlineUser userByLogin = usersByLogin.get(0);
            String password = user.getPassword().trim();
            if (userByLogin.getPassword().equals(password)) {
                if (userByLogin.isAdmin()){
                    List<OnlineUser> users = dbService.loadAll();
                    model.addAttribute("users", users);
                    model.addAttribute("status", "Hello !!!");
                    model.addAttribute("user", new OnlineUser());
                    return "adminPage.html";
                } else {
                    model.addAttribute("login", userByLogin.getLogin());
                    return "userPage.html";
                }
            }
        }

        return "wrong.html";
    }
}
