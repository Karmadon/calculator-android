package pro.megakit.calculator.calculator;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class CalculationResult {

    private CalculatorExpression expression;
    private double result;

    CalculationResult(CalculatorExpression expression, double result) {
        this.expression = expression;
        this.result = result;
    }

    public CalculatorExpression getExpression() {
        return expression;
    }

    public double getResult() {
        return result;
    }
}
