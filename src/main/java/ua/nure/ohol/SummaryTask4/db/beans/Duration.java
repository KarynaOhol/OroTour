package ua.nure.ohol.SummaryTask4.db.beans;
/**
 * Entity to store fields from duration table
 */
public class Duration extends Entity {
    private String tourBeginDate;
    private String tourEndDate;
    private int durationInDays;

    public String getTourBeginDate() {
        return tourBeginDate;
    }

    public String getTourEndDate() {
        return tourEndDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }


    public void setTourBeginDate(String tourBeginDate) {
        this.tourBeginDate = tourBeginDate;
    }


    public void setTourEndDate(String tourEndDate) {
        this.tourEndDate = tourEndDate;
    }

    @Override
    public String toString() {
        return "Duration [ tourBeginDate = " + tourBeginDate +
                ", tourEndDate = " + tourEndDate +
                ", durationInDays = " + durationInDays +
                ", getId() = " + super.getId();
    }
}
