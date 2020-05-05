package ua.nure.ohol.SummaryTask4.db.beans;
/**
 * Entity to store fields from discount table
 */
public class Discount extends Entity {
    private int discountPercent;
    private String discountName;
    private String discountNameRu;
    private String discountDateFrom;
    private String discountDateTo;
    private float discountPrice;
    private String discountLastChangeData;
    private int discountStep;

    public String getDiscountName() {
        return discountName;
    }

    public String getDiscountNameRu() {
        return discountNameRu;
    }

    public void setDiscountNameRu(String discountNameRu) {
        this.discountNameRu = discountNameRu;
    }


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

    @Override
    public String toString() {
        return "Discount [ discountStep = " + discountStep +
                ", discountLastChangeData = " + discountLastChangeData +
                ", discountPrice = " + discountPrice +
                ", discountDateTo = " + discountDateTo +
                ", discountDateFrom = " + discountDateFrom +
                ", discountName = " + discountName +
                ", discountPercent = " + discountPercent +
                ", getId() = " + super.getId();
    }
}
