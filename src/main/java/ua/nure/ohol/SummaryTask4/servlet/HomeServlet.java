package ua.nure.ohol.SummaryTask4.servlet;


import org.apache.commons.lang3.tuple.ImmutablePair;
import ua.nure.ohol.SummaryTask4.db.beans.Description;
import ua.nure.ohol.SummaryTask4.db.beans.Discount;
import ua.nure.ohol.SummaryTask4.db.beans.Duration;
import ua.nure.ohol.SummaryTask4.db.beans.Tour;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Home", urlPatterns = {"/home", "/"})
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 3770756119040486823L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ImmutablePair<ImmutablePair<Tour, Discount>, ImmutablePair<Duration, Description>>> tourMap = null;
        String language = MyUtils.getStoredLanguage(req);

        Connection connection = MyUtils.getStoredConnection(req);

        try {
            tourMap = DBUtils.findAllTourHot(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int size = (int) Math.ceil(tourMap.size() / 3d);
        req.setAttribute("size", size);
        req.setAttribute("tour", tourMap);
        // Forward to /WEB-INF/views/homeView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}