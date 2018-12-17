package dev.sadovnikov.testforott;

import android.arch.lifecycle.LifecycleOwner;

public class Presenter implements Contract.Presenter {
    Contract.View view;
    LifecycleOwner lifecycleOwner;

    public Presenter(Contract.View view, LifecycleOwner lifecycleOwner) {
        this.view = view;
        this.lifecycleOwner = lifecycleOwner;
    }
}
