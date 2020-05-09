package ua.nure.ohol.SummaryTask4.servlet;


import io.vavr.Tuple2;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "HotTours", urlPatterns = "/hotTours")
public class HotToursServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> tourMap = null;
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
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/hotToursView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();

    }
}
