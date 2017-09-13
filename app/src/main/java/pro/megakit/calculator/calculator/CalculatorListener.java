package pro.megakit.calculator.calculator;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public interface CalculatorListener {

    void onCalculationResult(CalculationResult result);

    void onCalculatorInput(CalculatorExpression expression);

    void onCalculationError(Exception e);

}
