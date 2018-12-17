package dev.sadovnikov.testforott;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository implements Contract.Repository {
    private static final String TAG = Repository.class.getSimpleName();

    private Observable<List<Hotel>> hotelsObservable;
    private Observable<List<Flight>> flightsObservable;
    private Observable<List<Company>> companiesObservable;
    private Observable<List<Tour>> toursObservable;

    private List<Hotel> hotels;
    private List<Flight> flights;
    private List<Company> companies;


    @Override
    public Observable<List<Hotel>> getHotelsObservable() {
        if (hotelsObservable == null) {
            hotelsObservable = ApiFactory.getOttService().getHotels().map(stringListMap -> stringListMap.get("hotels"));
        }
        return hotelsObservable;
    }

    @Override
    public Observable<List<Flight>> getFlightsObservable() {
        if (flightsObservable == null) {
            flightsObservable = ApiFactory.getOttService().getFlights().map(stringListMap -> stringListMap.get("flights"));
        }
        return flightsObservable;
    }

    @Override
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
                        TourMaker::makeTours)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        );

    }

    @Override
    public void loadTours() {
        Observable<List<Tour>> toursObservable1 = Observable.defer(() -> Observable.zip(
                getHotelsObservable(),
                getFlightsObservable(),
                TourMaker::makeTours));

        Observable<List<Tour>> toursObservable = Observable.zip(
                getHotelsObservable(),
                getFlightsObservable(),
                TourMaker::makeTours);
    }
}
