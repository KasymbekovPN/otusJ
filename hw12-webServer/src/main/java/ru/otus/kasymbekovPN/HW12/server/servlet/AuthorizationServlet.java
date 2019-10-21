package ru.otus.kasymbekovPN.HW12.server.servlet;

import ru.otus.kasymbekovPN.HW12.server.utils.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Сервлет для работы со страницей авторизации
 */
public class AuthorizationServlet extends HttpServlet {

    /**
     * Имя файла, содержащего страницу авторизацией
     */
    private static final String TIMER_PAGE_TEMPLATE = "authorization.html";

    /**
     * Процнссов шаблогов
     */
    private final TemplateProcessor templateProcessor;

    /**
     * Конструктор
     */
    public AuthorizationServlet() throws IOException {
        this.templateProcessor = new TemplateProcessor();
    }

    /**
     * Обработчик get-метода
     * @param req запрос
     * @param resp ответ
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(templateProcessor.getPage(TIMER_PAGE_TEMPLATE, new HashMap<>()));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
