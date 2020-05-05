package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;
import ua.nure.ohol.SummaryTask4.db.utils.Translator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet(name = "EditDiscount", urlPatterns = {"/editDiscount"})
public class EditDiscountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings", new Locale(language));

        String[] checks = req.getParameterValues("check");
        String[] tourId = req.getParameterValues("tour_id");
        String[] query = req.getParameterValues("query");
        String[] discName = req.getParameterValues("discName");
        String[] discNameRu;
        String[] discNameEn;
        String[] percent = req.getParameterValues("percent");
        String[] step = req.getParameterValues("step");
        String[] dateFrom = req.getParameterValues("dateFrom");
        String[] dateTo = req.getParameterValues("dateTo");
        String tabId = req.getParameter("tab_id");


        if (language.equals("en")) {
            discNameEn = discName;
            discNameRu = new String[discName.length];
            for (int i = 0; i < discName.length; i++) {
                discNameRu[i] = Translator.translate("en", "ru", discName[i]);
            }
        } else {
            discNameRu = discName;
            discNameEn = new String[discName.length];
            for (int i = 0; i < discName.length; i++) {
                discNameEn[i] = Translator.translate("ru", "en", discName[i]);
            }
        }


        String updateDiscount = "";

        Connection connection = MyUtils.getStoredConnection(req);

        int j = 0;
        for (int i = 0; i < tourId.length; i++) {
            int discoutId = 0;
            try {
                discoutId = DBUtils.findDiscountIdByTourId(connection, Integer.parseInt(tourId[i]));
            } catch (SQLException s) {
                s.printStackTrace();
            }

            if (discoutId != -1 && checks[i].equals("true")) {
                try {
                    if (query[i].equals("update")) {
                        if (DBUtils.updateAllDiscount(connection, Integer.parseInt(percent[j]), Integer.parseInt(step[j]),
                                discNameEn[j], discNameRu[j], dateFrom[j], dateTo[j], discoutId)) {
                            DBUtils.updateDiscountLastChange(connection, null, discoutId);

                            updateDiscount = resourceBundle.getString("w.update");
                        }
                    }
                } catch (SQLException s) {
                    s.printStackTrace();
                    updateDiscount = s.getMessage();
                }
            } else if (discoutId != -1 && checks[i].equals("false")) {
                try {
                    if (DBUtils.deleteDiscount(connection, discoutId)) {
                        updateDiscount = resourceBundle.getString("w.delete");
                    }
                } catch (SQLException s) {
                    s.printStackTrace();
                    updateDiscount = s.getMessage();
                }

            } else if (discoutId == -1 && checks[i].equals("true")) {
                try {
                    if (query[i].equals("insert")) {
                        if (DBUtils.createNewDiscount(connection, Integer.parseInt(percent[j]), Integer.parseInt(step[j]),
                                discNameEn[j], dateFrom[j], dateTo[j], discNameRu[j])) {
                            updateDiscount = resourceBundle.getString("w.add");
                            DBUtils.updateTourDiscountId(connection, Integer.parseInt(tourId[i]),
                                    DBUtils.getLastDiscountId(connection));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    updateDiscount = e.getMessage();
                }
            }

            j++;
        }

//
//        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//            System.out.println(entry.getKey());
//            System.out.println(Arrays.toString(entry.getValue()));
//            System.out.println("__________________________________________________");
//        }
        req.getSession().setAttribute("upStat3", updateDiscount);
        resp.sendRedirect(req.getContextPath() + "/managerInfo?tab_id=" + tabId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
