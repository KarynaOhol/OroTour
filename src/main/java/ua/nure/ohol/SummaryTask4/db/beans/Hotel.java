package ua.nure.ohol.SummaryTask4.db.beans;
/**
 * Entity to store fields from Hotel table
 */
public class Hotel extends Entity {
    private String hotelName;
    private int hotelClass;
    private String hotelSite;

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelSite() {
        return hotelSite;
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


    public void setHotelSite(String hotelSite) {
        this.hotelSite = hotelSite;
    }

    @Override
    public String toString() {
        return "Hotel [" +
                "hotelName='" + hotelName + '\'' +
                ", hotelClass=" + hotelClass +
                ", hotelSite='" + hotelSite + '\'' +
                ", id=" + getId() + ']';
    }
}
