package ru.otus.kasymbekovPN.HW12.server.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр, реализующий проверку сессии
 */
public class SessionFilter implements Filter {

    /**
     * Выполняет проверку наличия сессии
     * @param servletRequest запрос
     * @param servletResponse ответ
     * @param filterChain цепочка фильтров
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        if (session == null){
            response.setStatus(403);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void destroy() {
    }

}
