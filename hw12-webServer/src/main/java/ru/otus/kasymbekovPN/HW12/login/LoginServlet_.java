package ru.otus.kasymbekovPN.HW12.login;

import ru.otus.kasymbekovPN.HW12.user.UserService_;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet_ extends HttpServlet {

    private static final int EXPIRE_INTERVAL = 20; // seconds
    private final UserService_ userService;

    public LoginServlet_(UserService_ userService) {
        this.userService = userService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //<
        System.out.println("login req : " + request);

        if (userService.authenticate(name, password)) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(300);
        } else {
            response.setStatus(403);
        }
    }

    //<
//    private static final int EXPIRE_INTERVAL = 20;
//    private final UserService service;
//
//    public LoginServlet(UserService service) {
//        this.service = service;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//
//        //<
//        System.out.println(name);
//        System.out.println(password);
//        //<
//
//        if (service.authenticate(name, password)){
//            HttpSession session = request.getSession(true);
//            session.setMaxInactiveInterval(300);
//            //<
//            System.out.println("auth+");
//            //<
//        } else {
//            //<
//            System.out.println("++++ : 403");
//            //<
//            response.setStatus(403);
//        }
//    }
}
