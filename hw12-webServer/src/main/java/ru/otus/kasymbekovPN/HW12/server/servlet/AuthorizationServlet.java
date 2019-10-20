package ru.otus.kasymbekovPN.HW12.server.servlet;

import ru.otus.kasymbekovPN.HW12.timer.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationServlet extends HttpServlet {

    private static final String TIMER_PAGE_TEMPLATE = "authorization.html";
    private final TemplateProcessor templateProcessor;

    public AuthorizationServlet() throws IOException {
        this.templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("auth");

        Map<String, Object> pageVariables = new HashMap<>();
//        pageVariables.put(REFRESH_VARIABLE_NAME, String.valueOf(PERIOD_MS));
//        pageVariables.put(TIME_VARIABLE_NAME, getTime());

        System.out.println(req.getSession(false).getAttribute("test"));

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(templateProcessor.getPage(TIMER_PAGE_TEMPLATE, pageVariables));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
