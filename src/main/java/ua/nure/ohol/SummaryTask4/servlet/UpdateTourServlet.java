package ua.nure.ohol.SummaryTask4.servlet;

import com.darkprograms.speech.translator.GoogleTranslate;
import ua.nure.ohol.SummaryTask4.db.beans.HotelClassBean;
import ua.nure.ohol.SummaryTask4.db.beans.TypeBean;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;



import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "UpdateTour", urlPatterns ={"/updateTour"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateTourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/managerInfo");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = MyUtils.getStoredLanguage(req);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings",new Locale(language)) ;

        String tourId = req.getParameter("tour_id");
        String hotelId = req.getParameter("hotel_id");
        String descriptionId = req.getParameter("description_id");
        String durationId = req.getParameter("duration_id");

        String tourName = req.getParameter("tourname");
        String tourNameRu;
        String tourNameEn;
        String departCity = req.getParameter("departCity");
        String departCityRu;
        String departCityEn;
        String price = req.getParameter("price");
        String avalTickets = req.getParameter("avalT");
        String sartTickets = req.getParameter("sartTickets");

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

        String updateDiscount = "";

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
            if (DBUtils.updateTour(connection, tourNameEn, tourNameRu, departCityEn, departCityRu,
                    Integer.parseInt(hotelId)
                    , Float.parseFloat(price), Integer.parseInt(avalTickets), Integer.parseInt(sartTickets),
                    Integer.parseInt(descriptionId), Integer.parseInt(durationId), Integer.parseInt(tourId))) {
                updateDiscount = resourceBundle.getString("w.update");
            }
            if (DBUtils.updateHotel(connection, Integer.parseInt(hotelId), hotelName,
                    HotelClassBean.getHotelClassId(hotelClass), hotelSite)) {
                updateDiscount = resourceBundle.getString("w.update");
            }
            if (DBUtils.updateDuration(connection, startDate, endDate, Integer.parseInt(durationId))) {
                updateDiscount = resourceBundle.getString("w.update");
            }
            if (DBUtils.updateDescription(connection, TypeBean.getType(type), countryEn, is,
                    programEn, beachEn, sportEn, countryRu,
                    programRu, beachRu, sportRu, Integer.parseInt(descriptionId))) {
                updateDiscount = resourceBundle.getString("w.update");
            }
        } catch (SQLException s) {
            s.printStackTrace();
            updateDiscount += s.getMessage();
        }
        req.getSession().setAttribute("upStat4", updateDiscount);
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
