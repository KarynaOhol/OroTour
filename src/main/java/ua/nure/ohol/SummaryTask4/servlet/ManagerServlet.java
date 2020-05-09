package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.QuerySQL;
import ua.nure.ohol.SummaryTask4.db.beans.*;
import ua.nure.ohol.SummaryTask4.db.connection.MySQLConnUtils;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "Manager", urlPatterns ={"/managerInfo"})
public class ManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ManagerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Map<String, Entity> reservationInfo = null;
        Map<String, Entity> hotTourInfo = null;
        List<Discount> discountList = null;
        List<Users> userInf = null;
        Map<String, Entity> tourInfo = null;
        Map<Tour, Discount> tourDiscount = new LinkedHashMap<>();
        String tabId = req.getParameter("tab_id");

        Map<String ,Float> topUsers = null;


        Connection con = MyUtils.getStoredConnection(req);
        try {
            topUsers=DBUtils.findTopUsers(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            reservationInfo = DBUtils.reservationList(con);
        } catch (SQLException s) {
            s.printStackTrace();
        }

        ///hot tour
        try {
            hotTourInfo = DBUtils.findAllTour(con);
        } catch (SQLException s) {
            s.printStackTrace();
        }


        try {
            discountList = DBUtils.findAllDiscount(con);
        } catch (SQLException s) {
            s.printStackTrace();
        }

        try {
            userInf = DBUtils.findAllUsers(con);
        } catch (SQLException s) {
            s.printStackTrace();
        }

        try {
            tourInfo = DBUtils.findAllTourFully(con);
        } catch (SQLException s) {
            s.printStackTrace();
        }

//        System.out.println( tourInfo.toString());


        List<Status> statuses = new LinkedList<>();
        for (int i = 0; i < reservationInfo.size() / 4; i++) {
            Reservation res = (Reservation) reservationInfo.get("Reservation" + (i + 1));
            statuses.add(Status.getStatus(res));
        }

        for (int i = 1; i <= hotTourInfo.size() / 2; i++) {
            Tour tour = (Tour) hotTourInfo.get("Tour" + i);
            for (Discount d :
                    discountList) {
                if (d.getId() == tour.getDiscountID()) {
                    tourDiscount.put(tour, d);
                }
            }

            if (!tourDiscount.containsKey(tour)) {
                tourDiscount.put(tour, null);
            }

        }

        req.setAttribute("user", MyUtils.getLoginedUser(req.getSession()));
        req.setAttribute("statuses", statuses);
        req.setAttribute("reservationInfo", reservationInfo);
        req.setAttribute("hotTourInfo", hotTourInfo);
        req.setAttribute("countTour", hotTourInfo.size() / 2);
        req.setAttribute("tourDiscount", tourDiscount);
        req.setAttribute("userInfo", userInf);
        req.setAttribute("tourInfo", tourInfo);
        req.setAttribute("topUsers", topUsers);

        if (tabId != null) {
            req.setAttribute("tabId", tabId);
        } else {
            req.setAttribute("tabId", "reservation");
        }

//        for (Map.Entry<Tour, Discount> m : tourDiscount.entrySet()
//        ) {
//            System.out.println(m.getKey());
//            System.out.println(m.getValue());
//        }


        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/managerInfoView.jsp");
        dispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
