package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.connection.ConnectionUtils;
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
import java.util.LinkedList;

@WebServlet("/payment")
public class TourPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        int tourId = Integer.parseInt(req.getParameter("tourId"));

        String language = MyUtils.getStoredLanguage(req);
        if (language == null) {
            language = "en";
        }

        Connection connection = MyUtils.getStoredConnection(req);
        LinkedList<Integer> reservationId = null;
        try {
            reservationId = DBUtils.AllreservationIdForUserByTour(connection, userId, tourId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean check = false;
        for (Integer id :
                reservationId) {
            try {
                if (!DBUtils.updateStatus(connection,id,1)) {
                    check = true;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (check) {
            ConnectionUtils.rollbackQuietly(connection);
        }

        resp.sendRedirect(req.getContextPath() + "/userInfo");
    }
}
