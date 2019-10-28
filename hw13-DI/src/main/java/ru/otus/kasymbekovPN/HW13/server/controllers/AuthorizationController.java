package ru.otus.kasymbekovPN.HW13.server.controllers;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW13.server.repository.OnlineUserRepository;

import java.util.List;

/**
 * Констроллер авторизации
 */
@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    /**
     * Хранилище пользователей
     */
    OnlineUserRepository repository;

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
        OnlineUser userByLogin = repository.findByLogin(user.getLogin().trim());
        if (userByLogin != null && user.getPassword().trim().equals(userByLogin.getPassword())){
            if (userByLogin.isAdmin()){
                List<OnlineUser> users = repository.loadAll();
                model.addAttribute("users", users);
                model.addAttribute("status", "Hello !!!");
                model.addAttribute("user", new OnlineUser());
                return "adminPage.html";
            } else {
                model.addAttribute("login", userByLogin.getLogin());
                return "userPage.html";
            }
        }

        return "wrong.html";
    }
}
