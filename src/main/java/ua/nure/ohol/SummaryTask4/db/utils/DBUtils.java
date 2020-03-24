package ua.nure.ohol.SummaryTask4.db.utils;

import ua.nure.ohol.SummaryTask4.db.Fields;
import ua.nure.ohol.SummaryTask4.db.QuerySQL;
import ua.nure.ohol.SummaryTask4.db.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

    public static Tour findTour(Connection conn, int tourId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_TOUR);
        pr.setInt(1, tourId);
        ResultSet rs = pr.executeQuery();
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
            return tour;
        }
        return null;
    }

    public static Description findDescription(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_DESCRIPTION);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Description description = new Description();
            description.setBeachActivity(rs.getString(Fields.DESCRIPTION_BEACH_ACTIVITY));
            description.setCountry(rs.getString(Fields.DESCRIPTION_COUNTRY));
            description.setProgramTour(rs.getString(Fields.DESCRIPTION_PROGRAM_TOUR));
            description.setSportActivity(rs.getString(Fields.DESCRIPTION_SPORT_ACTIVITY));
            description.setTypeID(rs.getInt(Fields.TYPE_ID));
            description.setId(rs.getInt(Fields.DESCRIPTION_ID));
            return description;
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
            return discount;
        }
        return null;
    }

    public static Hotel findHotel(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_HOTEL);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Hotel hotel = new Hotel();
            hotel.setHotelName(rs.getString(Fields.HOTEL_NAME));
            hotel.setHotelSite(rs.getString(Fields.HOTEL_SITE));
            hotel.setHotelClass(rs.getInt(Fields.HOTEL_CLASS));
            hotel.setId(rs.getInt(Fields.HOTEL_ID));
            return hotel;
        }
        return null;
    }

    public static Duration findDuration(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_DURATION);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Duration duration = new Duration();
            duration.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            duration.setTourEndDate(rs.getString(Fields.END_DATE));
            duration.setId(rs.getInt(Fields.DURATION_ID));
            return duration;
        }
        return null;
    }

    private static Map<String, Entity> returnTourInformation(ResultSet result) throws SQLException {
        Map<String, Entity> tourInf = new HashMap<>();
        Tour tour = new Tour();
        Duration du = new Duration();
        tour.setTourName(result.getString(Fields.TOUR_NAME));
        tour.setPrice(result.getFloat(Fields.TOUR_PRICE));
        tour.setHotTour(result.getBoolean(Fields.TOUR_HOT_TOUR));
        tour.setDiscountID(result.getInt(Fields.DISCOUNT_ID));
        du.setTourBeginDate(result.getString(Fields.BEGIN_DATE));
        du.setTourEndDate(result.getString(Fields.END_DATE));
        tourInf.put("Tour", tour);
        tourInf.put("Duration", du);
        return tourInf;
    }

    public static Map<String, Entity> findHotTour(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_FIND_HOT_TOUR);
        if (rs.next()) {
            return returnTourInformation(rs);
        }
        return null;
    }

    public static Map<String, Entity> findTourType(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_FIND_TOURTYPE);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Map<String, Entity> tourInf = new HashMap<>();
            Tour tour = new Tour();
            Duration du = new Duration();
            Description desc = new Description();
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setPrice(rs.getFloat(Fields.TOUR_PRICE));
            tour.setHotTour(rs.getBoolean(Fields.TOUR_HOT_TOUR));
            tour.setDiscountID(rs.getInt(Fields.DISCOUNT_ID));
            du.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            du.setTourEndDate(rs.getString(Fields.END_DATE));
            desc.setTypeID(rs.getInt(Fields.TYPE_ID));
            tourInf.put(Tour.class.getName(), tour);
            tourInf.put(Duration.class.getName(), du);
            tourInf.put(Description.class.getName(), desc);
        }
        return null;
    }

    public static Map<String, Entity> sortByPriceDESC(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_SORT_TOURS_BY_PRICE_DESC);
        if (rs.next()) {
            return returnTourInformation(rs);
        }
        return null;
    }

    public static Map<String, Entity> sortByPriceASC(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_SORT_TOURS_BY_PRICE_ASC);
        if (rs.next()) {
            return returnTourInformation(rs);
        }
        return null;
    }

    public static Map<String, Entity> sortByAvailableTicketsDESC(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_SORT_TOURS_BY_AVTICKETS_DESC);
        if (rs.next()) {
            return returnTourInformation(rs);
        }
        return null;
    }

    public static Map<String, Entity> sortByAvailableTicketsASC(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_SORT_TOURS_BY_AVTICKETS_ASC);
        if (rs.next()) {
            return returnTourInformation(rs);
        }
        return null;
    }

    public static Map<String, Entity> sortByHotelType(Connection conn, int hotelClass) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_TOURS_BY_HOTEL_TYPE);
        pr.setInt(1, hotelClass);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            return returnTourInformation(rs);
        }
        return null;
    }

    public static Map<String, Entity> reservationListForCustumer(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_GET_RESERVATION_LIST_FOR_CUSTOMER);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            Map<String, Entity> tourInf = new HashMap<>();
            Tour tour = new Tour();
            Duration du = new Duration();
            Reservation res = new Reservation();
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setPrice(rs.getFloat(Fields.TOUR_PRICE));
            du.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            du.setTourEndDate(rs.getString(Fields.END_DATE));
            res.setStatusId(rs.getInt(Fields.STATUS_ID));
            tourInf.put(Tour.class.getName(), tour);
            tourInf.put(Duration.class.getName(), du);
            tourInf.put(Reservation.class.getName(), res);
            return tourInf;
        }
        return null;
    }

    public static Map<String, Entity> reservationList(Connection conn, int id) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(QuerySQL.SQL_GET_RESERVATION_LIST);
        if (rs.next()) {
            Map<String, Entity> tourInf = new HashMap<>();
            Tour tour = new Tour();
            Duration du = new Duration();
            Reservation res = new Reservation();
            Users u = new Users();
            tour.setTourName(rs.getString(Fields.TOUR_NAME));
            tour.setPrice(rs.getFloat(Fields.TOUR_PRICE));
            du.setTourBeginDate(rs.getString(Fields.BEGIN_DATE));
            du.setTourEndDate(rs.getString(Fields.END_DATE));
            res.setStatusId(rs.getInt(Fields.STATUS_ID));
            u.setId(rs.getInt(Fields.USER_ID));
            u.setLastName(rs.getString(Fields.LAST_NAME));
            u.setFirstName(rs.getString(Fields.FIRST_NAME));
            tourInf.put(Tour.class.getName(), tour);
            tourInf.put(Duration.class.getName(), du);
            tourInf.put(Reservation.class.getName(), res);
            tourInf.put(Reservation.class.getName(), u);
            return tourInf;
        }
        return null;
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

    public static boolean updateHotTour(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_HOT_TOUR);
        pr.setInt(1, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateTourName(Connection conn, String tourName, int tourId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_TOUR_NAME);
        pr.setString(1, tourName);
        pr.setInt(2, tourId);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateTourPrice(Connection conn, int price, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_TOUR_PRICE);
        pr.setInt(1, price);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateAvailableTickets(Connection conn, int availableTickets, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_TOUR_AVAILABLE_TICKETS);
        pr.setInt(1, availableTickets);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateProgramTour(Connection conn, String program, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_PROGRAM_TOUR);
        pr.setString(1, program);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateSportActivity(Connection conn, String sport, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_SPORT_ACTIVITY);
        pr.setString(1, sport);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateBeachActivity(Connection conn, String beach, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_BEACH_ACTIVITY);
        pr.setString(1, beach);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateDiscountPersent(Connection conn, int percent, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_PERCENT);
        pr.setInt(1, percent);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateDiscountStep(Connection conn, int step, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_STEP);
        pr.setInt(1, step);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateDiscountName(Connection conn, String name, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_NAME);
        pr.setString(1, name);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateDiscountDateFrom(Connection conn, String dateFrom, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_DATE_FROM);
        pr.setString(1, dateFrom);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateDiscountDateTo(Connection conn, String dateTo, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_DATE_TO);
        pr.setString(1, dateTo);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateDiscountPrice(Connection conn, int price, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_DISCOUNT_PRICE);
        pr.setInt(1, price);
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

    public static boolean updateHotelName(Connection conn, String name, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_HOTEL_NAME);
        pr.setString(1, name);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateHotelClass(Connection conn, int hotelClass, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_HOTEL_CLASS);
        pr.setInt(1, hotelClass);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateHotelSite(Connection conn, String site, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_HOTEL_SITE);
        pr.setString(1, site);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateBeginDate(Connection conn, String date, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_TOUR_BEGIN_DATE);
        pr.setString(1, date);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateEndDate(Connection conn, String date, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_TOUR_END_DATE);
        pr.setString(1, date);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateLogin(Connection conn, String login, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_LOGIN);
        pr.setString(1, login);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updatePassword(Connection conn, String password, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_PASSWORD);
        pr.setString(1, password);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateFirstName(Connection conn, String name, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_FIRST_NAME);
        pr.setString(1, name);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateLastName(Connection conn, String name, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_LAST_NAME);
        pr.setString(1, name);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateEmail(Connection conn, String email, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_EMAIL);
        pr.setString(1, email);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updatePhone(Connection conn, String phone, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_UPDATE_PHONE);
        pr.setString(1, phone);
        pr.setInt(2, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean userBlocking(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_USER_BLOCKING);
        pr.setInt(1, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean userUnBlocking(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_USER_UNBLOCKING);
        pr.setInt(1, id);
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

    public static boolean createNewManager(Connection conn, String login,
                                           String password, String firstName,
                                           String lastName,
                                           String userEmail, String userPhone) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_MANAGER);
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

    public static boolean createNewDescription(Connection conn, int typeId,
                                               String country, String programTour,
                                               String sportActiv,
                                               String beachActiv) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_DESCRIPTION);
        pr.setInt(1, typeId);
        pr.setString(2, country);
        pr.setString(3, programTour);
        pr.setString(4, sportActiv);
        pr.setString(5, beachActiv);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createNewDiscount(Connection conn, int discountPercent,
                                            int discountStep, String discountName,
                                            String discountDateFrom, String discountDateTo,
                                            int discountPrice, String dataLastChange) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_DISCOUNT);
        pr.setInt(1, discountPercent);
        pr.setInt(2, discountStep);
        pr.setString(3, discountName);
        pr.setString(4, discountDateFrom);
        pr.setString(5, discountDateTo);
        pr.setInt(5, discountPrice);
        pr.setString(5, dataLastChange);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createNewHotel(Connection conn, int hotelClass,
                                         String hotelName,
                                         String hotelSite) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_HOTEL);
        pr.setInt(1, hotelClass);
        pr.setString(2, hotelName);
        pr.setString(3, hotelSite);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createNewTour(Connection conn, String tourName,
                                        String departureCity, int hotelId, float price,
                                        int avaliableTikets, int tikets, int descId,
                                        int disId, boolean hotTour, int durId
    ) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_TOUR);
        pr.setString(1, tourName);
        pr.setString(2, departureCity);
        pr.setInt(3, hotelId);
        pr.setFloat(4, price);
        pr.setInt(5, avaliableTikets);
        pr.setInt(6, tikets);
        pr.setInt(7, descId);
        pr.setInt(8, disId);
        pr.setBoolean(9, hotTour);
        pr.setInt(10, durId);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createNewDuration(Connection conn, String beginDate,
                                            String endDate) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_DURATION);
        pr.setString(1, beginDate);
        pr.setString(2, endDate);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createNewReservation(Connection conn, int tourId, int statusId) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_ADD_NEW_RESERVATION);
        pr.setInt(1, tourId);
        pr.setInt(2, statusId);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
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

    public static boolean deleteDuration(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_DELETE_DURATION);
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

    public static boolean deleteHotel(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_DELETE_HOTEL);
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

    public static boolean deleteDescription(Connection conn, int id) throws SQLException {
        PreparedStatement pr = conn.prepareStatement(QuerySQL.SQL_DELETE_DESCRIPTION);
        pr.setInt(1, id);
        try {
            pr.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


}


