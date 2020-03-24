package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.beans.Entity;
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
import java.util.Map;

@WebServlet("/searchNazarsDick")
public class SearchNazarsDickServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/searchNazarsDickView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);
        Map<String, Entity> mapHuyap = null;
        try {
            mapHuyap = DBUtils.sortByPriceDESC(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("tourMap",mapHuyap);

        RequestDispatcher dispatcher = this.getServletContext().
                    getRequestDispatcher("/WEB-INF/views/tourListView.jsp");
        dispatcher.forward(req,resp);
    }
}
