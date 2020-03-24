package ua.nure.ohol.SummaryTask4.db.beans;

public enum  Status {
    REGISTER, CANCELED, PAID;

    public static Status getStatus(Reservation res) {
        int statusId = res.getStatusId();
        return Status.values()[statusId - 1];
    }

    public String getStatusName()

    {
        return name().toLowerCase();
    }

}
