package ru.otus.kasymbekovPN.HW12.timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimerServlet extends HttpServlet {

    private static final String REFRESH_VARIABLE_NAME = "refreshPeriod";
    private static final String TIME_VARIABLE_NAME = "time";
    private static final String TIMER_PAGE_TEMPLATE = "timer.html";
    private static final int PERIOD_MS = 1_000;

    private final TemplateProcessor templateProcessor;

    //<
    public TimerServlet(/*TemplateProcessor templateProcessor*/) throws IOException {
//        this.templateProcessor = templateProcessor;
        //<
        this.templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(REFRESH_VARIABLE_NAME, String.valueOf(PERIOD_MS));
        pageVariables.put(TIME_VARIABLE_NAME, getTime());

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(templateProcessor.getPage(TIMER_PAGE_TEMPLATE, pageVariables));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private String getTime(){
        Date date = new Date();
        //<
//        date.getTime();
        DateFormat format = new SimpleDateFormat("HH.mm.ss");
        return format.format(date.getTime());
        //<
//        return format.format(date);
    }
}
