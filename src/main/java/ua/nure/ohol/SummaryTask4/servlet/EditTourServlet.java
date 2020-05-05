package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.beans.Description;
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

@WebServlet(name = "EditTour", urlPatterns ={"/editTour"})
public class EditTourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/managerInfo");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Entity> tourInfo = null;
        int tourId = Integer.parseInt(req.getParameter("tour_id"));

        Connection con = MyUtils.getStoredConnection(req);
        try {
            tourInfo = DBUtils.findTourFullyByTourId(con, tourId);
        } catch (SQLException s) {
            s.printStackTrace();
        }

        Description description = (Description) tourInfo.get("Description");

        req.setAttribute("tourInfo", tourInfo);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/editTourView.jsp");
        dispatcher.forward(req, resp);
    }
}
