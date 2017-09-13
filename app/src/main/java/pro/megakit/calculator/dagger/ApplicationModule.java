package pro.megakit.calculator.dagger;

import dagger.Module;
import dagger.Provides;
import pro.megakit.calculator.calculator.CalculatorController;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

@Module
abstract class ApplicationModule {
    @Provides
    static CalculatorController provideCalculatorController() {
        return new CalculatorController();
    }
}
