package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.beans.Discount;
import ua.nure.ohol.SummaryTask4.db.beans.Entity;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet(name = "TourPage", urlPatterns = {"/tourPage"})
public class TourPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tourId = req.getParameter("tour_id");
        String booking = req.getParameter("booked");

        Connection connection = MyUtils.getStoredConnection(req);

        Map<String, Entity> tour = null;


        if(tourId ==  null){
            tourId = (String) req.getSession().getAttribute("tourId");
        }

        try {
            tour = DBUtils.findTourFullyByTourId(connection, Integer.parseInt(tourId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Discount discount=null;

        try {
            discount = DBUtils.findDiscountByTour(connection, Integer.parseInt(tourId));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (tour == null || tour.isEmpty()) {
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        req.setAttribute("tour", tour);
        req.setAttribute("booked", booking);
        req.setAttribute("error", req.getParameter("error"));
        req.setAttribute("discount", discount);
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/tourPageView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
