package ua.nure.ohol.SummaryTask4.db.beans;

public class Duration extends Entity {
    private String tourBeginDate;
    private String TourEndDate;

    public String getTourBeginDate() {
        return tourBeginDate;
    }

    public void setTourBeginDate(String tourBeginDate) {
        this.tourBeginDate = tourBeginDate;
    }

    public String getTourEndDate() {
        return TourEndDate;
    }

    public void setTourEndDate(String tourEndDate) {
        TourEndDate = tourEndDate;
    }

    @Override
    public String toString() {
        return "Duration [ tourBeginDate = " + tourBeginDate +
                ", TourEndDate = " + TourEndDate +
                ", getId() = " + super.getId();
    }
}
