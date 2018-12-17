package dev.sadovnikov.testforott;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class Repository implements Contract.Repository {
    private static final String TAG = Repository.class.getSimpleName();

    private Observable<List<Hotel>> hotelsObservable;
    private Observable<List<Flight>> flightsObservable;
    private Observable<List<Company>> companiesObservable;
    private Observable<List<Tour>> toursObservable;

    private List<Hotel> hotels = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();
    private List<Tour> tours = new ArrayList<>();


    private Observable<List<Hotel>> getHotelsObservable() {
        if (hotelsObservable == null) {
            hotelsObservable = ApiFactory.getOttService().getHotels().map(stringListMap -> stringListMap.get("hotels"));
        }
        return hotelsObservable;
    }


    private Observable<List<Flight>> getFlightsObservable() {
        if (flightsObservable == null) {
            flightsObservable = ApiFactory.getOttService().getFlights().map(stringListMap -> stringListMap.get("flights"));
        }
        return flightsObservable;
    }

    public Observable<List<Company>> getCompaniesObservable() {
        if (companiesObservable == null) {
            companiesObservable = ApiFactory.getOttService().getCompanies().map(stringListMap -> stringListMap.get("companies"));
        }
        return companiesObservable;
    }

    @Override
    public Observable<List<Tour>> getToursObservable() {
        return Observable.defer(() -> Observable
                .zip(getHotelsObservable(),
                        getFlightsObservable(),
                        getCompaniesObservable(),
                        (hotels, flights, companies) -> {
                            Repository.this.companies = companies;
                            return TourMaker.makeTours(hotels, flights);
                        })
                .startWith(tours)
//                .compose(new ObservableTransformer<List<Tour>, List<Tour>>() {
//                    @Override
//                    public ObservableSource<List<Tour>> apply(Observable<List<Tour>> upstream) {
//                        return null;
//                    }
//                })
                .doOnNext(tours -> {
                    Log.d(TAG, "onNext: " + Thread.currentThread().getName() + ", " + tours);
                    this.tours = tours;
                    if (!this.tours.isEmpty()) this.tours.remove(0);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        );

    }

}
