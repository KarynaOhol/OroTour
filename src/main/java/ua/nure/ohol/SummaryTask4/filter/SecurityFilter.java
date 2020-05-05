package ua.nure.ohol.SummaryTask4.filter;

import ua.nure.ohol.SummaryTask4.db.beans.Roles;
import ua.nure.ohol.SummaryTask4.db.beans.Users;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;
import ua.nure.ohol.SummaryTask4.security.request.UserRoleRequestWrapper;
import ua.nure.ohol.SummaryTask4.security.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controls permission to pages
 */
@WebFilter(filterName = "securityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        Users loginedUser = MyUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.getLogin();

            // Роли (Role).
            String role = String.valueOf(Roles.getRole(loginedUser));

            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }


        if (SecurityUtils.isSecurityPage(request)) {


            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                int redirectId = MyUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                wrapRequest.getSession().setAttribute("redirectId", redirectId);

                response.sendRedirect(wrapRequest.getContextPath() + "/login");
                return;
            }

            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}