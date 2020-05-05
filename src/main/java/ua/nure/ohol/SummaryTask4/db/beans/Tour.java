package ua.nure.ohol.SummaryTask4.db.beans;
/**
 * Entity to store fields from Tour table
 */
 public class Tour extends Entity{
    private String tourName;
    private String departureCity;
    private int hotelId;
    private float price;
    private int availableTickets;
    private int amountTickets;
    private int descriptionId;
    private int discountID;
    private boolean hotTour ;
    private int durationID;
     private String tourNameRu;
     private String departureCityRu;

    public String getDepartureCity() {
        return departureCity;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public int getAmountTickets() {
        return amountTickets;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public boolean isHotTour() {
        return hotTour;
    }

    public int getDurationID() {
        return durationID;
    }

    public String getDepartureCityRu() {
        return departureCityRu;
    }

    public String getTourNameRu() {
         return tourNameRu;
     }

     public void setTourNameRu(String tourNameRu) {
         this.tourNameRu = tourNameRu;
     }

     public void setDepartureCityRu(String departureCityRu) {
         this.departureCityRu = departureCityRu;
     }

     public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void setAmountTickets(int amountTickets) {
        this.amountTickets = amountTickets;
    }

    public void setDescriptionId(int descriptionId) {
        this.descriptionId = descriptionId;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public void setHotTour(boolean hotTour) {
        this.hotTour = hotTour;
    }

    public void setDurationID(int durationID) {
        this.durationID = durationID;
    }

     @Override
     public String toString() {
         return "Tour [ tourName = " + tourName +
                 ", departureCity = " + departureCity +
                 ", hotelId = " + hotelId +
                 ", price = " + price +
                 ", availableTickets = " + availableTickets +
                 ", amountTickets = " + amountTickets +
                 ", descriptionId = " + descriptionId +
                 ", discountID = " + discountID +
                 ", hotTour = " + hotTour +
                 ", durationID = " + durationID +
                 ", getId() = " + super.getId();
     }
 }

