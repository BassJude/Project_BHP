package pl.coderslab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AuthenticationFilterAdmin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);


        boolean loggedIn = (session != null) && (session.getAttribute("loggedUser") != null) && (boolean) session.getAttribute("loggedUser") && (session.getAttribute("admin") != null) && (boolean) session.getAttribute("admin");

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
//            response.sendRedirect("/login");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
