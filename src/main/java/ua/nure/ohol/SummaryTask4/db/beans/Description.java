package ua.nure.ohol.SummaryTask4.db.beans;

public class Description extends Entity {
    private int typeID;
    private String country;
    private String programTour;
    private String sportActivity;
    private String beachActivity;

    public Description() {
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProgramTour() {
        return programTour;
    }

    public void setProgramTour(String programTour) {
        this.programTour = programTour;
    }

    public String getSportActivity() {
        return sportActivity;
    }

    public void setSportActivity(String sportActivity) {
        this.sportActivity = sportActivity;
    }

    public String getBeachActivity() {
        return beachActivity;
    }

    public void setBeachActivity(String beachActivity) {
        this.beachActivity = beachActivity;
    }
}
