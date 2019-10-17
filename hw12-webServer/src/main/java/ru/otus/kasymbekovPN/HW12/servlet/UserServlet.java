package ru.otus.kasymbekovPN.HW12.servlet;

import com.google.gson.Gson;
import ru.otus.kasymbekovPN.HW12.user.User;
import ru.otus.kasymbekovPN.HW12.user.UserDao;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private final UserDao dao;
    private final Gson gson;

    public UserServlet(UserDao dao, Gson gson) {
        this.dao = dao;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameterValues("name")[0];
        User user = dao.findByName(name);

        resp.setContentType(APPLICATION_JSON);
        ServletOutputStream out = resp.getOutputStream();
        out.print(gson.toJson(user));
    }

}
