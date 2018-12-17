package dev.sadovnikov.testforott;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import io.reactivex.Observable;

public interface Contract {

    interface Repository {

        Observable<List<Hotel>> getHotelsObservable();

        Observable<List<Flight>> getFlightsObservable();

        Observable<List<Company>> getCompaniesObservable();

        Observable<List<Tour>> getToursObservable();

        void loadTours();


    }

    interface Presenter {
        void onMainActivityCreate();

    }

    interface SerpView extends MvpView {
        void showTours(List<Tour> tours);

        void showLoading();

        void hideLoading();

        void showFlightsDialog();
    }

}
