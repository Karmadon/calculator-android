package pro.megakit.calculator.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import pro.megakit.calculator.R;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class CalculatorKeyboardButton extends AppCompatButton {

    private String operation;
    private String value;

    public CalculatorKeyboardButton(Context context) {
        this(context, null);
    }

    public CalculatorKeyboardButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.calculatorButtonStyle);
    }

    public CalculatorKeyboardButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CalculatorKeyboardButton, defStyleAttr, 0);
        operation = a.getString(R.styleable.CalculatorKeyboardButton_operation);
        value = a.getString(R.styleable.CalculatorKeyboardButton_value);
        a.recycle();

        setText(value);
    }

    public String getOperation() {
        return operation;
    }

    public String getValue() {
        return value;
    }
}
