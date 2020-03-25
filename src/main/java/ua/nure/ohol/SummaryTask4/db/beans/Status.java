package ua.nure.ohol.SummaryTask4.db.beans;

public enum  Status {
    REGISTER, CANCELED, PAID;

    public static Status getStatus(Reservation res) {
        int statusId = res.getStatusId();
        return Status.values()[statusId];
    }

    public static Status getStatus(int statusId) {
        return Status.values()[statusId];
    }

    public String getStatusName()

    {
        return name().toLowerCase();
    }

}
