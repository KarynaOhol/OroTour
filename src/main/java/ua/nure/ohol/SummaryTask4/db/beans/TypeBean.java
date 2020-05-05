package ua.nure.ohol.SummaryTask4.db.beans;

/**
 * Wraps to simplified work with Type enum
 */
public class TypeBean {

    public Type[] getValues() {
        return Type.values();
    }

    public static String getType(int typeId) {
        return Type.values()[typeId - 1].toString();
    }

    public static int getType(String type) {
        Type[] values = Type.values();

        for (int i = 0; i < values.length; i++) {
            if (values[i].getTypeName().equals(type)) {
                return i + 1;
            }
        }

        return 0;
    }
}