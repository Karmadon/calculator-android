package pro.megakit.calculator.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import pro.megakit.calculator.calculator.CalculationResult;
import pro.megakit.calculator.calculator.CalculatorExpression;
import pro.megakit.calculator.calculator.CalculatorListener;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class CalculatorDisplay extends LinearLayoutCompat implements CalculatorListener {

    private TextView expressionView;
    private TextView resultView;

    public CalculatorDisplay(Context context) {
        this(context, null);
    }

    public CalculatorDisplay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalculatorDisplay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);

        expressionView = new AppCompatTextView(context);
        expressionView.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(expressionView);

        resultView = new AppCompatTextView(context);
        resultView.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        resultView.setGravity(Gravity.END);
        addView(resultView);
    }

    @Override
    public void onCalculationResult(CalculationResult result) {
        resultView.setText(String.valueOf(result.getResult()));
    }

    @Override
    public void onCalculatorInput(CalculatorExpression expression) {
        expressionView.setText(expression.toString());
        resultView.setText("");
    }

    @Override
    public void onCalculationError(Exception e) {
        resultView.setText(e.getMessage());
    }
}
