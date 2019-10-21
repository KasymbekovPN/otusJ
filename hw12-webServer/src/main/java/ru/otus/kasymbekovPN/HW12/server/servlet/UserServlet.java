package ru.otus.kasymbekovPN.HW12.server.servlet;

import ru.otus.kasymbekovPN.HW12.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW12.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW12.server.utils.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервлет для работы со страницами : admin, user
 */
public class UserServlet extends HttpServlet {

    /**
     * Имя файла, содержащего страницу admin
     */
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    /**
     * Имя файла, содержащего страницу user
     */
    private static final String USER_PAGE_TEMPLATE = "user.html";

    /**
     * Имя шаблона (в admin), заменяемого на таблицу пользователей
     */
    private static final String USER_TABLE_VAR_NAME = "userTable";

    /**
     * Имя шаблона (в admin), заменяемого на статусную строку
     */
    private static final String STATUS_VAR_NAME = "status";

    /**
     * Имя шаблона (в user), заменяемого на имя пользователя
     */
    private static final String USER_NAME_VAR_NAME = "userName";

    /**
     * Процессор шаблонов
     */
    private final TemplateProcessor templateProcessor;

    /**
     * Сервис работы с БД для OnlineUser
     */
    private final DBServiceOnlineUser dbService;

    /**
     * Конструктор
     * @param dbService сервис работы с БД для OnlineUser
     */
    public UserServlet(DBServiceOnlineUser dbService) throws IOException {
        this.templateProcessor = new TemplateProcessor();
        this.dbService = dbService;
    }

    /**
     * Обработчик get-метода
     * @param req запрос
     * @param resp ответ
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean admin = (boolean)req.getSession(false).getAttribute("admin");
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(
            admin ? makeAdminPage("") : makeUserPage(req)
        );
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Обработчик post-метода
     * @param req запрос
     * @param resp ответ
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String status = "Unknown parameter(s)";
        if (parameterMap.containsKey("login") && parameterMap.containsKey("password")){
            status = addUser(req);
        } else if (parameterMap.containsKey("delLogin")){
            status = delUser(req);
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(makeAdminPage(status));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Добавляет пользователя
     * @param req запрос
     * @return Статус действия
     */
    private String addUser(HttpServletRequest req){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String status;
        if (!login.equals("") && !password.equals("")){
            List<OnlineUser> records = dbService.loadRecord(login);
            if (records.size() == 0){
                dbService.createRecord(new OnlineUser(0, login, password, false));
                status = "user : '" + login + "' was add.";
            } else {
                status = "user : '" + login + "' already exists.";
            }
        } else {
            status = "Login and/or password is empty.";
        }

        return status;
    }

    /**
     * Удаляет пользователя
     * @param req запрос
     * @return Статус действия
     */
    private String delUser(HttpServletRequest req){
        String delLogin = req.getParameter("delLogin");

        if (delLogin.equals("admin")){
            return "Couldn't delete 'admin'";
        } else {
            boolean success = dbService.deleteRecord(delLogin);
            return success
                    ? "user '" + delLogin + "' was delete."
                    : "user '" + delLogin + "' doesn't exist";
        }
    }

    /**
     * Создает страницу админа
     * @param status Статус
     * @return HTML-код страницы
     */
    private String makeAdminPage(String status) throws IOException {

        StringBuilder table = new StringBuilder(
                "<table border=1>\n" +
                        "<caption>User Table</caption>\n" +
                        "<tr><th>ID</th><th>login</th><th>password</th><th>admin</th></tr>"
        );

        List<OnlineUser> onlineUsers = dbService.loadAll();
        for (OnlineUser onlineUser : onlineUsers) {
            table.append("<tr>")
                    .append("<th>").append(onlineUser.getId()).append("</th>")
                    .append("<th>").append(onlineUser.getLogin()).append("</th>")
                    .append("<th>").append(onlineUser.getPassword()).append("</th>")
                    .append("<th>").append(onlineUser.isAdmin()).append("</th>")
                    .append("</tr>");
        }

        table.append("</table>");

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(USER_TABLE_VAR_NAME, table);
        pageVariables.put(STATUS_VAR_NAME, status);
        return templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
    }

    /**
     * Создает страницу пользователя
     * @param req Запрос
     * @return HTML-код страницы
     */
    private String makeUserPage(HttpServletRequest req) throws IOException {
        String login = req.getParameter("login");
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(USER_NAME_VAR_NAME, login);
        return templateProcessor.getPage(USER_PAGE_TEMPLATE, pageVariables);
    }
}
