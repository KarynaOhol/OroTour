package ua.nure.ohol.SummaryTask4.db.beans;
/**
 * Entity to store fields from Reservation table
 */
public class Reservation extends Entity {
    private int tourId;
    private int statusId;

    public int getNumberOfreserv() {
        return numberOfreserv;
    }

    private int numberOfreserv;

    public void setNumberOfreserv(int numberOfreserv) {
        this.numberOfreserv = numberOfreserv;
    }
    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Reservation [ tourId = " + tourId +
                ", statusId = " + statusId +
                ", getId() = " + getId();
    }
}
