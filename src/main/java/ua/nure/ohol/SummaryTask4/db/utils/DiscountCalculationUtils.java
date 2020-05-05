package ua.nure.ohol.SummaryTask4.db.utils;

import ua.nure.ohol.SummaryTask4.db.beans.Discount;
import ua.nure.ohol.SummaryTask4.db.beans.Entity;
import ua.nure.ohol.SummaryTask4.db.beans.Tour;
import ua.nure.ohol.SummaryTask4.db.connection.MySQLConnUtils;
import ua.nure.ohol.SummaryTask4.exception.DiscountCalculationException;
import ua.nure.ohol.SummaryTask4.exception.Message;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculate and store discount fields
 */
public class DiscountCalculationUtils {
    public static Map<Tour, Discount> getTourDiscountMap(Connection con) {

        List<Discount> discountList = null;
        Map<Tour, Discount> tourDiscount = new LinkedHashMap<>();
        Map<String, Entity> hotTourInfo = null;

        try {
            hotTourInfo = DBUtils.findAllTour(con);
            discountList = DBUtils.findAllDiscount(con);
        } catch (SQLException s) {
            s.printStackTrace();
        }

        for (int i = 1; i <= hotTourInfo.size() / 2; i++) {
            Tour tour = (Tour) hotTourInfo.get("Tour" + i);
            for (Discount d :
                    discountList) {
                if (d.getId() == tour.getDiscountID()) {
                    tourDiscount.put(tour, d);
                }
            }

            if (!tourDiscount.containsKey(tour)) {
                tourDiscount.put(tour, null);
            }
        }

        return tourDiscount;
    }

    /** calculate discount price for tour by parameters
     * @param connection
     * @param tour
     * @return
     * @throws DiscountCalculationException
     */
    public static float calcPrice(Connection connection, Tour tour) throws DiscountCalculationException {
        Discount d = null;
        try {
            d = DBUtils.findDiscount(connection, tour.getDiscountID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (d == null) {
            throw new IllegalArgumentException("No discount for particular tour");
        } else {
            double maxPriceWithDiscount = tour.getPrice() - (tour.getPrice() * d.getDiscountPercent() * 0.01);
            double currentPrice = (tour.getPrice() * d.getDiscountStep() * 0.01);
            if (d.getDiscountPrice() != 0) {
                currentPrice = d.getDiscountPrice() - currentPrice;
            } else {
                currentPrice = tour.getPrice() - currentPrice;
            }
            if (currentPrice > maxPriceWithDiscount) {
                return (float) currentPrice;
            } else {
                throw new DiscountCalculationException(Message.ERROR_ACHIEVE_MAX_PRICE);
            }
        }
    }

    /** Checks day for discount recalcularion
     * @param discount
     * @return
     */
    public static boolean checkDateFromDiscount(Discount discount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowDate = LocalDateTime.now().format(formatter);

        if (discount != null) {
            if (discount.getDiscountLastChangeData() == null) {
                return true;
            } else if (discount.getDiscountDateFrom().compareTo(discount.getDiscountLastChangeData()) < 0
                    && discount.getDiscountDateTo().compareTo(discount.getDiscountLastChangeData()) > 0) {
                return !nowDate.equals(discount.getDiscountLastChangeData());
            }
        }

        return false;
    }

    public static void updateDiscount(Connection connection) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowDate = LocalDateTime.now().format(formatter);

        Map<Tour, Discount> tourDiscountMap = DiscountCalculationUtils.getTourDiscountMap(connection);

        for (Map.Entry<Tour, Discount> entry : tourDiscountMap.entrySet()) {
            if (checkDateFromDiscount(entry.getValue())) {
                try {
                    float calcPrice = DiscountCalculationUtils.calcPrice(connection, entry.getKey());
                    try {
                        if (DBUtils.updateDiscountPrice(connection, calcPrice, entry.getKey().getDiscountID())) {
                            DBUtils.updateDiscountLastChange(connection, nowDate, entry.getKey().getDiscountID());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (DiscountCalculationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
