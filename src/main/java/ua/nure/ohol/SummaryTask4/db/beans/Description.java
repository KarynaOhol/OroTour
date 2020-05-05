package ua.nure.ohol.SummaryTask4.db.beans;


/**
 * Entity to store fields from description table
 */
public class Description extends Entity {
    private int typeID;
    private String country;
    private String countryRu;
    private String programTour;
    private String programTourRu;
    private String sportActivity;
    private String beachActivity;
    private String sportActivityRu;
    private String beachActivityRu;
    private byte[] photo;
    private String base64image;

    public String getCountryRu() {
        return countryRu;
    }

    public String getProgramTour() {
        return programTour;
    }

    public String getProgramTourRu() {
        return programTourRu;
    }

    public String getSportActivity() {
        return sportActivity;
    }

    public String getBeachActivity() {
        return beachActivity;
    }

    public String getSportActivityRu() {
        return sportActivityRu;
    }

    public String getBeachActivityRu() {
        return beachActivityRu;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getBase64image() {
        return base64image;
    }

    public Description() {
    }

    public void setCountryRu(String countryRu) {
        this.countryRu = countryRu;
    }


    public void setProgramTourRu(String programTourRu) {
        this.programTourRu = programTourRu;
    }

    public void setSportActivityRu(String sportActivityRu) {
        this.sportActivityRu = sportActivityRu;
    }


    public void setBeachActivityRu(String beachActivityRu) {
        this.beachActivityRu = beachActivityRu;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public void setProgramTour(String programTour) {
        this.programTour = programTour;
    }

    public void setSportActivity(String sportActivity) {
        this.sportActivity = sportActivity;
    }

    public void setBeachActivity(String beachActivity) {
        this.beachActivity = beachActivity;
    }

    @Override
    public String toString() {
        return "Description{" +
                "typeID=" + typeID +
                ", country='" + country + '\'' +
                ", programTour='" + programTour + '\'' +
                ", sportActivity='" + sportActivity + '\'' +
                ", beachActivity='" + beachActivity + '\'' +
                ", photo=" + photo +
                ", id=" + getId() + '}';
    }
}
