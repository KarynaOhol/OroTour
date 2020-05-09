package ua.nure.ohol.SummaryTask4.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * connection to  MySql Database
 */
public class MySQLConnUtils {

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {
//        String hostName = "localhost";
//        String dbName = "touragency";
//        String userName = "root";
//        String password = "admin";
        String hostName = "eu-cdbr-west-03.cleardb.net";
        String dbName = "heroku_e0c3247c2851965";
        String userName = "b561bb3b7a39dd";
        String password = "50f19ca6";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        String connectionURL =
                "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&serverTimezone=UTC&reconnect=true";

        return DriverManager.getConnection(connectionURL, userName,
                password);
    }
}