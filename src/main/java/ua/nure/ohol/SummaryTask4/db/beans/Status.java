package ua.nure.ohol.SummaryTask4.db.beans;

public enum Status {
    REGISTER,PAID,CANCELED;

    public static Status getStatus(Reservation res) {
        int statusId = res.getStatusId();
        return Status.values()[statusId];
    }

    public static int getStatusId(String statusName) {
        Status[] values = Status.values();

        for (int i = 0; i < values.length; i++) {
            if (values[i].getStatusName().equals(statusName)) {
                return i;
            }
        }

        return 0;
    }

    public static Status getStatus(int statusId) {
        return Status.values()[statusId];
    }

    public String getStatusName() {
        return name();
    }

}
