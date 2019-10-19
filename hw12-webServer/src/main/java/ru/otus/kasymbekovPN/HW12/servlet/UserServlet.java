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
    private final UserDao userDao;
    private final Gson gson;

    public UserServlet(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //<
        System.out.println("user doGet req : " + req + " : " + req.getSession(false) );

        String name = req.getParameterValues("name")[0];
        User user = userDao.findByName(name);

        //<
        System.out.println("name : " + name);

        resp.setContentType(APPLICATION_JSON);
        ServletOutputStream out = resp.getOutputStream();
        out.print(gson.toJson(user));
    }

    //<
//    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
//    private final UserDao dao;
//    private final Gson gson;
//
//    public UserServlet(UserDao dao, Gson gson) {
//        this.dao = dao;
//        this.gson = gson;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameterValues("name")[0];
//        User user = dao.findByName(name);
//
//        //<
//        System.out.println("name : " + name);
//
//        response.setContentType(APPLICATION_JSON);
//        ServletOutputStream out = response.getOutputStream();
//        out.print(gson.toJson(user));
//
//        System.out.println(out);
//    }

}
