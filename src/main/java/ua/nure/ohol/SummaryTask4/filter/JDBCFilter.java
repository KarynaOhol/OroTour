package ua.nure.ohol.SummaryTask4.filter;


import ua.nure.ohol.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;


/**
 * Controls connection to DB, provides integrity of process
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {

    public JDBCFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


    /**Checks  belonging Servlet to request
     * @param request
     * @return
     */
    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("JDBC Filter");
        String servletPath = request.getServletPath();

        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;

        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
        }
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();

        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // Открыть  connection (соединение) только для request со специальной ссылкой.
        // (Например ссылка к servlet, jsp, ..)
        // Избегать открытия Connection для обычных запросов.
        // (Например image, css, javascript,... )
        if (this.needJDBC(req)) {

            System.out.println("Open Connection for: " + req.getServletPath());
//DB connection transaction
            Connection conn = null;
            try {
                conn = ConnectionUtils.getConnection();
                conn.setAutoCommit(false);

                MyUtils.storeConnection(request, conn);

                chain.doFilter(request, response);

                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                ConnectionUtils.rollbackQuietly(conn);
                throw new ServletException();
            } finally {
                ConnectionUtils.closeQuietly(conn);
            }
        }

        else {

            chain.doFilter(request, response);
        }

    }


}
