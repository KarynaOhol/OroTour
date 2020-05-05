package ua.nure.ohol.SummaryTask4.db.beans;

/**
 * Wraps to simplified work with HotelClass enum
 */
public class HotelClassBean {
    public HotelClass[] getValues() {
        return HotelClass.values();
    }

    public static String getHotelClass(int hotelClassId) {
        return HotelClass.values()[hotelClassId - 1].name();
    }

    public static int getHotelClassId(String hotelclass) {
        HotelClass[] values = HotelClass.values();

        for (int i = 0; i < values.length; i++) {
            if (values[i].getHotelClassName().equals(hotelclass)) {
                return i + 1;
            }
        }

        return 0;
    }
}
