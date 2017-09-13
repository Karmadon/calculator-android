package pro.megakit.calculator.calculator;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 05.09.17.
 */

public class CalculatorExpression {

    private List<CalculatorOperation> input;

    CalculatorExpression() {
        input = new ArrayList<>();
    }

    @Nullable
    CalculatorOperation getLastOperation() {
        return input.size() > 0 ? input.get(input.size() - 1) : null;
    }

    double calculate() throws CalculationException {

        List<CalculatorOperation> operators = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isOperator()) {
                operators.add(input.get(i));
            }
        }

        Collections.sort(operators, (a, b) -> {
            if (a.getPriority() > b.getPriority()) {
                return -1;
            } else if (a.getPriority() < b.getPriority()) {
                return  1;
            } else {
                return  0;
            }
        });

        for (int i = 0; i < operators.size(); i++) {
            CalculatorOperation operator = operators.get(i);
            operator.process(this, input.indexOf(operator));
        }

        if (getLastOperation() != null) {
            return getLastOperation().doubleValue();
        }

        return 0;
    }

    int indexOf(CalculatorOperation operation) {
        return input.indexOf(operation);
    }

    @Nullable
    CalculatorOperation get(int index) {
        try {
            return input.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    void add(int index, CalculatorOperation operation) {
        input.add(index, operation);
    }

    void set(int index, CalculatorOperation operation) {
        input.set(index, operation);
    }

    void append(CalculatorOperation... operations) {
        Collections.addAll(input, operations);
    }

    void remove(CalculatorOperation... operations) {
        for (CalculatorOperation operation : operations) {
            input.remove(operation);
        }
    }

    void clear() {
        if (input.size() > 0) {
            input.remove(input.size() - 1);
        }
    }

    void clearAll() {
        input.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (CalculatorOperation operation : input) {
            builder.append(operation.getValue());
        }
        return builder.toString();
    }
}
