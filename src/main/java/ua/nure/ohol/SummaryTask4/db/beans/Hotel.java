package ua.nure.ohol.SummaryTask4.db.beans;

public class Hotel extends Entity {
    private String hotelName;
    private int hotelClass;
    private String hotelSite;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelClass() {
        return hotelClass;
    }

    public void setHotelClass(int hotelClass) {
        this.hotelClass = hotelClass;
    }

    public String getHotelSite() {
        return hotelSite;
    }

    public void setHotelSite(String hotelSite) {
        this.hotelSite = hotelSite;
    }
}
