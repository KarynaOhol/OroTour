package ua.nure.ohol.SummaryTask4.security.config;


import ua.nure.ohol.SummaryTask4.db.beans.Roles;

import java.util.*;

public class SecurityConfig {

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        // Конфигурация для роли "CUSTOMER".
        List<String> urlPatterns1 = new ArrayList<String>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/tourBooking");

        mapConfig.put(String.valueOf(Roles.getRole(2)), urlPatterns1);

        // Конфигурация для роли "MANAGER".
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/managerInfo");

        mapConfig.put(String.valueOf(Roles.getRole(1)), urlPatterns2);

        // Конфигурация для роли "ADMIN".
        List<String> urlPatterns3 = new ArrayList<String>();

        urlPatterns3.add("/userInfo");
        urlPatterns3.add("/managerInfo");
        urlPatterns3.add("/addTour");
        urlPatterns3.add("/editTour");



        mapConfig.put(String.valueOf(Roles.getRole(0)), urlPatterns3);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}

