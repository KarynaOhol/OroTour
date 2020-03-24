package ua.nure.ohol.SummaryTask4.db.beans;

public enum Roles {
    ADMIN, MANAGER, CUSTOMER;

    public static Roles getRole(Users user) {
        int roleId = user.getRoleId();
        return Roles.values()[roleId];
    }

    public static Roles getRole(int roleId) {
        return Roles.values()[roleId];
    }

    public String getRoleName() {
        return name().toLowerCase();
    }

}