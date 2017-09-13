package pro.megakit.calculator.module.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import pro.megakit.calculator.R;
import pro.megakit.calculator.base.BaseFragment;
import pro.megakit.calculator.calculator.CalculatorController;
import pro.megakit.calculator.view.CalculatorDisplay;
import pro.megakit.calculator.view.CalculatorKeyboard;
import pro.megakit.calculator.view.CalculatorKeyboardButton;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class CalculatorFragment extends BaseFragment {

    @Inject CalculatorController calculatorController;

    @BindView(R.id.display) CalculatorDisplay display;
    @BindView(R.id.keyboard) CalculatorKeyboard keyboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewBinding(View view, @Nullable Bundle savedInstanceState) {
        registerKeyboardButtons();
        setupDisplay();
    }

    @Override
    public void onDestroyView() {
        calculatorController.setListener(null);
        super.onDestroyView();
    }

    private void registerKeyboardButtons() {
        for (int i = 0, count = keyboard.getChildCount(); i < count; i++) {
            keyboard.getChildAt(i).setOnClickListener(this::onClick);
        }
    }

    private void onClick(View view) {
        if (view instanceof CalculatorKeyboardButton) {
            handleKeyboardButton((CalculatorKeyboardButton) view);
        }
    }

    private void handleKeyboardButton(CalculatorKeyboardButton button) {
        calculatorController.processInput(button.getOperation(), button.getValue());
    }

    private void setupDisplay() {
        calculatorController.setListener(display);
    }
}
