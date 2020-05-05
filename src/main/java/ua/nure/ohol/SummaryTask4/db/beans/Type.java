package ua.nure.ohol.SummaryTask4.db.beans;

public enum  Type {
    CITY_TOURS,
    CULTYRAL_THEMATIC_TOURS,
    HOLIDAY_SEASONAL_TOURS,
    INDULGENCE_LUXURY_TOURS,
    OUTDOOR_ACTIVITES,
    RELAXATION_TOURS,
    WILD_ADVENTURE_TOURS;

    public static Type getType(Description desc) {
        int typeId = desc.getTypeID();
        return Type.values()[typeId-1];
    }

    public String getTypeName() {
        return name().toUpperCase();
    }

}
