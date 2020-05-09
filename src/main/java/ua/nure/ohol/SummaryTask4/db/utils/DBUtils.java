package ua.nure.ohol.SummaryTask4.db.utils;

import io.vavr.Tuple2;
import ua.nure.ohol.SummaryTask4.db.Fields;
import ua.nure.ohol.SummaryTask4.db.QuerySQL;
import ua.nure.ohol.SummaryTask4.db.beans.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

/**
 * DAO level, queries execution
 */
public class DBUtils {

    public static Users findUserByLogin(Connection conn, String login) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_USER_BY_LOGIN);
        pr.setString(1, login);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Users u = new Users();
            u.setRoleId(rs.getInt(Fields.ROLE_ID));
            u.setFirstName(rs.getString(Fields.FIRST_NAME));
            u.setLastName(rs.getString(Fields.LAST_NAME));
            u.setLogin(rs.getString(Fields.LOGIN));
            u.setPassword(rs.getString(Fields.PASSWORD));
            u.setValidUser(rs.getBoolean(Fields.VALID_USER));
            u.setId(rs.getInt(Fields.USER_ID));
            u.setEmail(rs.getString(Fields.USER_EMAIL));
            u.setPhone(rs.getString(Fields.USER_PHONE));
            return u;
        }
        return null;
    }


    public static Users findUserByEmail(Connection conn, String email) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_USER_BY_EMAIL);
        pr.setString(1, email);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Users u = new Users();
            u.setRoleId(rs.getInt(Fields.ROLE_ID));
            u.setFirstName(rs.getString(Fields.FIRST_NAME));
            u.setLastName(rs.getString(Fields.LAST_NAME));
            u.setLogin(rs.getString(Fields.LOGIN));
            u.setPassword(rs.getString(Fields.PASSWORD));
            u.setValidUser(rs.getBoolean(Fields.VALID_USER));
            u.setId(rs.getInt(Fields.USER_ID));
            u.setEmail(rs.getString(Fields.USER_EMAIL));
            u.setPhone(rs.getString(Fields.USER_PHONE));
            return u;
        }
        return null;
    }

    public static Users findUserByLoginPassword(Connection conn, String login, String password) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_USER_BY_LOGIN_PASSWORD);
        pr.setString(1, login);
        pr.setString(2, password);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Users u = new Users();
            u.setRoleId(rs.getInt(Fields.ROLE_ID));
            u.setFirstName(rs.getString(Fields.FIRST_NAME));
            u.setLastName(rs.getString(Fields.LAST_NAME));
            u.setLogin(rs.getString(Fields.LOGIN));
            u.setPassword(rs.getString(Fields.PASSWORD));
            u.setValidUser(rs.getBoolean(Fields.VALID_USER));
            u.setId(rs.getInt(Fields.USER_ID));
            u.setEmail(rs.getString(Fields.USER_EMAIL));
            u.setPhone(rs.getString(Fields.USER_PHONE));
            return u;
        }
        return null;
    }

    public static Discount findDiscount(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_DISCOUNT);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Discount discount = new Discount();
            discount.setDiscountPrice(rs.getFloat(Fields.DISCOUNT_PRICE));
            discount.setDiscountDateFrom(rs.getString(Fields.DISCOUNT_DATE_FROM));
            discount.setDiscountDateTo(rs.getString(Fields.DISCOUNT_DATE_TO));
            discount.setDiscountLastChangeData(rs.getString(Fields.DISCOUNT_LAST_CHANGE));
            discount.setDiscountName(rs.getString(Fields.DISCOUNT_NAME));
            discount.setDiscountPercent(rs.getInt(Fields.DISCOUNT_PERCENT));
            discount.setDiscountStep(rs.getInt(Fields.DISCOUNT_STEP));
            discount.setId(rs.getInt(Fields.DISCOUNT_ID));
            discount.setDiscountNameRu(rs.getString(Fields.DISCOUNT_NAME_RU));
            return discount;
        }
        return null;
    }

    public static Discount findDiscountByTour(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_DISCOUNT_BY_TOUR);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Discount discount = new Discount();
            discount.setDiscountPrice(rs.getFloat(Fields.DISCOUNT_PRICE));
            discount.setDiscountDateFrom(rs.getString(Fields.DISCOUNT_DATE_FROM));
            discount.setDiscountDateTo(rs.getString(Fields.DISCOUNT_DATE_TO));
            discount.setDiscountLastChangeData(rs.getString(Fields.DISCOUNT_LAST_CHANGE));
            discount.setDiscountName(rs.getString(Fields.DISCOUNT_NAME));
            discount.setDiscountPercent(rs.getInt(Fields.DISCOUNT_PERCENT));
            discount.setDiscountStep(rs.getInt(Fields.DISCOUNT_STEP));
            discount.setId(rs.getInt(Fields.DISCOUNT_ID));
            discount.setDiscountNameRu(rs.getString(Fields.DISCOUNT_NAME_RU));
            return discount;
        }
        return null;
    }

    private static Map<String, Entity> returnTourInformation(ResultSet result) throws SQLException {
        Map<String, Entity> tourInf = new LinkedHashMap<>();

        int i = 1;
        while (result.next()) {
            Tour tour = new Tour();
            Duration du = new Duration();
            tour.setTourName(result.getString(Fields.TOUR_NAME));
            tour.setPrice(result.getFloat(Fields.TOUR_PRICE));
            tour.setHotTour(result.getBoolean(Fields.TOUR_HOT_TOUR));
            tour.setDiscountID(result.getInt(Fields.DISCOUNT_ID));
            du.setTourBeginDate(result.getString(Fields.BEGIN_DATE));
            du.setTourEndDate(result.getString(Fields.END_DATE));
            tour.setId(result.getInt(Fields.TOUR_ID));
            du.setId(result.getInt(Fields.DURATION_ID));
            tour.setDepartureCityRu(result.getString(Fields.TOUR_DEPARTURE_CITY_RU));
            tour.setTourNameRu(result.getString(Fields.TOUR_NAME_RU));
            tourInf.put("Tour" + i, tour);
            tourInf.put("Duration" + i, du);
            i++;
        }
        return tourInf;
    }

    public static List<Discount> findAllDiscount(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_FIND_ALL_DISCOUNT);
        List<Discount> tourInf = new LinkedList<>();
        int i = 1;
        while (rs.next()) {
            Discount discount = new Discount();
            discount.setDiscountPrice(rs.getFloat(Fields.DISCOUNT_PRICE));
            discount.setDiscountDateFrom(rs.getString(Fields.DISCOUNT_DATE_FROM));
            discount.setDiscountDateTo(rs.getString(Fields.DISCOUNT_DATE_TO));
            discount.setDiscountLastChangeData(rs.getString(Fields.DISCOUNT_LAST_CHANGE));
            discount.setDiscountName(rs.getString(Fields.DISCOUNT_NAME));
            discount.setDiscountNameRu(rs.getString(Fields.DISCOUNT_NAME_RU));
            discount.setDiscountPercent(rs.getInt(Fields.DISCOUNT_PERCENT));
            discount.setDiscountStep(rs.getInt(Fields.DISCOUNT_STEP));
            discount.setId(rs.getInt(Fields.DISCOUNT_ID));
            discount.setDiscountName(rs.getString(Fields.DISCOUNT_NAME));
            tourInf.add(discount);
            i++;
        }
        return tourInf;
    }

    public static int findDiscountIdByTourId(Connection conn, int tourId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_DISCOUNT_BY_TOUR_ID);
        pr.setInt(1, tourId);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            return rs.getInt(Fields.DISCOUNT_ID);
        }
        return -1;
    }

    public static List<Users> findAllUsers(Connection con) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery(QuerySQL.SQL_FIND_ALL_USERS);
        List<Users> userInf = new LinkedList<>();
        int i = 1;
        while (rs.next()) {
            Users users = new Users();
            users.setId(rs.getInt(Fields.USER_ID));
            users.setEmail(rs.getString(Fields.USER_EMAIL));
            users.setPhone(rs.getString(Fields.USER_PHONE));
            users.setLogin(rs.getString(Fields.LOGIN));
            users.setPassword(rs.getString(Fields.PASSWORD));
            users.setFirstName(rs.getString(Fields.FIRST_NAME));
            users.setLastName(rs.getString(Fields.LAST_NAME));
            users.setRoleId(rs.getInt(Fields.ROLE_ID));
            users.setValidUser(rs.getBoolean(Fields.VALID_USER));
            userInf.add(users);
            i++;
        }
        return userInf;
    }

    public static Map<String, Entity> findAllTour(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_FIND_All_TOUR);

        return returnTourInformation(rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourHotInTop
            (Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_FIND_All_TOUR_HOT_IN_TOP);

        List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> tourInf = new LinkedList<>();

        while (rs.next()) {
            Tour tour = new Tour();
            tour.setAmountTickets(rs.getInt(Fields.TOUR_AMOUNT_OF_TICKETS));
            tour.setAvailableTickets(rs.getInt(Fields.TOUR_AMOUNT_AVAILABLE_TICKETS));
            tour.setDepartureCity(rs.getString(Fields.TOUR_DEPARTURE_CITY));
            tour.setDescriptionId(rs.getInt(Fields.DESCRIPTION_ID));
            tour.setDiscountID(rs.getInt(Fields.DISCOUNT_ID));
            tour.setDurationID(rs.getInt(Fields.DURATION_ID));
            tour.setHotelId(rs.getInt(Fields.HOTEL_ID));
            tour.setHotTour(rs.getBoolean(Fields.TOUR_HOT_TOUR));
            tour.setPrice(rs.getInt(Fields.TOUR_PRICE));
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setId(rs.getInt(Fields.TOUR_ID));
            tour.setDepartureCityRu(rs.getString(Fields.TOUR_DEPARTURE_CITY_RU));
            tour.setTourNameRu(rs.getString(Fields.TOUR_NAME_RU));

            Duration duration = new Duration();
            duration.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            duration.setTourEndDate(rs.getString(Fields.END_DATE));
            duration.setId(rs.getInt(Fields.DURATION_ID));
            duration.setDurationInDays(rs.getInt(Fields.DURATION));

            Description description = new Description();
            description.setBeachActivity(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY));
            description.setCountry(rs.getString(Fields.DESCRIPTION_COUNTRY));
            description.setProgramTour(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR));
            description.setSportActivity(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY));
            description.setTypeID(rs.getInt(Fields.TYPE_ID));
            description.setId(rs.getInt(Fields.DESCRIPTION_ID));
            description.setBeachActivityRu(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY_RU));
            description.setCountryRu(rs.getString(Fields.DESCRIPTION_COUNTRY_RU));
            description.setProgramTourRu(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR_RU));
            description.setSportActivityRu(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY_RU));

            Blob blob = rs.getBlob(Fields.DESCRIPTION_PHOTO);
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            description.setPhoto(bytes);
            description.setBase64image(new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8));

            Discount discount = null;

            if (tour.getDiscountID() != 0) {
                discount = findDiscount(conn, tour.getDiscountID());
            }

            tourInf.add(new Tuple2<>(new Tuple2<>(tour, discount), new Tuple2<>(duration, description)));
        }
        return tourInf;

    }


    private static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> convert(Connection conn, ResultSet rs)
            throws SQLException {
        List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> tourInf = new LinkedList<>();

        while (rs.next()) {
            Tour tour = new Tour();
            tour.setAmountTickets(rs.getInt(Fields.TOUR_AMOUNT_OF_TICKETS));
            tour.setAvailableTickets(rs.getInt(Fields.TOUR_AMOUNT_AVAILABLE_TICKETS));
            tour.setDepartureCity(rs.getString(Fields.TOUR_DEPARTURE_CITY));
            tour.setDescriptionId(rs.getInt(Fields.DESCRIPTION_ID));
            tour.setDiscountID(rs.getInt(Fields.DISCOUNT_ID));
            tour.setDurationID(rs.getInt(Fields.DURATION_ID));
            tour.setHotelId(rs.getInt(Fields.HOTEL_ID));
            tour.setHotTour(rs.getBoolean(Fields.TOUR_HOT_TOUR));
            tour.setPrice(rs.getInt(Fields.TOUR_PRICE));
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setId(rs.getInt(Fields.TOUR_ID));
            tour.setDepartureCityRu(rs.getString(Fields.TOUR_DEPARTURE_CITY_RU));
            tour.setTourNameRu(rs.getString(Fields.TOUR_NAME_RU));

            Duration duration = new Duration();
            duration.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            duration.setTourEndDate(rs.getString(Fields.END_DATE));
            duration.setId(rs.getInt(Fields.DURATION_ID));
            duration.setDurationInDays(rs.getInt(Fields.DURATION));

            Description description = new Description();
            description.setBeachActivity(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY));
            description.setCountry(rs.getString(Fields.DESCRIPTION_COUNTRY));
            description.setProgramTour(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR));
            description.setSportActivity(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY));
            description.setTypeID(rs.getInt(Fields.TYPE_ID));
            description.setId(rs.getInt(Fields.DESCRIPTION_ID));
            description.setBeachActivityRu(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY_RU));
            description.setCountryRu(rs.getString(Fields.DESCRIPTION_COUNTRY_RU));
            description.setProgramTourRu(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR_RU));
            description.setSportActivityRu(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY_RU));

            Blob blob = rs.getBlob(Fields.DESCRIPTION_PHOTO);
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            description.setPhoto(bytes);
            description.setBase64image(new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8));

            Discount discount = null;

            if (tour.getDiscountID() != 0) {
                discount = findDiscount(conn, tour.getDiscountID());
            }

            tourInf.add(new Tuple2<>(new Tuple2<>(tour, discount), new Tuple2<>(duration, description)));
        }
        return tourInf;
    }

    //_____________________________________________________________________________________________________
    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByType
    (Connection conn, int typeId) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE);
        pr.setInt(1, typeId);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByClass
            (Connection conn, int classId) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_HOTEL_CLASS);
        pr.setInt(1, classId);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByAvalTikets
            (Connection conn, int num) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_AVALIABLE_TICKETS);
        pr.setInt(1, num);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByIntermPice
            (Connection conn, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_PRICE_DIF);
        pr.setInt(1, min);
        pr.setInt(2, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByTypeByClass
            (Connection conn, int typeId, int classId) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS);
        pr.setInt(1, typeId);
        pr.setInt(2, classId);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByTypeByClassByAvalTikets
            (Connection conn, int typeId, int classId, int num) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS_BY_AVALIABLE_TICKETS);
        pr.setInt(1, typeId);
        pr.setInt(2, classId);
        pr.setInt(3, num);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByTypeByClassByAvalTiketsByIntermPice
            (Connection conn, int typeId, int classId, int num, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS_BY_AVALIABLE_TICKETS_BY_PRICE_DIF);
        pr.setInt(1, typeId);
        pr.setInt(2, classId);
        pr.setInt(3, num);
        pr.setInt(4, min);
        pr.setInt(5, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByClassByAvalTikets
            (Connection conn, int classId, int num) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_HOTEL_CLASS_BY_AVALIABLE_TICKETS);
        pr.setInt(1, classId);
        pr.setInt(2, num);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }


    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByAvalTiketsByIntermPice
            (Connection conn, int num, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_AVALIABLE_TICKETS_BY_PRICE_DIF);
        pr.setInt(1, num);
        pr.setInt(2, min);
        pr.setInt(3, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByTypeByAvalTikets
            (Connection conn, int typeId, int num) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE_BY_AVALIABLE_TICKETS);
        pr.setInt(1, typeId);
        pr.setInt(2, num);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByTypeByIntermPice
            (Connection conn, int typeId, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE_BY_PRICE_DIF);
        pr.setInt(1, typeId);
        pr.setInt(2, min);
        pr.setInt(3, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByClassByIntermPice
            (Connection conn, int classId, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_HOTEL_CLASS_BY_PRICE_DIF);
        pr.setInt(1, classId);
        pr.setInt(2, min);
        pr.setInt(3, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByTypeByClassByIntermPice
            (Connection conn, int typeId, int classId, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS_BY_PRICE_DIF);
        pr.setInt(1, typeId);
        pr.setInt(2, classId);
        pr.setInt(3, min);
        pr.setInt(4, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByTypeByAvalTiketsByIntermPice
            (Connection conn, int typeId, int num, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_TYPE_BY_AVALIABLE_TICKETS_BY_PRICE_DIF);
        pr.setInt(1, typeId);
        pr.setInt(2, num);
        pr.setInt(3, min);
        pr.setInt(4, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourByClassByAvalTiketsByIntermPice
            (Connection conn, int classId, int num, int min, int max) throws SQLException {

        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_All_TOUR_BY_HOTEL_CLASS_AVALIABLE_TICKETS_BY_PRICE_DIF);
        pr.setInt(1, classId);
        pr.setInt(2, num);
        pr.setInt(3, min);
        pr.setInt(4, max);
        ResultSet rs = pr.executeQuery();

        return convert(conn, rs);

    }
//___________________________________

    public static List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> findAllTourHot
            (Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_FIND_All_TOUR_HOT);

        List<Tuple2<Tuple2<Tour, Discount>, Tuple2<Duration, Description>>> tourInf = new LinkedList<>();

        while (rs.next()) {
            Tour tour = new Tour();
            tour.setAmountTickets(rs.getInt(Fields.TOUR_AMOUNT_OF_TICKETS));
            tour.setAvailableTickets(rs.getInt(Fields.TOUR_AMOUNT_AVAILABLE_TICKETS));
            tour.setDepartureCity(rs.getString(Fields.TOUR_DEPARTURE_CITY));
            tour.setDescriptionId(rs.getInt(Fields.DESCRIPTION_ID));
            tour.setDiscountID(rs.getInt(Fields.DISCOUNT_ID));
            tour.setDurationID(rs.getInt(Fields.DURATION_ID));
            tour.setHotelId(rs.getInt(Fields.HOTEL_ID));
            tour.setHotTour(rs.getBoolean(Fields.TOUR_HOT_TOUR));
            tour.setPrice(rs.getInt(Fields.TOUR_PRICE));
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setId(rs.getInt(Fields.TOUR_ID));
            tour.setDepartureCityRu(rs.getString(Fields.TOUR_DEPARTURE_CITY_RU));
            tour.setTourNameRu(rs.getString(Fields.TOUR_NAME_RU));

            Duration duration = new Duration();
            duration.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            duration.setTourEndDate(rs.getString(Fields.END_DATE));
            duration.setId(rs.getInt(Fields.DURATION_ID));
            duration.setDurationInDays(rs.getInt(Fields.DURATION));

            Description description = new Description();
            description.setBeachActivity(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY));
            description.setCountry(rs.getString(Fields.DESCRIPTION_COUNTRY));
            description.setProgramTour(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR));
            description.setSportActivity(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY));
            description.setTypeID(rs.getInt(Fields.TYPE_ID));
            description.setId(rs.getInt(Fields.DESCRIPTION_ID));
            description.setBeachActivityRu(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY_RU));
            description.setCountryRu(rs.getString(Fields.DESCRIPTION_COUNTRY_RU));
            description.setProgramTourRu(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR_RU));
            description.setSportActivityRu(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY_RU));

            Blob blob = rs.getBlob(Fields.DESCRIPTION_PHOTO);
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            description.setPhoto(bytes);
            description.setBase64image(new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8));

            Discount discount = null;

            if (tour.getDiscountID() != 0) {
                discount = findDiscount(conn, tour.getDiscountID());
            }

            tourInf.add(new Tuple2<>(new Tuple2<>(tour, discount), new Tuple2<>(duration, description)));
        }
        return tourInf;

    }


    public static Map<String, Entity> findAllTourFully(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_FIND_All_TOUR_FULLY);

        Map<String, Entity> tourInf = new LinkedHashMap<>();
        int i = 1;
        while (rs.next()) {
            Tour tour = new Tour();
            tour.setAmountTickets(rs.getInt(Fields.TOUR_AMOUNT_OF_TICKETS));
            tour.setAvailableTickets(rs.getInt(Fields.TOUR_AMOUNT_AVAILABLE_TICKETS));
            tour.setDepartureCity(rs.getString(Fields.TOUR_DEPARTURE_CITY));
            tour.setDescriptionId(rs.getInt(Fields.DESCRIPTION_ID));
            tour.setDiscountID(rs.getInt(Fields.DISCOUNT_ID));
            tour.setDurationID(rs.getInt(Fields.DURATION_ID));
            tour.setHotelId(rs.getInt(Fields.HOTEL_ID));
            tour.setHotTour(rs.getBoolean(Fields.TOUR_HOT_TOUR));
            tour.setPrice(rs.getInt(Fields.TOUR_PRICE));
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setId(rs.getInt(Fields.TOUR_ID));
            tour.setDepartureCityRu(rs.getString(Fields.TOUR_DEPARTURE_CITY_RU));
            tour.setTourNameRu(rs.getString(Fields.TOUR_NAME_RU));

            Duration duration = new Duration();
            duration.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            duration.setTourEndDate(rs.getString(Fields.END_DATE));
            duration.setId(rs.getInt(Fields.DURATION_ID));

            Hotel hotel = new Hotel();
            hotel.setHotelName(rs.getString(Fields.HOTEL_NAME));
            hotel.setHotelSite(rs.getString(Fields.HOTEL_SITE));
            hotel.setHotelClass(rs.getInt(Fields.HOTEL_CLASS));
            hotel.setId(rs.getInt(Fields.HOTEL_ID));

            Description description = new Description();
            description.setBeachActivity(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY));
            description.setCountry(rs.getString(Fields.DESCRIPTION_COUNTRY));
            description.setProgramTour(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR));
            description.setSportActivity(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY));
            description.setTypeID(rs.getInt(Fields.TYPE_ID));
            description.setId(rs.getInt(Fields.DESCRIPTION_ID));
            description.setBeachActivityRu(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY_RU));
            description.setCountryRu(rs.getString(Fields.DESCRIPTION_COUNTRY_RU));
            description.setProgramTourRu(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR_RU));
            description.setSportActivityRu(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY_RU));

            tourInf.put("Tour" + i, tour);
            tourInf.put("Duration" + i, duration);
            tourInf.put("Hotel" + i, hotel);
            tourInf.put("Description" + i, description);
            i++;
        }
        return tourInf;

    }

    public static Map<String, Entity> findTourFullyByTourId(Connection conn, int tourId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(QuerySQL.SQL_FIND_TOUR_FULLY_BY_TOUR_ID);
        ps.setInt(1, tourId);

        ResultSet rs = ps.executeQuery();

        Map<String, Entity> tourInf = new LinkedHashMap<>();

        if (rs.next()) {
            Tour tour = new Tour();
            tour.setAmountTickets(rs.getInt(Fields.TOUR_AMOUNT_OF_TICKETS));
            tour.setAvailableTickets(rs.getInt(Fields.TOUR_AMOUNT_AVAILABLE_TICKETS));
            tour.setDepartureCity(rs.getString(Fields.TOUR_DEPARTURE_CITY));
            tour.setDescriptionId(rs.getInt(Fields.DESCRIPTION_ID));
            tour.setDiscountID(rs.getInt(Fields.DISCOUNT_ID));
            tour.setDurationID(rs.getInt(Fields.DURATION_ID));
            tour.setHotelId(rs.getInt(Fields.HOTEL_ID));
            tour.setHotTour(rs.getBoolean(Fields.TOUR_HOT_TOUR));
            tour.setPrice(rs.getInt(Fields.TOUR_PRICE));
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setId(rs.getInt(Fields.TOUR_ID));
            tour.setDepartureCityRu(rs.getString(Fields.TOUR_DEPARTURE_CITY_RU));
            tour.setTourNameRu(rs.getString(Fields.TOUR_NAME_RU));

            Duration duration = new Duration();
            duration.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            duration.setTourEndDate(rs.getString(Fields.END_DATE));
            duration.setDurationInDays(rs.getInt(Fields.DURATION));
            duration.setId(rs.getInt(Fields.DURATION_ID));

            Hotel hotel = new Hotel();
            hotel.setHotelName(rs.getString(Fields.HOTEL_NAME));
            hotel.setHotelSite(rs.getString(Fields.HOTEL_SITE));
            hotel.setHotelClass(rs.getInt(Fields.HOTEL_CLASS));
            hotel.setId(rs.getInt(Fields.HOTEL_ID));

            Description description = new Description();
            description.setBeachActivity(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY));
            description.setCountry(rs.getString(Fields.DESCRIPTION_COUNTRY));
            description.setProgramTour(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR));
            description.setSportActivity(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY));
            description.setTypeID(rs.getInt(Fields.TYPE_ID));
            description.setId(rs.getInt(Fields.DESCRIPTION_ID));
            description.setBeachActivityRu(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY_RU));
            description.setCountryRu(rs.getString(Fields.DESCRIPTION_COUNTRY_RU));
            description.setProgramTourRu(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR_RU));
            description.setSportActivityRu(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY_RU));

            Blob blob = rs.getBlob(Fields.DESCRIPTION_PHOTO);
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            description.setPhoto(bytes);
            description.setBase64image(new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8));

            tourInf.put("Tour", tour);
            tourInf.put("Duration", duration);
            tourInf.put("Hotel", hotel);
            tourInf.put("Description", description);
        }

        System.out.println(tourInf);

        return tourInf;
    }

    public static Map<String, Entity> reservationListForCustumer(Connection conn, int userId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_GET_RESERVATION_LIST_FOR_CUSTOMER);
        pr.setInt(1, userId);
        ResultSet rs = pr.executeQuery();
        Map<String, Entity> tourInf = new LinkedHashMap<>();
        int i = 1;
        while (rs.next()) {
            Tour tour = new Tour();
            Duration du = new Duration();
            Reservation res = new Reservation();
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setPrice(rs.getFloat(Fields.TOUR_PRICE));
            tour.setTourNameRu(rs.getString(Fields.TOUR_NAME_RU));
            tour.setId(rs.getInt(Fields.TOUR_ID));
            du.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            du.setTourEndDate(rs.getString(Fields.END_DATE));
            du.setDurationInDays(rs.getInt(Fields.DURATION));
            res.setId(rs.getInt(Fields.RESERVATION_ID));
            res.setStatusId(rs.getInt(Fields.STATUS_ID));
            res.setNumberOfreserv(rs.getInt(Fields.NUM_OF_RESERVATION));
            tourInf.put("Tour" + i, tour);
            tourInf.put("Duration" + i, du);
            tourInf.put("Reservation" + i, res);
            i++;
        }
        return tourInf;
    }

    public static LinkedList<Integer> AllreservationIdForUserByTour(Connection conn, int userId, int tourId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_ALL_USER_RESERVATION);
        pr.setInt(1, userId);
        pr.setInt(2, tourId);
        ResultSet rs = pr.executeQuery();
        LinkedList<Integer> reservationId = new LinkedList<>();
        while (rs.next()) {
            reservationId.add(rs.getInt(Fields.RESERVATION_ID));
        }
        return reservationId;
    }


    public static Map<String, Entity> reservationList(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_GET_RESERVATION_LIST);
        Map<String, Entity> tourInf = new LinkedHashMap<>();
        int i = 1;
        while (rs.next()) {
            Tour tour = new Tour();
            Duration du = new Duration();
            Reservation res = new Reservation();
            Users u = new Users();
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setPrice(rs.getFloat(Fields.TOUR_PRICE));
            tour.setDepartureCityRu(rs.getString(Fields.TOUR_DEPARTURE_CITY_RU));
            tour.setTourNameRu(rs.getString(Fields.TOUR_NAME_RU));
            du.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            du.setTourEndDate(rs.getString(Fields.END_DATE));
            res.setStatusId(rs.getInt(Fields.STATUS_ID));
            res.setId(rs.getInt(Fields.RESERVATION_ID));
            u.setId(rs.getInt(Fields.USER_ID));
            u.setLastName(rs.getString(Fields.LAST_NAME));
            u.setFirstName(rs.getString(Fields.FIRST_NAME));
            u.setEmail(rs.getString(Fields.USER_EMAIL));
            tourInf.put("Users" + i, u);
            tourInf.put("Tour" + i, tour);
            tourInf.put("Duration" + i, du);
            tourInf.put("Reservation" + i, res);
            i++;
        }
        return tourInf;
    }


    public static int getLastDiscountId(Connection con) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery(QuerySQL.SQL_FIND_LAST_DISCOUNT);
        if (rs.next()) {
            return rs.getInt(Fields.DISCOUNT_ID);
        }
        return -1;
    }

    public static boolean updateStatus(Connection conn, int reservationId, int statusId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_RESERVATION_STATUS);
        pr.setInt(1, statusId);
        pr.setInt(2, reservationId);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateHotTour(Connection conn, boolean state, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_HOT_TOUR);
        pr.setBoolean(1, state);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateAllDiscount(Connection conn, int percent, int step,
                                            String name, String nameRu, String dateFrom, String dateTo, int id)
            throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT);
        pr.setInt(1, percent);
        pr.setInt(2, step);
        pr.setString(3, name);
        pr.setString(4, nameRu);
        pr.setString(5, dateFrom);
        pr.setString(6, dateTo);
        pr.setInt(7, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateTour(Connection connection, String tourName, String tourNameRu, String departureCity,
                                     String departureCityRu, int hotelId, float price, int amountAvailableTickets, int amountTickets,
                                     int descriptionId, int durationId, int tourId) throws SQLException {
        PreparedStatement pr = connection.prepareStatement(QuerySQL.SQL_UPDATE_TOUR);
        pr.setString(1, tourName);
        pr.setString(2, tourNameRu);
        pr.setString(3, departureCity);
        pr.setString(4, departureCityRu);
        pr.setInt(5, hotelId);
        pr.setFloat(6, price);
        pr.setInt(7, amountAvailableTickets);
        pr.setInt(8, amountTickets);
        pr.setInt(9, descriptionId);
        pr.setInt(10, durationId);
        pr.setInt(11, tourId);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean updateHotel(Connection connection, int hotelId, String hotelName, int hotelClass,
                                      String hotelSite) throws SQLException {
        PreparedStatement pr = connection.prepareStatement(QuerySQL.SQL_UPDATE_HOTEL);
        pr.setString(1, hotelName);
        pr.setInt(2, hotelClass);
        pr.setString(3, hotelSite);
        pr.setInt(4, hotelId);

        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateDescription(Connection conn, int typeId,
                                            String country,
                                            InputStream photo,
                                            String programTour,
                                            String beachActivity,
                                            String sportActivity, String countryRu,
                                            String programTourRu,
                                            String beachActivityRu,
                                            String sportActivityRu, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DESCRIPTION);
        pr.setInt(1, typeId);
        pr.setString(2, country);
        pr.setBlob(3, photo);
        pr.setString(4, programTour);
        pr.setString(5, beachActivity);
        pr.setString(6, sportActivity);
        pr.setInt(11, id);
        pr.setString(7, countryRu);
        pr.setString(8, programTourRu);
        pr.setString(9, beachActivityRu);
        pr.setString(10, sportActivityRu);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean updateDuration(Connection conn, String beginDate, String endDate, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DURATION);
        pr.setString(2, beginDate);
        pr.setString(1, endDate);
        pr.setInt(3, id);

        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateDiscountPrice(Connection conn, float price, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_PRICE);
        pr.setFloat(1, price);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateDiscountLastChange(Connection conn, String date, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_DATA_LAST_CHANGE);
        pr.setString(1, date);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateTourDiscountId(Connection connection, int tourId, int discountId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(QuerySQL.SQL_UPDATE_TOUR_DISCOUNT_ID);
        ps.setInt(1, discountId);
        ps.setInt(2, tourId);

        try {
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUserValidation(Connection conn, boolean status, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_USER_VALIDATION);
        pr.setBoolean(1, status);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createNewCustomer(Connection conn, String login,
                                            String password, String firstName,
                                            String lastName,
                                            String userEmail, String userPhone) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_CUSTOMER);
        pr.setString(1, login);
        pr.setString(2, password);
        pr.setString(3, firstName);
        pr.setString(4, lastName);
        pr.setString(5, userEmail);
        pr.setString(6, userPhone);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public static int createNewDescription(Connection conn, int typeId,
                                           String country, InputStream is, String programTour,
                                           String sportActiv,
                                           String beachActiv,
                                           String countryRu, String programTourRu,
                                           String sportActivRu,
                                           String beachActivRu) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_DESCRIPTION, Statement.RETURN_GENERATED_KEYS);
        pr.setInt(1, typeId);
        pr.setString(2, country);
        pr.setString(3, programTour);
        pr.setString(4, sportActiv);
        pr.setString(5, beachActiv);
        pr.setBlob(6, is);
        pr.setString(7, countryRu);
        pr.setString(8, programTourRu);
        pr.setString(9, sportActivRu);
        pr.setString(10, beachActivRu);
        try {
            pr.execute();
            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean createNewDiscount(Connection conn, int discountPercent,
                                            int discountStep, String discountName,
                                            String discountDateFrom, String discountDateTo, String discountNameRu) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_DISCOUNT);
        pr.setInt(1, discountPercent);
        pr.setInt(2, discountStep);
        pr.setString(3, discountName);
        pr.setString(4, discountDateFrom);
        pr.setString(5, discountDateTo);
        pr.setString(6, null);
        pr.setString(7, null);
        pr.setString(8, discountNameRu);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int createNewHotel(Connection conn, int hotelClass,
                                     String hotelName,
                                     String hotelSite) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_HOTEL, Statement.RETURN_GENERATED_KEYS);
        pr.setString(1, hotelName);
        pr.setInt(2, hotelClass);
        pr.setString(3, hotelSite);
        try {
            pr.execute();
            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int createNewTour(Connection conn, String tourName,
                                    String departureCity, int hotelId, float price,
                                    int avaliableTikets, int tikets, int descId,
                                    boolean hotTour, int durId, String tourNameRu,
                                    String departureCityRu
    ) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_TOUR, Statement.RETURN_GENERATED_KEYS);
        pr.setString(1, tourName);
        pr.setString(2, departureCity);
        pr.setInt(3, hotelId);
        pr.setFloat(4, price);
        pr.setInt(5, avaliableTikets);
        pr.setInt(6, tikets);
        pr.setInt(7, descId);
        pr.setBoolean(8, hotTour);
        pr.setInt(9, durId);
        pr.setString(10, tourNameRu);
        pr.setString(11, departureCityRu);
        try {
            pr.execute();
            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int createNewDuration(Connection conn, String beginDate,
                                        String endDate) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_DURATION, Statement.RETURN_GENERATED_KEYS);
        pr.setString(1, beginDate);
        pr.setString(2, endDate);
        try {
            pr.execute();
            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int createNewReservation(Connection conn, int tourId, int statusId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_RESERVATION, Statement.RETURN_GENERATED_KEYS);
        pr.setInt(1, tourId);
        pr.setInt(2, statusId);
        try {
            pr.execute();
            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean createNewReservationList(Connection conn, int UserId, int reservationId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_RESERVATION_LIST);
        pr.setInt(1, UserId);
        pr.setInt(2, reservationId);
        try {
            pr.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteReservation(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_DELETE_RESERVATION);
        pr.setInt(1, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deleteTour(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_DELETE_TOUR);
        pr.setInt(1, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deleteDiscount(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_DELETE_DISCOUNT);
        pr.setInt(1, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static Map<String, Float> findTopUsers(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_FIND_TOP_USERS);
        Map<String, Float> map = new LinkedHashMap<>();
        while (rs.next()) {
            map.put(rs.getString(1) + " " + rs.getString(2), rs.getFloat(3));
        }
        return map;
    }

}


