package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.beans.Status;
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
import java.time.LocalDate;
import java.util.*;

@WebServlet(name = "EditHotTour", urlPatterns = {"/editHot"})
public class EditHotTourServlet extends HttpServlet {
    public EditHotTourServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String[] checks = req.getParameterValues("check");
        String[] tourId = req.getParameterValues("tour_id");
        String[] endDate = req.getParameterValues("end_date");
        String tabId = req.getParameter("tab_id");

        String language = MyUtils.getStoredLanguage(req);
        if (language == null) {
            language = "en";
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings", new Locale(language));

        String updateStatus = null;

        Connection connection = MyUtils.getStoredConnection(req);

        for (int i = 0; i < tourId.length; i++) {
            boolean ch = Boolean.parseBoolean(checks[i]);
            try {
                if (DBUtils.updateHotTour(connection, ch, Integer.parseInt(tourId[i]))) {
                    if (ch) {
                        if (DBUtils.createNewDiscount(connection, 50, 10,
                                "Hot tour discount", LocalDate.now().toString(), endDate[i],
                                "Скидка Горящий тур")) {
                            DBUtils.updateTourDiscountId(connection, Integer.parseInt(tourId[i]),
                                    DBUtils.getLastDiscountId(connection));
                        }
                        updateStatus = resourceBundle.getString("w.update");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                updateStatus = e.getMessage() + resourceBundle.getString("w.exception") + i;
                break;
            }

        }
        req.getSession().setAttribute("upStat2", updateStatus);
        resp.sendRedirect(req.getContextPath() + "/managerInfo?tab_id=" + tabId);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
