package pro.megakit.calculator.calculator;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 12.09.17.
 */

@SuppressWarnings("WeakerAccess")
public class CalculationException extends Exception {

    public CalculationException() {
    }

    public CalculationException(String message) {
        super(message);
    }

    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculationException(Throwable cause) {
        super(cause);
    }
}
