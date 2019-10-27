package ru.otus.kasymbekovPN.HW13.server.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW13.server.domain.User;
import ru.otus.kasymbekovPN.HW13.server.repository.UserRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    UserRepository repository;

    @GetMapping("/")
    public String authorizationView(@NotNull Model model){
        model.addAttribute("user", new OnlineUser());
        return "authorization.html";
    }

    @PostMapping("/authorization")
//    public String /*RedirectView*/ authorization(@NotNull @ModelAttribute OnlineUser user){
            //<
    public String /*RedirectView*/ authorization(@NotNull Model model, OnlineUser user){
        //<
//        System.out.println("---------- : user : " + user);
        //<

//        Object user = model.asMap().get("user");
//        System.out.println("+++++ : " + user);

        OnlineUser userByLogin = repository.findByLogin(user.getLogin());
        if (userByLogin != null && user.getPassword().equals(userByLogin.getPassword())){
            if (userByLogin.isAdmin()){
                List<OnlineUser> users = repository.findAll();
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



    //<
//    @PostMapping("/user/save")
//    public RedirectView userSave(@NotNull @ModelAttribute User user) {
//
//        //<
//        System.out.println("----------------- : " + user);
//        //<
//
//        repository.create(user.getName());
//        return new RedirectView("/user/list", true);
//    }

    //<
//    @GetMapping("/authorization")
//    public String authorizationHandler(@NonNull Model model){
//        //<
//        System.out.println("------ authorizationHandler : " + model);
//        //<
//
//        //<
//        return "authorization.html";
//        //<
//    }
}
