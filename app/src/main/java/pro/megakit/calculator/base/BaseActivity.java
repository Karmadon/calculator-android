package pro.megakit.calculator.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public class BaseActivity extends DaggerAppCompatActivity {

    private CompositeDisposable disposables;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposables = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
        unbindViews();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        unbinder = ButterKnife.bind(this);
    }

    protected void disposable(Disposable disposable) {
        disposables.add(disposable);
    }

    private void unbindViews() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
