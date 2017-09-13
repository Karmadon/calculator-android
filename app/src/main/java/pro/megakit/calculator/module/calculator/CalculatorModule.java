package pro.megakit.calculator.module.calculator;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 05.09.17.
 */

@Module
public abstract class CalculatorModule {

    @ContributesAndroidInjector
    abstract CalculatorActivity calculatorActivity();

    @ContributesAndroidInjector
    abstract CalculatorFragment calculatorFragment();

}
