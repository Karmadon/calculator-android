package pro.megakit.calculator.calculator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class CalculatorController
{
    private WeakReference<CalculatorListener> listener;
    private CalculatorExpression expression;

    public CalculatorController() {
        expression = new CalculatorExpression();
    }

    public void processInput(@NonNull String name, @Nullable String value) {
        CalculatorOperation operation = CalculatorOperation.fromType(name.toLowerCase());
        exec(operation.setValue(value));
    }

    private void exec(CalculatorOperation operation) {
        switch (operation.getType()) {
            case CalculatorOperation.NONE:
                // Nothing
                break;
            case CalculatorOperation.CLEAR:
                expression.clear();
                getListener().onCalculatorInput(expression);
                break;
            case CalculatorOperation.CLEAR_ALL:
                expression.clearAll();
                getListener().onCalculatorInput(expression);
                break;
            case CalculatorOperation.CALC:
                calculateResult();
                break;
            case CalculatorOperation.INPUT:
                processInputOperation(expression.getLastOperation(), operation);
                break;
            default:
                expression.append(operation);
                getListener().onCalculatorInput(expression);
                break;
        }
    }

    private void calculateResult() {
        try {
            CalculationResult result = new CalculationResult(expression, expression.calculate());
            getListener().onCalculationResult(result);
        } catch (Exception e) {
            getListener().onCalculationError(e);
            expression.clearAll();
        }
    }

    private void processInputOperation(CalculatorOperation last, CalculatorOperation operation) {
        if (last != null && last.isInput()) {
            if (isValidNumber(last.getValue() + operation.getValue())) {
                last.setValue(last.getValue() + operation.getValue());
                getListener().onCalculatorInput(expression);
            }
        } else if (isValidNumber(operation.getValue())) {
            expression.append(operation);
            getListener().onCalculatorInput(expression);
        }
    }

    private boolean isValidNumber(String value){
        return value.matches("^([1-9]\\d*|0)(\\.\\d*)?$") && value.length() <= 64;
    }

    public void setListener(CalculatorListener listener) {
        this.listener = new WeakReference<>(listener);
    }

    private CalculatorListener getListener() {
        if (listener == null || listener.get() == null) {
            listener = new WeakReference<>(new CalculatorListenerImpl());
        }
        return listener.get();
    }
}
