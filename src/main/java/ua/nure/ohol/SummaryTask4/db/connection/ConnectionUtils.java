package ua.nure.ohol.SummaryTask4.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * working with connection and transaction
 */
public class ConnectionUtils {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        return MySQLConnUtils.getMySQLConnection();
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}