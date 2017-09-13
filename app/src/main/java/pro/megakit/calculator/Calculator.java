package pro.megakit.calculator;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import pro.megakit.calculator.dagger.DaggerApplicationComponent;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class Calculator extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }
}
