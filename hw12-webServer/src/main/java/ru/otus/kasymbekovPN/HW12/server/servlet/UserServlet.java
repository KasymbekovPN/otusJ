package ru.otus.kasymbekovPN.HW12.server.servlet;

import ru.otus.kasymbekovPN.HW12.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW12.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW12.timer.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServlet extends HttpServlet {

    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private static final String USER_PAGE_TEMPLATE = "user.html";
    private static final String USER_TABLE_VAR_NAME = "userTable";
    private static final String STATUS_VAR_NAME = "status";
    private static final String USER_NAME_VAR_NAME = "userName";

    private final TemplateProcessor templateProcessor;
    private final DBServiceOnlineUser dbService;

    public UserServlet(DBServiceOnlineUser dbService) throws IOException {
        this.templateProcessor = new TemplateProcessor();
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //<
        System.out.println("user");

        //<
//        HttpSession session = req.getSession(false);
//        String pageTemplate = (Boolean)session.getAttribute("admin") ? ADMIN_PAGE_TEMPLATE : USER_PAGE_TEMPLATE;
//        Map<String, Object> pageVariables = new HashMap<>();
//        pageVariables.put(REFRESH_VARIABLE_NAME, String.valueOf(PERIOD_MS));
//        pageVariables.put(TIME_VARIABLE_NAME, getTime());


        boolean admin = (boolean)req.getSession(false).getAttribute("admin");
        resp.setContentType("text/html;charset=utf-8");
//        resp.getWriter().println(templateProcessor.getPage(pageTemplate, pageVariables));
        //<
        resp.getWriter().println(
            admin ? makeAdminPage("") : makeUserPage(req)
        );
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //<
        System.out.println("+++++++++ : " + req.getParameterMap());
        //<

//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        String status = "";
//        List<OnlineUser> records = dbService.loadRecord(login);
//        if (records.size() == 0){
//            dbService.createRecord(new OnlineUser(0, login, password, false));
//            status = "user : '" + login + "' was add.";
//        } else {
//            status = "user : '" + login + "' already exists.";
//        }
        //<
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

    private String addUser(HttpServletRequest req){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String status = "";
        List<OnlineUser> records = dbService.loadRecord(login);
        if (records.size() == 0){
            dbService.createRecord(new OnlineUser(0, login, password, false));
            status = "user : '" + login + "' was add.";
        } else {
            status = "user : '" + login + "' already exists.";
        }

        return status;
    }

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

    private String makeAdminPage(String status) throws IOException {

        StringBuilder table = new StringBuilder(
                "<table border=1>\n" +
                        "<caption>User Table</caption>\n" +
                        "<tr><th>ID</th><th>login</th><th>password</th><th>admin</th></tr>"
        );

        long id = 1;
        Optional<OnlineUser> maybeOnlineUser = Optional.empty();
        do {
            maybeOnlineUser = dbService.loadRecord(id++);
            maybeOnlineUser.ifPresent(onlineUser -> table.append("<tr>")
                    .append("<th>").append(onlineUser.getId()).append("</th>")
                    .append("<th>").append(onlineUser.getLogin()).append("</th>")
                    .append("<th>").append(onlineUser.getPassword()).append("</th>")
                    .append("<th>").append(onlineUser.isAdmin()).append("</th>")
                    .append("</tr>"));
        } while (maybeOnlineUser.isPresent());

        table.append("</table>");

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(USER_TABLE_VAR_NAME, table);
        pageVariables.put(STATUS_VAR_NAME, status);
        return templateProcessor.getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
    }

    private String makeUserPage(HttpServletRequest req) throws IOException {
        String login = req.getParameter("login");
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(USER_NAME_VAR_NAME, login);
        return templateProcessor.getPage(USER_PAGE_TEMPLATE, pageVariables);
    }
}
