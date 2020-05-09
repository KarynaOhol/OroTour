package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.beans.Users;
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

@WebServlet("/tourBooking")
public class TourBookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = Integer.parseInt(req.getParameter("tourId"));
        int numOfpeople = Integer.parseInt(req.getParameter("numberOfPeople"));
        Users user = MyUtils.getLoginedUser(req.getSession());
        Connection con = MyUtils.getStoredConnection(req);
        int reservationId = 0;
        boolean isErrorCheck = false;

        req.getSession().setAttribute("tourId", tourId);
        if (user != null) {
            try {
                con.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < numOfpeople; i++) {
                try {
                    reservationId = DBUtils.createNewReservation(con, tourId, 0);
                } catch (SQLException e) {
                    isErrorCheck = true;
                }
                try {
                    DBUtils.createNewReservationList(con, user.getId(), reservationId);
                } catch (SQLException e) {
                    isErrorCheck = true;
                }
            }
            if (!isErrorCheck) {
                try {
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                ConnectionUtils.rollbackQuietly(con);
            }
            resp.sendRedirect(req.getContextPath() + "/tourPage?tour_id=" + tourId + "&booked=true");

        } else {
            String requestUri = req.getContextPath() + "/tourPage?tour_id=" + tourId;
            int redirectId = MyUtils.storeRedirectAfterLoginUrl(req.getSession(), requestUri);
            req.getSession().setAttribute("redirectId", redirectId);

            resp.sendRedirect(req.getContextPath() + "/tourPage?tour_id=" + tourId + "&error=error");
        }

    }
}

