package ua.nure.ohol.SummaryTask4.exception;

/**
 * Exception in Discont Calculation Utils
 */
public class DiscountCalculationException extends Exception {

    public DiscountCalculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscountCalculationException(String message) {
        super(message);
    }

    public DiscountCalculationException() {
    }
}
