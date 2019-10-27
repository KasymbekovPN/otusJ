package ru.otus.kasymbekovPN.HW13.server.controllers;

import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
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
//<
//import ru.otus.domain.User;
//import ru.otus.repostory.UserRepository;

@Controller
@RequiredArgsConstructor
public class UserController {

    UserRepository repository;

//    @GetMapping({"/", "/user/list"})
    //<
//    @GetMapping("/user/list")
//    public String userListView(@NotNull Model model) {
//        model.addAttribute("users", repository.findAll());
//        return "userList.html";
//    }

    //<
//    @GetMapping({"/sss", "/user/list"})
//    public String userListViewSSS(@NotNull Model model) {
//        model.addAttribute("users", repository.findAll());
//        return "userList.html";
//    }

//
//    @GetMapping("/user/create")
//    public String userCreateView(@NotNull Model model) {
//        model.addAttribute("user", new User());
//        return "userCreate.html";
//    }
//
//    @PostMapping("/user/save")
//    public RedirectView userSave(@NotNull @ModelAttribute User user) {
//
//        //<
//        System.out.println("----------------- : " + user);
//        //<
//
//        repository.create(user.getLogin(), "123");
//        return new RedirectView("/user/list", true);
//    }

    @PostMapping("/createUser")
    public String handleUserCreation(@NotNull Model model, OnlineUser user){

        //<
        System.out.println("------------ handleUserCreation : " +  user);
        //<

        OnlineUser findUser = repository.findByLogin(user.getLogin());

        String status;
        if (findUser == null){
            if (!user.getLogin().equals("") && !user.getPassword().equals("")){
                repository.create(user.getLogin(), user.getPassword());
                status = "User '" + user.getLogin() + " was create.";
            } else {
                status = "Login and/or password are empty.";
            }
        } else {
            status = "User : '" + user.getLogin() + "' already exists.";
        }

        fillModel(model, status);
        //<
//        List<OnlineUser> users = repository.findAll();
//        model.addAttribute("users", users);
//        model.addAttribute("status", status);
//        model.addAttribute("user", new OnlineUser());

        return "adminPage.html";
    }

    @PostMapping("/deleteUser")
    public String handleDeleteUser(@NotNull Model model, OnlineUser user){

        //<
        System.out.println("--- delete : " + user);
        //<

        String status;
        if (user.getLogin().equals("admin")){
            status = "Couldn't delete 'admin'";
        } else {
            boolean success = repository.delete(user.getLogin());
            status = success
                    ? "user '" + user.getLogin() + "' was delete."
                    : "user '" + user.getLogin() + "' doesn't exist";
        }

        fillModel(model, status);
        //<
//        List<OnlineUser> users = repository.findAll();
//        model.addAttribute("users", users);
//        model.addAttribute("status", status);
//        model.addAttribute("user", new OnlineUser());

        return "adminPage.html";
    }

    private void fillModel(@NotNull Model model, String status){
        List<OnlineUser> users = repository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("status", status);
        model.addAttribute("user", new OnlineUser());
    }
}


//<
//import lombok.RequiredArgsConstructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.view.RedirectView;
//import ru.otus.kasymbekovPN.HW13.server.domain.User;
//import ru.otus.kasymbekovPN.HW13.server.repository.UserRepository;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//
//    //<
////    package ru.otus.controllers;
////
////import lombok.RequiredArgsConstructor;
////import org.jetbrains.annotations.NotNull;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.servlet.view.RedirectView;
////import ru.otus.domain.User;
////import ru.otus.repostory.UserRepository;
////
////    @Controller
////    @RequiredArgsConstructor
////    public class UserController {
////
//
//    UserRepository repository;
//    //<
////        UserRepository repository;
////
//
//    @GetMapping({"/", "/user/list"})
//    public String userListView(@NotNull Model model){
//        model.addAttribute("users", repository.findAll());
//        return "userList.html";
//    }
//    //<
////        @GetMapping({"/", "/user/list"})
////        public String userListView(@NotNull Model model) {
////            model.addAttribute("users", repository.findAll());
////            return "userList.html";
////        }
////
//
//    @GetMapping("/user/create")
//    public String userCreateView(@NotNull Model model){
//        model.addAttribute("user", new User());
//        return "userCreate.html";
//    }
//    //<
////        @GetMapping("/user/create")
////        public String userCreateView(@NotNull Model model) {
////            model.addAttribute("user", new User());
////            return "userCreate.html";
////        }
////
//
//    @PostMapping("/user/save")
//    public RedirectView userSave(@NotNull @ModelAttribute User user){
//        repository.create(user.getName());
//        return new RedirectView("/user/list", true);
//    }
//    //<
////        @PostMapping("/user/save")
////        public RedirectView userSave(@NotNull @ModelAttribute User user) {
////            repository.create(user.getName());
////            return new RedirectView("/user/list", true);
////        }
////
////    }
//
//
//}
