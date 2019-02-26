package pl.coderslab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/questions/*")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);



        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session= request.getSession(false);
        boolean check=(boolean) session.getAttribute("loggedUser");
        if(check!=true) {
            response.sendRedirect(request.getContextPath() + "/users/login");

        }

        //        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("loggedUser") == null) {
//            response.sendRedirect(request.getContextPath() + "/users/login");
//        } else chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
