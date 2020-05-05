package ua.nure.ohol.SummaryTask4.db.utils;

import ua.nure.ohol.SummaryTask4.db.beans.Users;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Store Connection in attribute in request.
 * information exist only in request scope
 */
public class MyUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    private static final String ATT_NAME_CURRENT_LANGUAGE = "language";

    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }


    /**
     * Get Connection  stored in attribute  in request.
     *
     * @param request
     * @return
     */
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }


    public static void storeLanguage(HttpServletResponse response, String language) {
        Cookie cookie = new Cookie(ATT_NAME_CURRENT_LANGUAGE, language);
        // 7 день (Конвертированный в секунды)
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public static String getStoredLanguage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_CURRENT_LANGUAGE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    /**
     * Store logined user in  Session.
     *
     * @param session
     * @param loginedUser
     */
    public static void storeLoginedUser(HttpSession session, Users loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static Users getLoginedUser(HttpSession session) {
        return (Users) session.getAttribute("loginedUser");
    }


    /**
     * Store user information in  Cookie.
     *
     * @param response
     * @param user
     */
    public static void storeUserCookie(HttpServletResponse response, Users user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getLogin());
        // 7 день (Конвертированный в секунды)
        cookieUserName.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        return id_uri_map.get(redirectId);
    }
}