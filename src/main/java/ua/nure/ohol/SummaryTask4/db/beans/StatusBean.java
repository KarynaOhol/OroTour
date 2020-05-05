package ua.nure.ohol.SummaryTask4.db.beans;

/**
 * Wraps to simplified work with HotelClass enum
 */
public class StatusBean {
    public Status[] getValues() {
        return Status.values();
    }

    public static String getStatus(int statusId) {
        return Status.values()[statusId].toString();
    }

    public static int getStatus(String status) {
        Status[] values = Status.values();

        for (int i = 0; i < values.length; i++) {
            if (values[i].getStatusName().equals(status)) {
                return i + 1;
            }
        }

        return 0;
    }
}
