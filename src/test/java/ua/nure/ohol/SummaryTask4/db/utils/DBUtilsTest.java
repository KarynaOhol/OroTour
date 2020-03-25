package ua.nure.ohol.SummaryTask4.db.utils;

import org.junit.Test;
import ua.nure.ohol.SummaryTask4.db.beans.Entity;
import ua.nure.ohol.SummaryTask4.db.connection.MySQLConnUtils;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBUtilsTest {

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
}