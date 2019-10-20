package ru.otus.kasymbekovPN.HW12.server.filter;

import ru.otus.kasymbekovPN.HW12.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW12.db.api.service.DBServiceOnlineUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuthorizationFilter implements Filter {

    private ServletContext context;
    private DBServiceOnlineUser service;

    public AuthorizationFilter(DBServiceOnlineUser service) {
        this.service = service;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        this.context.log("Requested Resource : " + request.getRequestURI());
        //<
        System.out.println("+++++ : auth");

        System.out.println("++++++++++++++++++ : " + request.getMethod());

        int errCode = 0;
        if (request.getMethod().equals("GET")){
            HttpSession session = request.getSession();
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            List<OnlineUser> records = service.loadRecord(login);
            if ((records.size() == 1) && (records.get(0).getPassword().equals(password))){
                session.setAttribute("admin", login.equals("admin"));
            } else {
                errCode = 403;
            }
        }

        if (errCode == 0){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.setStatus(errCode);
        }

//        List<OnlineUser> records = service.loadRecord(login);
//        if ((records.size() == 1 && records.get(0).getPassword().equals(password)) || request.getMethod().equals("POST")){
//            session.setAttribute(
//                    "admin", login.equals("admin")
//            );
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            response.setStatus(401);
//        }


        //<
//        System.out.println(login);
//        System.out.println(password);
        //<

//        if (session == null){
//            response.setStatus(401);
//        } else {
//            session.setAttribute("test", 12345);
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void destroy() {
    }
}
