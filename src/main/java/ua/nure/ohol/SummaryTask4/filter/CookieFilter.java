package ua.nure.ohol.SummaryTask4.filter;

import ua.nure.ohol.SummaryTask4.db.beans.Users;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * find user stored in Cookie
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        Users userInSession = MyUtils.getLoginedUser(session);
        //
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }

        // Connection in JDBCFilter.
        Connection conn = MyUtils.getStoredConnection(request);

        // flag for Cookie.
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            String userName = MyUtils.getUserNameInCookie(req);
            try {
                Users user = DBUtils.findUserByLogin(conn, userName);
                MyUtils.storeLoginedUser(session, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // mark Cookie.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }

        chain.doFilter(request, response);
    }

}