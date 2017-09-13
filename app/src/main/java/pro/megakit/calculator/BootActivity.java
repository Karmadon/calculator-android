package pro.megakit.calculator;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pro.megakit.calculator.base.BaseActivity;
import pro.megakit.calculator.module.calculator.CalculatorActivity;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class BootActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        startDelayed();
    }

    private void startDelayed() {
        disposable(Observable
                .just(new Intent(this, CalculatorActivity.class))
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::start));
    }

    private void start(Intent intent) {
        startActivity(intent);
        finish();
    }
}
