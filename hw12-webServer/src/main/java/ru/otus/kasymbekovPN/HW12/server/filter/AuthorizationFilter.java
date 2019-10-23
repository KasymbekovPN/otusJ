package ru.otus.kasymbekovPN.HW12.server.filter;

import ru.otus.kasymbekovPN.HW12.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW12.db.api.service.DBServiceOnlineUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Авторизационный фильтр
 */
public class AuthorizationFilter implements Filter {

    /**
     * Сервис работы с БД для OnlineUser
     */
    private DBServiceOnlineUser service;

    /**
     * Конструктор
     * @param service Сервис работы с БД для OnlineUser
     */
    public AuthorizationFilter(DBServiceOnlineUser service) {
        this.service = service;
    }

    /**
     * При попытке авторизоваться (get-метод) проверяет
     * наличие пользователя и правильность пароля
     * @param servletRequest запрос
     * @param servletResponse ответ
     * @param filterChain цепочка фильтров
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        int errCode = 0;
        if (request.getMethod().equals("GET")){
            HttpSession session = request.getSession();
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            List<OnlineUser> records = service.loadRecord(login);
            if ((records.size() == 1) && (records.get(0).getPassword().equals(password))){
                session.setAttribute("admin", login.equals("admin"));
            } else {
                errCode = HttpServletResponse.SC_UNAUTHORIZED;
            }
        }

        if (errCode == 0){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.setStatus(errCode);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
