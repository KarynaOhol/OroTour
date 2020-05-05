package ua.nure.ohol.SummaryTask4.filter;

import ua.nure.ohol.SummaryTask4.db.beans.Discount;
import ua.nure.ohol.SummaryTask4.db.beans.Tour;
import ua.nure.ohol.SummaryTask4.db.connection.MySQLConnUtils;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.DiscountCalculationUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;
import ua.nure.ohol.SummaryTask4.exception.DiscountCalculationException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * controls discount recalculation
 */
@WebFilter(filterName = "discountFilter", urlPatterns = {"/managerInfo"})
public class DiscountCalculationFilter implements Filter {

    public DiscountCalculationFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Connection conn = MyUtils.getStoredConnection(request);

        DiscountCalculationUtils.updateDiscount(conn);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
