package ru.otus.kasymbekovPN.HW12.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW12.db.api.dao.OnlineUserDao;
import ru.otus.kasymbekovPN.HW12.db.api.model.OnlineUser;
import ru.otus.kasymbekovPN.HW12.db.api.service.DBServiceOnlineUser;
import ru.otus.kasymbekovPN.HW12.db.api.service.DBServiceOnlineUserImpl;
import ru.otus.kasymbekovPN.HW12.db.hibernate.HibernateUtils;
import ru.otus.kasymbekovPN.HW12.db.hibernate.dao.OnlineUserDaoHibernate;
import ru.otus.kasymbekovPN.HW12.db.hibernate.sessionManager.SessionManagerHibernate;
import ru.otus.kasymbekovPN.HW12.server.filter.AuthorizationFilter;
import ru.otus.kasymbekovPN.HW12.server.filter.SessionFilter;
import ru.otus.kasymbekovPN.HW12.server.servlet.AuthorizationServlet;
import ru.otus.kasymbekovPN.HW12.server.servlet.UserServlet;

public class ServerStarter {

    private final static Logger logger = LoggerFactory.getLogger(ServerStarter.class);
    private final static int PORT = 8080;
    private final static String STATIC = "/static";

    private DBServiceOnlineUser dbService;

    public ServerStarter() {
        dbService = createOnlineUserService();
        createAdmin(dbService);
    }

    public void start() throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(STATIC);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addFilter(new FilterHolder(new SessionFilter()), "/authorization", null);
        context.addFilter(new FilterHolder(new AuthorizationFilter(dbService)), "/user", null);

        context.addServlet(new ServletHolder(new AuthorizationServlet()), "/authorization");
        context.addServlet(new ServletHolder(new UserServlet(dbService)), "/user");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    static private DBServiceOnlineUser createOnlineUserService(){
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                OnlineUser.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        OnlineUserDao dao = new OnlineUserDaoHibernate(sessionManager);
        return new DBServiceOnlineUserImpl(dao);
    }

    static private void createAdmin(DBServiceOnlineUser dbService){
        if (dbService != null)
        {
            dbService.createRecord(
                    new OnlineUser(0, "admin", "qwerty", true)
            );
        }
    }
}
