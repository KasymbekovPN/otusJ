package ru.otus.kasymbekovPN.HW12.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class AuthorizationFilter_ implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        //<
        System.out.println("auth req : " + servletRequest);

        System.out.println("sReq : " + servletRequest.getServletContext().getSessionCookieConfig());

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource:" + uri);

        HttpSession session = req.getSession(false);

        //<
        System.out.println(req.toString());
        System.out.println(Arrays.toString(req.getCookies()));

        //<
        System.out.println(session);

        if (session == null) {
            res.setStatus(403);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

//    private ServletContext context;
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        this.context = filterConfig.getServletContext();
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String uri = request.getRequestURI();
//        this.context.log("Requested Response : " + uri);
//
//        HttpSession session = request.getSession(false);
//
//        if (session == null){
//            //<
//            System.out.println("403");
//            //<
//            response.setStatus(403);
//        } else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
}
