package pro.megakit.calculator.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
