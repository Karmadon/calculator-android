package pro.megakit.calculator.calculator;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

@SuppressWarnings("WeakerAccess")
class CalculatorOperation {

    private static final String TAG = "CalculatorOperation";

    static final String INPUT = "input";

    static final String NONE = "none";
    static final String CLEAR_ALL = "clear_all";
    static final String CLEAR = "clear";
    static final String CALC = "calc";

    static final String PLUS = "plus";
    static final String MINUS = "minus";
    static final String MULTIPLY = "multiply";
    static final String DIVIDE = "divide";

    static final String COS = "cos";
    static final String SIN = "sin";
    static final String PERCENT = "percent";
    static final String SQRT = "sqrt";

    static final String PI = "pi";
    static final String E = "e";

    private static final String[] OPERATIONS = {
            INPUT, NONE, CLEAR_ALL, CLEAR, CALC,
            PLUS, MINUS, MULTIPLY, DIVIDE,
            COS, SIN, PERCENT, SQRT, PI, E
    };

    private static final String[] BINARY = {
            PLUS, MINUS, MULTIPLY, DIVIDE
    };

    private static final String[] UNARY = {
            COS, SIN, PERCENT, SQRT
    };

    private static final String[] CONST = {
            PI, E
    };

    private String value;
    private String type;

    private CalculatorOperation(String type) {
        this.type = type;
    }

    static CalculatorOperation fromType(String type) {
        if (Arrays.asList(OPERATIONS).indexOf(type) == -1) {
            throw new UnsupportedOperationException("Operation [" + type + "] not supported");
        }
        return new CalculatorOperation(type);
    }

    private CalculatorOperation from(double value) {
        return fromType(INPUT).setValue(String.valueOf(value));
    }

    CalculatorOperation process(CalculatorExpression expression, int index) throws CalculationException {
        if (isBinary()) {
            return processBinary(expression, index);
        } else if (isUnary()) {
            return processUnary(expression, index);
        } else if (isConst()) {
            return processConst(expression, index);
        } else {
            return this;
        }
    }

    private CalculatorOperation processBinary(CalculatorExpression expression, int index) throws CalculationException {

        CalculatorOperation left = expression.get(index - 1);
        CalculatorOperation right = expression.get(index + 1);

        if (left == null || !left.isInput() || right == null || !right.isInput()) {
            throw new CalculationException("Calculation error");
        }

        double result = 0;

        switch (type) {
            case PLUS:
                result = left.doubleValue() + right.doubleValue();
                break;
            case MINUS:
                result = left.doubleValue() - right.doubleValue();
                break;
            case MULTIPLY:
                result = left.doubleValue() * right.doubleValue();
                break;
            case DIVIDE:
                result = left.doubleValue() / right.doubleValue();
                break;
        }

        Log.i(TAG, "processBinary: " + left.getValue() + this.getValue() + right.getValue() + "=" + result);

        CalculatorOperation operation = from(result);

        expression.set(index, operation);
        expression.remove(left, right);

        return operation;
    }

    private CalculatorOperation processUnary(CalculatorExpression expression, int index) throws CalculationException {

        CalculatorOperation left = expression.get(index - 1);
        CalculatorOperation right = expression.get(index + 1);

        CalculatorOperation current = null;

        if (right != null && right.isInput()) {
            current = right;
        } else if (left != null && left.isInput()) {
            current = left;
        }

        if (current == null) {
            throw new CalculationException("Calculation error");
        }

        double result = 0;

        switch (type) {
            case COS:
                result = Math.cos(current.doubleValue());
                break;
            case SIN:
                result = Math.sin(current.doubleValue());
                break;
            case SQRT:
                result = Math.sqrt(current.doubleValue());
                break;
        }

        if (PERCENT.equals(type)) {
            for (int i = expression.indexOf(current) - 1; i >= 0; i--) {
                CalculatorOperation value = expression.get(i);
                if (value == null || !value.isInput()) continue;
                result = processPercent(value, current);
                break;
            }
        }

        Log.i(TAG, "processUnary: " + type + current.getValue() + "=" + result);

        CalculatorOperation operation = from(result);
        expression.set(index, operation);

        return operation;
    }

    private CalculatorOperation processConst(CalculatorExpression expression, int index)
            throws CalculationException {

        Map<String, Double> values = new HashMap<>();
        values.put(PI, Math.PI);
        values.put(E, Math.E);

        if (!values.containsKey(type)) {
            throw new CalculationException("Unsupported constant [" + type + "]");
        }

        CalculatorOperation operation = from(values.get(type));
        expression.set(index, operation);

        return operation;
    }

    private double processPercent(CalculatorOperation value, CalculatorOperation percent) {
        return percent.doubleValue() / 100 * value.doubleValue();
    }

    int getPriority() {
        if (isBinary()) {
            return MULTIPLY.equals(type) || DIVIDE.equals(type) ? 5 : 0;
        } else if (isUnary()) {
            return 10;
        } else if (isConst()) {
            return 15;
        } else {
            return 0;
        }
    }

    private boolean isBinary() {
        return Arrays.asList(BINARY).indexOf(type) != -1;
    }

    private boolean isUnary() {
        return Arrays.asList(UNARY).indexOf(type) != -1;
    }

    private boolean isConst() {
        return Arrays.asList(CONST).indexOf(type) != -1;
    }

    boolean isOperator() {
        return isBinary() || isUnary() || isConst();
    }

    boolean isInput() {
        return INPUT.equals(type);
    }

    public CalculatorOperation setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }

    Double doubleValue() {
        return Double.valueOf(getValue());
    }

    String getType() {
        return type;
    }
}
