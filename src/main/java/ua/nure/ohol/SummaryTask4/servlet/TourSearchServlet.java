package ua.nure.ohol.SummaryTask4.servlet;

import javafx.util.Pair;
import ua.nure.ohol.SummaryTask4.db.beans.*;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TourSearch", urlPatterns = {"/tourSearch"})
public class TourSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pair<Pair<Tour, Discount>, Pair<Duration, Description>>> tourMap = null;
        String language = MyUtils.getStoredLanguage(req);

        Connection connection = MyUtils.getStoredConnection(req);

        try {
            tourMap = DBUtils.findAllTourHotInTop(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int size = (int) Math.ceil(tourMap.size() / 2d);
        req.setAttribute("size", size);
        req.setAttribute("tour", tourMap);
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/tourSearchView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(Arrays.toString(entry.getValue()));
        }


        String tourType = req.getParameter("tourType");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        String numberOfPeople = req.getParameter("numberOfPeople");
        String hotelType = req.getParameter("hotelType");
        String information;

        if (tourType == null) {
            tourType = "";
        }
        if(hotelType == null){
            hotelType = "";
        }
        if (minPrice == null) {
            minPrice = "";
        }
        if (maxPrice == null) {
            maxPrice = "";
        }
        if (numberOfPeople == null) {
            numberOfPeople = "";
        }

        List<Pair<Pair<Tour, Discount>, Pair<Duration, Description>>> tour = null;

        Connection conn = MyUtils.getStoredConnection(req);
        if (tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            information = "Please choose parameters to execute search";

        }
        if (!tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByType(conn, Integer.parseInt(tourType));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByClass(conn, Integer.parseInt(hotelType));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && !numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByAvalTikets(conn, Integer.parseInt(numberOfPeople));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByIntermPice(conn, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (!tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByTypeByClass(conn, Integer.parseInt(tourType), Integer.parseInt(hotelType));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (!tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && !numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByTypeByClassByAvalTikets(conn, Integer.parseInt(tourType),
                        Integer.parseInt(hotelType), Integer.parseInt(numberOfPeople));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (!tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && !numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByTypeByClassByAvalTiketsByIntermPice(conn, Integer.parseInt(tourType),
                        Integer.parseInt(hotelType), Integer.parseInt(numberOfPeople),
                        Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && !numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByClassByAvalTikets(conn,
                        Integer.parseInt(hotelType), Integer.parseInt(numberOfPeople) );
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && !numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByAvalTiketsByIntermPice(conn,
                        Integer.parseInt(numberOfPeople),
                        Integer.parseInt(minPrice),Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (!tourType.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty() && !numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByTypeByAvalTikets(conn,
                        Integer.parseInt(tourType),Integer.parseInt(numberOfPeople));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (!tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByTypeByIntermPice(conn,Integer.parseInt(tourType),
                        Integer.parseInt(minPrice),Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByClassByIntermPice(conn,
                        Integer.parseInt(hotelType),
                        Integer.parseInt(minPrice),Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (!tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByTypeByClassByIntermPice(conn,Integer.parseInt(tourType),
                        Integer.parseInt(hotelType),
                        Integer.parseInt(minPrice),Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (!tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && !numberOfPeople.isEmpty() && hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByTypeByAvalTiketsByIntermPice(conn,Integer.parseInt(tourType),
                        Integer.parseInt(numberOfPeople),
                        Integer.parseInt(minPrice),Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (tourType.isEmpty() && !minPrice.isEmpty() && !maxPrice.isEmpty() && !numberOfPeople.isEmpty() && !hotelType.isEmpty()) {
            try {
                tour = DBUtils.findAllTourByClassByAvalTiketsByIntermPice(conn,
                        Integer.parseInt(hotelType),Integer.parseInt(numberOfPeople),
                        Integer.parseInt(minPrice),Integer.parseInt(maxPrice));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        int size = (int) Math.ceil(tour.size() / 2d);
        req.setAttribute("size", size);
        req.setAttribute("tour", tour);
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/tourSearchView.jsp");
        dispatcher.forward(req, resp);
    }
}
