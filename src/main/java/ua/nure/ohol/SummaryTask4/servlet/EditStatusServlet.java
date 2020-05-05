package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.beans.Status;
import ua.nure.ohol.SummaryTask4.db.connection.MySQLConnUtils;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet(name = "EditStatus", urlPatterns ={"/editStatus"})
public class EditStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] stats = req.getParameterValues("stat");
        String[] reserv_id = req.getParameterValues("reserv_id");
        String tabId=req.getParameter("tab_id");


        String updateStatus = null;

        String language = MyUtils.getStoredLanguage(req);
        if(language==null){
            language="en";
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings",new Locale(language)) ;

        Connection connection = MyUtils.getStoredConnection(req);
        for (int i = 0; i < stats.length; i++) {
            try {
                if (DBUtils.updateStatus(connection, Integer.parseInt(reserv_id[i]), Status.getStatusId(stats[i]))) {
                    updateStatus = resourceBundle.getString("w.update");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                updateStatus = e.getMessage() + resourceBundle.getString("w.exception") + i ;
                break;
            }
        }
        req.getSession().setAttribute("upStat", updateStatus);
        resp.sendRedirect(req.getContextPath() + "/managerInfo?tab_id=" + tabId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
