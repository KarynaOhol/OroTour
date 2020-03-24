package ua.nure.ohol.SummaryTask4.db.beans;

public class Discount extends Entity {
    private int discountPercent;
    private String discountName;
    private String discountDateFrom;
    private String discountDateTo;
    private float discountPrice;
    private String discountLastChangeData;
    private int discountStep;

    public int getDiscountStep() {
        return discountStep;
    }

    public void setDiscountStep(int discountStep) {
        this.discountStep = discountStep;
    }

    public String getDiscountLastChangeData() {
        return discountLastChangeData;
    }

    public void setDiscountLastChangeData(String discountLastChangeData) {
        this.discountLastChangeData = discountLastChangeData;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountDateFrom() {
        return discountDateFrom;
    }

    public void setDiscountDateFrom(String discountDateFrom) {
        this.discountDateFrom = discountDateFrom;
    }

    public String getDiscountDateTo() {
        return discountDateTo;
    }

    public void setDiscountDateTo(String discountDateTo) {
        this.discountDateTo = discountDateTo;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }
}
