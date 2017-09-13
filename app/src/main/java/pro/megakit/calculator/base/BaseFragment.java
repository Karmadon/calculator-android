package pro.megakit.calculator.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * Author: Sergey Semenko <abler98@gmail.com>
 * Created at 11.09.17.
 */

public abstract class BaseFragment extends DaggerFragment {

    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        onViewBinding(view, savedInstanceState);
    }

    public void onViewBinding(View view, @Nullable Bundle savedInstanceState) {
        // 
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
