package pro.megakit.calculator.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pro.megakit.calculator.BootActivity;
import pro.megakit.calculator.module.calculator.CalculatorActivity;
import pro.megakit.calculator.module.calculator.CalculatorFragment;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

@Module
abstract class AndroidBindingModule {

    @ContributesAndroidInjector
    abstract BootActivity bootActivity();

}
