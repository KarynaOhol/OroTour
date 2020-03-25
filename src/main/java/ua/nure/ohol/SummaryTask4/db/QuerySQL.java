package ua.nure.ohol.SummaryTask4.db;

public final class QuerySQL {
    //query for finding general information about tour
    public static final String SQL_FIND_TOUR = "SELECT* FROM  TOUR WHERE tour_id= ?;";
    public static final String SQL_FIND_DESCRIPTION = "SELECT* FROM  description WHERE description_id= ?;";
    public static final String SQL_FIND_DISCOUNT = "SELECT* FROM  discount WHERE discount_id= ?;";
    public static final String SQL_FIND_HOTEL = "SELECT* FROM  hotel WHERE hotel_id= ?;";
    public static final String SQL_FIND_DURATION = "SELECT* FROM  tour_duration WHERE tour_duration_id= ?;";

    //query for criterion search
    public static String SQL_FIND_HOT_TOUR = "SELECT * FROM tour t " +
            " JOIN tour_duration td on" +
            " t.tour_duration_id = td.tour_duration_id WHERE hot_tour=true;";
    public static final String SQL_FIND_TOURTYPE =
            "SELECT * FROM Tour t JOIN description d On t.description_id = d.description_id" +
                    "    JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id WHERE d.type_id =?;";
    public static final String SQL_SORT_TOURS_BY_PRICE_DESC =
            "SELECT * FROM Tour t JOIN tour_duration td on " +
                    "t.tour_duration_id = td.tour_duration_id order by t.price DESC ;";

    public static final String SQL_SORT_TOURS_BY_PRICE_ASC =
            "SELECT * FROM Tour t JOIN tour_duration td on" +
                    " t.tour_duration_id = td.tour_duration_id order by t.price ASC;";

    public static final String SQL_SORT_TOURS_BY_AVTICKETS_DESC =
            "SELECT * FROM Tour t JOIN tour_duration td on t.tour_duration_id = " +
                    "td.tour_duration_id order by t.price DESC ;";

    public static final String SQL_SORT_TOURS_BY_AVTICKETS_ASC =
            "SELECT * FROM Tour t JOIN tour_duration td on t.tour_duration_id = " +
                    "td.tour_duration_id order by t.price ASC;";

    public static final String SQL_TOURS_BY_HOTEL_TYPE =
            "SELECT * FROM Tour t JOIN hotel h ON t.hotel_id = h.hotel_id " +
                    "JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id  WHERE hotel_class =?;";

    //customer
    public static final String SQL_GET_RESERVATION_LIST_FOR_CUSTOMER =
            "SELECT tour_name,TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration,tour_reservation.status_id, price,tour_begin_date,tour_end_date\n" +
                    "                    FROM Tour t JOIN tour_reservation ON t.tour_id = tour_reservation.tour_id\n" +
                    "                    JOIN reservation_list rl on tour_reservation.reservation_id = rl.reservation_id\n" +
                    "                    JOIN tour_duration d ON t.tour_id =d.tour_duration_id\n" +
                    "                    JOIN tour_status s ON tour_reservation.status_id = s.status_id WHERE user_id =?;";

    public static String SQL_GET_RESERVATION_LIST =
            "SELECT * FROM Tour t JOIN tour_reservation ON t.tour_id = tour_reservation.tour_id " +
                    "JOIN reservation_list rl on tour_reservation.reservation_id = rl.reservation_id " +
                    "JOIN tour_duration d ON t.tour_id =d.tour_duration_id " +
                    "JOIN tour_status s ON tour_reservation.status_id = s.status_id " +
                    "JOIN users u ON rl.user_id = u.user_id;";

    public static String SQL_UPDATE_RESERVATION_STATUS =
            "UPDATE tour_reservation SET status_id =? WHERE reservation_id=?;";

    public static String SQL_UPDATE_HOT_TOUR = "UPDATE TOUR SET hot_tour = true WHERE tour_id=?;";


    public static String SQL_UPDATE_TOUR_NAME = "UPDATE TOUR SET tour_name=? WHERE tour_id =?";
    public static String SQL_UPDATE_TOUR_PRICE = "UPDATE TOUR SET price=? WHERE tour_id =?";
    public static String SQL_UPDATE_TOUR_AVAILABLE_TICKETS =
            "UPDATE TOUR SET amount_available_tickets=? WHERE tour_id =?";

    public static String SQL_UPDATE_PROGRAM_TOUR = "UPDATE description SET program_tour =? WHERE description_id=? ";
    public static String SQL_UPDATE_SPORT_ACTIVITY = "UPDATE description SET sport_activity =? WHERE description_id=? ";
    public static String SQL_UPDATE_BEACH_ACTIVITY = "UPDATE description SET beach_activity =? WHERE description_id=? ";

    public static String SQL_UPDATE_DISCOUNT_PERCENT = "UPDATE discount SET discount_percent =? WHERE discount_id=? ";
    public static String SQL_UPDATE_DISCOUNT_STEP = "UPDATE discount SET discount_step =? WHERE discount_id=? ";
    public static String SQL_UPDATE_DISCOUNT_NAME = "UPDATE discount SET discount_name =? WHERE discount_id=? ";
    public static String SQL_UPDATE_DISCOUNT_DATE_FROM =
            "UPDATE discount SET discount_date_from =? WHERE discount_id=? ";
    public static String SQL_UPDATE_DISCOUNT_DATE_TO = "UPDATE discount SET discount_date_to =? WHERE discount_id=? ";
    public static String SQL_UPDATE_DISCOUNT_PRICE = "UPDATE discount SET discount_price =? WHERE discount_id=? ";
    public static String SQL_UPDATE_DISCOUNT_DATA_LAST_CHANGE =
            "UPDATE discount SET data_last_change =? WHERE discount_id=? ";

    public static String SQL_UPDATE_HOTEL_NAME = "UPDATE hotel SET hotel_name =? WHERE hotel_id=? ";
    public static String SQL_UPDATE_HOTEL_CLASS = "UPDATE hotel SET hotel_class =? WHERE hotel_id=? ";
    public static String SQL_UPDATE_HOTEL_SITE = "UPDATE hotel SET hotel_site =? WHERE hotel_id=? ";

    public static String SQL_UPDATE_TOUR_BEGIN_DATE =
            "UPDATE tour_duration SET tour_begin_date =? WHERE tour_duration_id=? ";
    public static String SQL_UPDATE_TOUR_END_DATE =
            "UPDATE tour_duration SET tour_end_date =? WHERE tour_duration_id=? ";

    public static String SQL_UPDATE_LOGIN = "UPDATE users SET login =? WHERE user_id=? ";
    public static String SQL_UPDATE_PASSWORD = "UPDATE users SET password =? WHERE user_id=? ";
    public static String SQL_UPDATE_FIRST_NAME = "UPDATE users SET first_name =? WHERE user_id=? ";
    public static String SQL_UPDATE_LAST_NAME = "UPDATE users SET last_name =? WHERE user_id=? ";
    public static String SQL_UPDATE_EMAIL = "UPDATE users SET user_email =? WHERE user_id=? ";
    public static String SQL_UPDATE_PHONE = "UPDATE users SET user_phone =? WHERE user_id=? ";

    public static String SQL_USER_BLOCKING = "UPDATE users SET valid_user=FALSE WHERE user_id=?;";
    public static String SQL_USER_UNBLOCKING = "UPDATE users SET valid_user=TRUE WHERE user_id=?;";


    public static String SQL_FIND_USER_BY_LOGIN = "SELECT * from users WHERE login =?;";
    public static String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * from users WHERE login =? AND password=?;";
    public static String SQL_ADD_NEW_CUSTOMER = "INSERT INTO users values (default,?,?,?,?,2,?,?,true);";
    public static String SQL_ADD_NEW_MANAGER = "INSERT INTO users values (default,?,?,?,?,1,?,?true);";


    public static String SQL_ADD_NEW_DESCRIPTION = "INSERT INTO description values (default,?,?,?,?,?);";
    public static String SQL_ADD_NEW_DISCOUNT = "INSERT INTO discount values (default,?,?,?,?,?,?,?);";
    public static String SQL_ADD_NEW_HOTEL = "INSERT INTO hotel values (default,?,?,?)";
    public static String SQL_ADD_NEW_TOUR = "INSERT INTO tour values (default,?,?,?,?,?,?,?,?,?,?)";
    public static String SQL_ADD_NEW_DURATION = "INSERT INTO tour_duration values (default,?,?)";
    public static String SQL_ADD_NEW_RESERVATION = "INSERT INTO tour_reservation values (default,?,?)";

    public static String SQL_DELETE_DESCRIPTION = "DELETE  FROM description WHERE description_id=?;";
    public static String SQL_DELETE_DISCOUNT = "DELETE  FROM discount WHERE discount_id=?;";
    public static String SQL_DELETE_HOTEL = "DELETE  FROM hotel WHERE hotel_id=?;";
    public static String SQL_DELETE_TOUR = "DELETE  FROM tour WHERE tour_id=?;";
    public static String SQL_DELETE_DURATION = "DELETE  FROM tour_duration WHERE tour_duration_id=?;";
    public static String SQL_DELETE_RESERVATION = "DELETE  FROM tour_reservation WHERE reservation_id=?;";


}
