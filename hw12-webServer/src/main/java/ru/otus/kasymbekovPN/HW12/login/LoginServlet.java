package ru.otus.kasymbekovPN.HW12.login;

import ru.otus.kasymbekovPN.HW12.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final int EXPIRE_INTERVAL = 20;
    private final UserService service;

    public LoginServlet(UserService service) {
        this.service = service;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (service.authenticate(name, password)){
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(EXPIRE_INTERVAL);
        } else {
            response.setStatus(403);
        }
    }
}
