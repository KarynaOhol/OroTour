package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "DeleteTour", urlPatterns = {"/deleteTour"})
public class DeleteTourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tourId = req.getParameter("tour_id");
        String tabId = req.getParameter("tab_id");

        String language = MyUtils.getStoredLanguage(req);
        if (language == null) {
            language = "en";
        }

        String updateStatus = null;

        Connection connection = MyUtils.getStoredConnection(req);

        try {
            if (DBUtils.deleteTour(connection, Integer.parseInt(tourId))) {

                ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings", new Locale(language));
                req.setAttribute("errorString", resourceBundle.getString("w.delete"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            updateStatus = e.getMessage() + " | Exception on " + " element";
        }
        req.getSession().setAttribute("upStat5", updateStatus);
        resp.sendRedirect(req.getContextPath() + "/managerInfo?tab_id=" + tabId);

    }
}
