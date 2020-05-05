package ua.nure.ohol.SummaryTask4.servlet;

import com.darkprograms.speech.translator.GoogleTranslate;
import ua.nure.ohol.SummaryTask4.db.beans.Description;
import ua.nure.ohol.SummaryTask4.db.beans.Entity;
import ua.nure.ohol.SummaryTask4.db.beans.HotelClassBean;
import ua.nure.ohol.SummaryTask4.db.beans.TypeBean;
import ua.nure.ohol.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.ohol.SummaryTask4.db.connection.MySQLConnUtils;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;
import ua.nure.ohol.SummaryTask4.db.utils.Translator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "AddTour", urlPatterns = {"/addTour"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddTourServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/addTourView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String language = MyUtils.getStoredLanguage(req);

        String tourName = req.getParameter("tourname");
        String tourNameRu;
        String tourNameEn;
        String departCity = req.getParameter("departCity");
        String departCityRu;
        String departCityEn;
        float price = Float.parseFloat(req.getParameter("price"));
        int sartTickets = Integer.parseInt(req.getParameter("sartTickets"));

        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        String hotelName = req.getParameter("hotelName");
        String hotelClass = req.getParameter("hotelClass");
        String hotelSite = req.getParameter("hotelSite");


        String type = req.getParameter("Type");
        String country = req.getParameter("country");
        String countryRu;
        String countryEn;
        String program = req.getParameter("program");
        String programRu;
        String programEn;
        String sport = req.getParameter("sport");
        String sportRu;
        String sportEn;
        String beach = req.getParameter("beach");
        String beachRu;
        String beachEn;

        Part fileImage = req.getPart("filePhoto");
        String fileName = getFilename(fileImage);
        InputStream is = null;

        if (fileName != null) {
            is = fileImage.getInputStream();
        }

        String addTour = "";


        if (language == null) {
            language = "en";
        }

        if (language.equals("en")) {
            tourNameEn = tourName;
            tourNameRu = GoogleTranslate.translate("en", "ru", tourName);

            departCityEn = departCity;
            departCityRu = GoogleTranslate.translate("en", "ru", departCity);

            countryEn = country;
            countryRu = GoogleTranslate.translate("en", "ru", country);

            programEn = program;
            programRu = GoogleTranslate.translate("en", "ru", program);

            sportEn = sport;
            sportRu = GoogleTranslate.translate("en", "ru", sport);

            beachEn = beach;
            beachRu = GoogleTranslate.translate("en", "ru", beach);

        } else {
            tourNameRu = tourName;
            tourNameEn = GoogleTranslate.translate("ru", "en", tourName);

            departCityRu = departCity;
            departCityEn = GoogleTranslate.translate("ru", "en", departCity);

            countryRu = country;
            countryEn = GoogleTranslate.translate("ru", "en", country);

            programRu = program;
            programEn = GoogleTranslate.translate("ru", "en", program);

            sportRu = sport;
            sportEn = GoogleTranslate.translate("ru", "en", sport);

            beachRu = beach;
            beachEn = GoogleTranslate.translate("ru", "en", beach);
        }


        Connection connection = MyUtils.getStoredConnection(req);

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            int hotelId = DBUtils.createNewHotel(connection, HotelClassBean.getHotelClassId(hotelClass),
                    hotelName, hotelSite);
            int durationId = DBUtils.createNewDuration(connection, startDate, endDate);
            int descriptionId = DBUtils.createNewDescription(connection, TypeBean.getType(type), countryEn, is,
                    programEn, sportEn, beachEn, countryRu,
                    programRu, sportRu, beachRu);
            if (hotelId != -1 && durationId != -1 && descriptionId != -1) {

                if (DBUtils.createNewTour(connection, tourNameEn, departCityEn, hotelId
                        , price, sartTickets, sartTickets,
                        descriptionId, false, durationId, tourNameRu, departCityRu) != -1) {
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings", new Locale(language));
                    addTour = resourceBundle.getString("w.add");
                    connection.commit();
                } else {
                    ConnectionUtils.rollbackQuietly(connection);
                }
            }
        } catch (SQLException s) {
            s.printStackTrace();
            addTour += s.getMessage();
        }
        req.getSession().setAttribute("upStat4", addTour);
        resp.sendRedirect(req.getContextPath() + "/managerInfo?tab_id=tourAdm");

    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
