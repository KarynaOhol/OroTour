package ua.nure.ohol.SummaryTask4.db.beans;

public class Duration extends Entity {
    private String tourBeginDate;
    private String TourEndDate;
    private int durationInDays;

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

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
                ", durationInDays = " + durationInDays +
                ", getId() = " + super.getId();
    }
}
