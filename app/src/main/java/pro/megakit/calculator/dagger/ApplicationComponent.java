package pro.megakit.calculator.dagger;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import pro.megakit.calculator.Calculator;
import pro.megakit.calculator.module.calculator.CalculatorModule;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

@Component(modules = {
        ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        AndroidBindingModule.class,
        CalculatorModule.class
})
interface ApplicationComponent extends AndroidInjector<Calculator> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<Calculator> {}
}
