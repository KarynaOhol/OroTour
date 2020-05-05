package ua.nure.ohol.SummaryTask4.db.beans;

/**
 * representation set of hotel classes
 */
public enum HotelClass {
    TOURIST, STANDARD, COMFORT, FIRST_CLASS, LUXURY;

    public static HotelClass getHotelClass(Hotel hotel) {
        int hotelClassId = hotel.getHotelClass();
        return HotelClass.values()[hotelClassId - 1];
    }

    public static HotelClass getHotelClass(int hotelClassId) {
        return HotelClass.values()[hotelClassId - 1];
    }

    public String getHotelClassName() {
        return name().toUpperCase();
    }
}
