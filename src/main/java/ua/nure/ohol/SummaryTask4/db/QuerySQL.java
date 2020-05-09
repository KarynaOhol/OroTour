package ua.nure.ohol.SummaryTask4.db;

public final class QuerySQL {
    public static final String SQL_FIND_DISCOUNT = "SELECT* FROM  discount WHERE discount_id= ?;";


    public static final String SQL_FIND_DISCOUNT_BY_TOUR = "SELECT* FROM  discount " +
            "Join tour t on discount.discount_id = t.discount_id WHERE tour_id= ?;";


    public static final String SQL_FIND_DISCOUNT_BY_TOUR_ID = "SELECT* FROM tour JOIN discount d " +
            "on tour.discount_id = d.discount_id WHERE tour_id= ?;";

    public static final String SQL_FIND_ALL_DISCOUNT = "SELECT discount_percent," +
            "discount_id," +
            "discount_name," +
            "discount_name_ru," +
            "discount_step," +
            "DATE(discount_date_from) discount_date_from," +
            "DATE(discount_date_to) discount_date_to," +
            "discount_price," +
            "DATE(data_last_change) data_last_change from  discount;";

    public static final String SQL_GET_RESERVATION_LIST_FOR_CUSTOMER =
            "SELECT t.tour_name,t.tour_name_ru,t.tour_id,TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration,\n" +
                    "       tr.status_id, t.price,td.tour_begin_date,td.tour_end_date, COUNT(t.tour_id)" +
                    " numberOfreserv, rl.reservation_id\n" +
                    "from reservation_list rl" +
                    "         JOIN users u on rl.user_id = u.user_id" +
                    "         join tour_reservation tr on rl.reservation_id = tr.reservation_id" +
                    "         join tour t on tr.tour_id = t.tour_id\n" +
                    "         join tour_duration td on t.tour_duration_id = td.tour_duration_id\n" +
                    "WHERE  u.user_id=? GROUP BY t.tour_id;";

    public static final String SQL_GET_RESERVATION_LIST =
            "SELECT *" +
                    "from reservation_list rl\n" +
                    "         JOIN users u on rl.user_id = u.user_id\n" +
                    "         join tour_reservation tr on rl.reservation_id = tr.reservation_id\n" +
                    "         join tour t on tr.tour_id = t.tour_id " +
                    "join tour_duration td on t.tour_duration_id = td.tour_duration_id";

    public static final String SQL_UPDATE_RESERVATION_STATUS =
            "UPDATE tour_reservation SET status_id =? WHERE reservation_id=?;";

    public static final String SQL_UPDATE_HOT_TOUR = "UPDATE TOUR SET hot_tour = ? WHERE tour_id=?;";

    public static final String SQL_UPDATE_TOUR = "UPDATE tour SET tour_name=?,tour_name_ru=?,departure_city=?," +
            "            departure_city_ru=?,hotel_id=?,price=?,amount_available_tickets=?,amount_of_tickets=?," +
            "description_id=?," +
            "            tour_duration_id=? WHERE tour_id =?;";

    public static final String SQL_UPDATE_HOTEL = "UPDATE hotel SET hotel_name=?,hotel_class=?,hotel_site=? " +
            "WHERE hotel_id=?";

    public static final String SQL_UPDATE_DESCRIPTION = "UPDATE description SET " +
            "type_id=?,\n" +
            "country=?\n," +
            "photo=?,\n" +
            "program_tour=?,\n" +
            "beach_activity=?,\n" +
            "sport_activity=?,country_ru=?,program_tour_ru=?,beach_activity_ru=?,sport_activity_ru=?" +
            " WHERE description_id=?";

    public static final String SQL_UPDATE_DURATION = "UPDATE tour_duration SET tour_end_date=?,\n" +
            "tour_begin_date=?\n" +
            " WHERE tour_duration_id=?";


    public static final String SQL_UPDATE_DISCOUNT = "UPDATE discount SET discount_percent =?,discount_step =?," +
            "discount_name =?,discount_name_ru =?,discount_date_from =?,discount_date_to =? WHERE discount_id=?";
    public static final String SQL_UPDATE_DISCOUNT_PRICE = "UPDATE discount SET discount_price =? WHERE discount_id=? ";
    public static final String SQL_UPDATE_DISCOUNT_DATA_LAST_CHANGE =
            "UPDATE discount SET data_last_change =? WHERE discount_id=? ";
    public static final String SQL_UPDATE_TOUR_DISCOUNT_ID =
            "UPDATE tour SET discount_id = ? WHERE tour_id = ?;";
    public static final String SQL_USER_VALIDATION = "UPDATE users SET valid_user=? WHERE user_id=?;";


    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * from users WHERE login =?;";
    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT * from users WHERE user_email =?;";
    public static final String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * from users WHERE login =? AND password=?;";
    public static final String SQL_FIND_ALL_USERS = "SELECT * from users JOIN roles r on users.role_id = r.role_id";


    public static final String SQL_ADD_NEW_CUSTOMER = "INSERT INTO users values (default,?,?,?,?,2,?,?,true);";


    public static final String SQL_ADD_NEW_DESCRIPTION = "INSERT INTO description values (default,?,?,?,?,?,?,?,?,?,?);";
    public static final String SQL_ADD_NEW_DISCOUNT = "INSERT INTO discount values (default,?,?,?,?,?,?,?,?);";
    public static final String SQL_ADD_NEW_HOTEL = "INSERT INTO hotel values (default,?,?,?)";
    public static final String SQL_ADD_NEW_TOUR =
            "INSERT INTO tour(tour_id,tour_name, departure_city, hotel_id, price, amount_available_tickets, " +
                    "amount_of_tickets, description_id, hot_tour, tour_duration_id,tour_name_ru,departure_city_ru) " +
                    "values (default,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SQL_ADD_NEW_DURATION = "INSERT INTO tour_duration values (default,?,?)";
    public static final String SQL_ADD_NEW_RESERVATION = "INSERT INTO tour_reservation values (default,?,?)";

    public static final String SQL_ADD_NEW_RESERVATION_LIST = "INSERT INTO reservation_list values (?,?)";

    public static final String SQL_FIND_ALL_USER_RESERVATION = "SELECT  tr.reservation_id FROM tour_reservation tr " +
            "JOIN reservation_list rl on tr.reservation_id = rl.reservation_id " +
            "WHERE user_id=? And tour_id=?";

    public static final String SQL_DELETE_DISCOUNT = "DELETE  FROM discount WHERE discount_id=?;";
    public static final String SQL_DELETE_TOUR = "DELETE  FROM tour WHERE tour_id=?;";
    public static final String SQL_DELETE_RESERVATION = "DELETE  FROM tour_reservation WHERE reservation_id=?;";


    public static final String SQL_FIND_All_TOUR = "SELECT * FROM tour t JOIN tour_duration td on " +
            "t.tour_duration_id = td.tour_duration_id ";

    public static final String SQL_FIND_All_TOUR_HOT_IN_TOP =
            "SELECT * ,TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
                    "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
                    "join description d on t.description_id = d.description_id " +
                    "order by hot_tour DESC ";
//    _________________________________________________________________________________________________________________________________________________

    public static final String SQL_FIND_All_TOUR_BY_TYPE = "SELECT * ,TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) " +
            "duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id" +
            " WHERE type_id=?;";

    public static final String SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id " +
            "WHERE type_id=? and hotel_class=?;";


    public static final String SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS_BY_AVALIABLE_TICKETS = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id " +
            "WHERE type_id=? and hotel_class=? and t.amount_available_tickets>=?;";

    public static final String SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS_BY_AVALIABLE_TICKETS_BY_PRICE_DIF = "SELECT * " +
            ",TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id " +
            "WHERE type_id=? and hotel_class=? and t.amount_available_tickets>=? and t.price >=? AND t.price<=?;";


    public static final String SQL_FIND_All_TOUR_BY_HOTEL_CLASS = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id " +
            "JOIN hotel h On h.hotel_id=t.hotel_id " +
            " WHERE hotel_class=?";
    public static final String SQL_FIND_All_TOUR_BY_HOTEL_CLASS_BY_AVALIABLE_TICKETS = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id " +
            "JOIN hotel h On h.hotel_id=t.hotel_id " +
            " WHERE hotel_class=? and t.amount_available_tickets>=?";
    public static final String SQL_FIND_All_TOUR_BY_HOTEL_CLASS_AVALIABLE_TICKETS_BY_PRICE_DIF = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id " +
            "JOIN hotel h On h.hotel_id=t.hotel_id " +
            " WHERE hotel_class=? and t.amount_available_tickets>=? and t.price >=? AND t.price<=?";

    public static final String SQL_FIND_All_TOUR_BY_AVALIABLE_TICKETS = "SELECT * ,TIMESTAMPDIFF" +
            "(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id " +
            "JOIN hotel h On h.hotel_id=t.hotel_id " +
            " WHERE t.amount_available_tickets>=?";

    public static final String SQL_FIND_All_TOUR_BY_AVALIABLE_TICKETS_BY_PRICE_DIF = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id " +
            "JOIN hotel h On h.hotel_id=t.hotel_id " +
            " WHERE t.amount_available_tickets>=? and t.price >=? AND t.price<=?";

    public static final String SQL_FIND_All_TOUR_BY_PRICE_DIF = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id " +
            "JOIN hotel h On h.hotel_id=t.hotel_id " +
            " WHERE t.price >=? AND t.price<=?";

    public static final String SQL_FIND_All_TOUR_BY_HOTEL_CLASS_BY_PRICE_DIF = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id " +
            "JOIN hotel h On h.hotel_id=t.hotel_id " +
            " WHERE hotel_class=? and t.price >=? AND t.price<=?";

    public static final String SQL_FIND_All_TOUR_BY_TYPE_BY_PRICE_DIF = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id WHERE type_id=? and t.price >=? AND t.price<=?;";

    public static final String SQL_FIND_All_TOUR_BY_TYPE_BY_HOTEL_CLASS_BY_PRICE_DIF = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id WHERE type_id=? and hotel_class=?  and t.price >=? AND t.price<=?;";

    public static final String SQL_FIND_All_TOUR_BY_TYPE_BY_AVALIABLE_TICKETS_BY_PRICE_DIF = "SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id" +
            " WHERE type_id=? and t.amount_available_tickets>=? and t.price >=? AND t.price<=?;";


    public static final String SQL_FIND_All_TOUR_BY_TYPE_BY_AVALIABLE_TICKETS="SELECT * ," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
            "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
            "join description d on t.description_id = d.description_id JOIN hotel h On h.hotel_id=t.hotel_id " +
            "WHERE  type_id=?  and t.amount_available_tickets>=?";



    //____________________________________________________________________________________________________________________________________________________________
    public static final String SQL_FIND_All_TOUR_HOT =
            "SELECT * ,TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration " +
                    "FROM tour t JOIN tour_duration td on t.tour_duration_id = td.tour_duration_id " +
                    "join description d on t.description_id = d.description_id " +
                    "WHERE hot_tour=true";

    public static final String SQL_FIND_All_TOUR_FULLY = "SELECT * FROM tour t JOIN tour_duration td on " +
            "t.tour_duration_id = td.tour_duration_id" +
            " JOIN hotel h on t.hotel_id = h.hotel_id " +
            "JOIN description d on t.description_id = d.description_id;";

    public static final String SQL_FIND_TOUR_FULLY_BY_TOUR_ID = "SELECT t.tour_id,t.tour_name,departure_city," +
            "t.hotel_id,price,amount_available_tickets,amount_of_tickets,t.description_id,t.discount_id," +
            "hot_tour,t.tour_duration_id,tour_name_ru,departure_city_ru," +
            "type_id,country,photo,program_tour,sport_activity,beach_activity,country_ru,program_tour_ru" +
            ",sport_activity_ru,beach_activity_ru,hotel_name,hotel_class," +
            "hotel_site,DATE(tour_begin_date) tour_begin_date,DATE(tour_end_date) tour_end_date," +
            "TIMESTAMPDIFF(DAY,tour_begin_date,tour_end_date) duration" +
            " FROM tour t JOIN tour_duration td on " +
            "t.tour_duration_id = td.tour_duration_id" +
            " JOIN hotel h on t.hotel_id = h.hotel_id " +
            "JOIN description d on t.description_id = d.description_id " +
            " WHERE tour_id = ?;";

    public static final String SQL_FIND_LAST_DISCOUNT = "  select * from discount ORDER BY discount_id DESC  limit 1;";


    public static final String SQL_FIND_USERS_RESERV="SELECT u.first_name,u.last_name, SUM(price) total FROM reservation_list rl JOIN tour_reservation tr " +
            "on rl.reservation_id = tr.reservation_id JOIN tour t on tr.tour_id = t.tour_id JOIN users u on rl.user_id = u.user_id GROUP BY rl.user_id";

    public static final String SQL_FIND_TOP_USERS ="SELECT u.first_name,u.last_name, SUM(price) total FROM reservation_list rl JOIN tour_reservation tr " +
            "on rl.reservation_id = tr.reservation_id JOIN tour t on " +
            "tr.tour_id = t.tour_id  Join users u on rl.user_id = u.user_id GROUP BY rl.user_id ORDER BY total DESC LIMIT 3";
}


