package ua.nure.ohol.SummaryTask4.db.beans;

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

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public int getHotelId() {
        return hotelId;
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

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getAmountTickets() {
        return amountTickets;
    }

    public void setAmountTickets(int amountTickets) {
        this.amountTickets = amountTickets;
    }

    public int getDescriptionId() {
        return descriptionId;
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

    public boolean isHotTour() {
        return hotTour;
    }

    public void setHotTour(boolean hotTour) {
        this.hotTour = hotTour;
    }

    public int getDurationID() {
        return durationID;
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

