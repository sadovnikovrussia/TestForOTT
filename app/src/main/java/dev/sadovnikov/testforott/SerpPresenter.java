package dev.sadovnikov.testforott;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.disposables.Disposable;

public class SerpPresenter extends MvpBasePresenter<Contract.SerpView> implements Contract.Presenter {
    private static final String TAG = SerpPresenter.class.getSimpleName();

    Contract.Repository repository;

    public SerpPresenter() {
        super();
        Log.d(TAG, "onConstructor: ");
        repository = new Repository();
    }

    @Override
    public void attachView(@NonNull Contract.SerpView serpView) {
        super.attachView(serpView);
        Log.d(TAG, "attachView: ");
    }

    @Override
    public void detachView() {
        super.detachView();
        Log.d(TAG, "detachView: ");
    }

    @Override
    public void destroy() {
        super.destroy();
        Log.d(TAG, "destroy: ");
    }

    @Override
    public void onMainActivityCreate() {
        Disposable subscribe = repository.getToursObservable()
                .doOnSubscribe(disposable -> {
                    Log.d(TAG, "onSubscribe: ");
                    ifViewAttached(Contract.SerpView::showLoading);
                })
                .doOnTerminate(() -> {
                    Log.d(TAG, "onTerminate: ");
                    ifViewAttached(Contract.SerpView::hideLoading);
                })
                .subscribe(tours -> ifViewAttached(view -> view.showTours(tours)));

    }
}
