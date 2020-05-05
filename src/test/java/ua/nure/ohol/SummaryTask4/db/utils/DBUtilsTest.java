package ua.nure.ohol.SummaryTask4.db.utils;

import org.junit.Test;
import ua.nure.ohol.SummaryTask4.db.beans.Entity;
import ua.nure.ohol.SummaryTask4.db.beans.HotelClass;
import ua.nure.ohol.SummaryTask4.db.beans.HotelClassBean;
import ua.nure.ohol.SummaryTask4.db.connection.MySQLConnUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBUtilsTest {
    Connection connection;

    {
        try {
            connection = MySQLConnUtils.getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUserByLogin() {

    }

    @Test
    public void findUserByLoginPassword() {

    }

    @Test
    public void reservationListForCustumer() {
        try {
           for(Entity en: DBUtils.reservationListForCustumer(MySQLConnUtils.getMySQLConnection(), 4).values()){
               System.out.println(en);
           }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findTour() {
    }

    @Test
    public void findDescription() {
    }

    @Test
    public void findDiscount() {
    }

    @Test
    public void findDiscountLastChange() {
    }

    @Test
    public void findHotel() {
    }

    @Test
    public void findDuration() {
    }

    @Test
    public void findAllDiscount() {
    }

    @Test
    public void findDiscountIdByTourId() {
    }

    @Test
    public void findHotTour() {
    }

    @Test
    public void findAllTour() {
    }

    @Test
    public void findTourType() {
    }

    @Test
    public void sortByPriceDESC() {
    }

    @Test
    public void sortByPriceASC() {
    }

    @Test
    public void sortByAvailableTicketsDESC() {
    }

    @Test
    public void sortByAvailableTicketsASC() {
    }

    @Test
    public void sortByHotelType() {
    }

    @Test
    public void reservationList() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void updateHotTour() {
    }

    @Test
    public void updateTourName() {
    }

    @Test
    public void updateTourPrice() {
    }

    @Test
    public void updateAvailableTickets() {
    }

    @Test
    public void updateProgramTour() {
    }

    @Test
    public void updateSportActivity() {
    }

    @Test
    public void updateBeachActivity() {
    }

    @Test
    public void updateAllDiscount() {
    }

    @Test
    public void updateDiscountPersent() {
    }

    @Test
    public void updateDiscountStep() {
    }

    @Test
    public void updateDiscountName() {
    }

    @Test
    public void updateDiscountDateFrom() {
    }

    @Test
    public void updateDiscountDateTo() {
    }

    @Test
    public void updateDiscountPrice() {
    }

    @Test
    public void updateDiscountLastChange() throws SQLException {

        System.out.println(DBUtils.updateDiscountLastChange(connection, null, 1));
    }

    @Test
    public void updateHotelName() {
    }

    @Test
    public void updateHotelClass() {
    }

    @Test
    public void updateHotelSite() {
    }

    @Test
    public void updateBeginDate() {
    }

    @Test
    public void updateEndDate() {
    }

    @Test
    public void updateLogin() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void updateFirstName() {
    }

    @Test
    public void updateLastName() {
    }

    @Test
    public void updateEmail() {
    }

    @Test
    public void updatePhone() {
    }

    @Test
    public void userBlocking() {
    }

    @Test
    public void userUnBlocking() {
    }

    @Test
    public void createNewCustomer() {
    }

    @Test
    public void createNewManager() {
    }

    @Test
    public void createNewDescription() {
    }

    @Test
    public void createNewDiscount() {
    }

    @Test
    public void createNewHotel() throws SQLException {
        DBUtils.createNewHotel(connection, HotelClassBean.getHotelClassId("COMFORT"),
                "hotel", "www.23.com");
    }

    @Test
    public void createNewTour() {
    }

    @Test
    public void createNewDuration() {
    }

    @Test
    public void createNewReservation() {
    }

    @Test
    public void deleteReservation() {
    }

    @Test
    public void deleteDuration() {
    }

    @Test
    public void deleteTour() {
    }

    @Test
    public void deleteHotel() {
    }

    @Test
    public void deleteDiscount() {
    }

    @Test
    public void deleteDescription() {
    }

    @Test
    public void findUserByEmail() throws SQLException {
        System.out.println(DBUtils.findUserByEmail(connection, "manager@mail.com"));
    }

    @Test
    public void findAllUsers() {
    }

    @Test
    public void findAllTourFully() {
    }

    @Test
    public void findTourFullyByTourId() {
    }

    @Test
    public void getLastDiscountId() {
    }

    @Test
    public void updateTour() {
    }

    @Test
    public void updateHotel() {
    }

    @Test
    public void updateDescription() {
    }

    @Test
    public void updateDuration() {
    }

    @Test
    public void updateTourDiscountId() {
    }

    @Test
    public void updateUserValidation() {
    }
}