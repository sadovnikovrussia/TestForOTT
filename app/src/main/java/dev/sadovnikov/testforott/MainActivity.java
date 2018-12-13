package dev.sadovnikov.testforott;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ToursRvAdapter.RvToursListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.rv_tours)
    RecyclerView rvTours;
    ToursRvAdapter adapter;

    List<Hotel> hotels = new ArrayList<>();
    List<Flight> flights = new ArrayList<>();
    List<Company> companies = new ArrayList<>();
    List<Tour> tours = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new ToursRvAdapter(tours, this);
        rvTours.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvTours.setAdapter(adapter);
        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: null");
            OttService ottService = ApiFactory.getOttService();
            Observable<List<Flight>> flightsObservable = ottService.getFlights()
                    .map(stringListMap -> stringListMap.get("flights"));
            Observable<List<Company>> companiesObservable = ottService.getCompanies()
                    .map(stringListMap -> stringListMap.get("companies"));
            Observable<List<Hotel>> hotelsObservable = ottService.getHotels()
                    .map(stringListMap -> stringListMap.get("hotels"));

            Disposable disposable = hotelsObservable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(hotels -> {
                                this.hotels = hotels;
                                Log.i(TAG, "onNext: " + this.hotels);
                            },
                            throwable -> Log.w(TAG, "onError: ", throwable),
                            () -> flightsObservable
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(flights -> {
                                                this.flights = flights;
                                                Log.i(TAG, "onNext: " + this.flights);
                                            },
                                            throwable -> Log.w(TAG, "onError: ", throwable),
                                            () -> companiesObservable
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribeOn(Schedulers.io())
                                                    .subscribe(companies -> {
                                                                this.companies = companies;
                                                                Log.i(TAG, "onNext: " + this.companies);
                                                            },
                                                            throwable -> Log.w(TAG, "onError: ", throwable),
                                                            () -> {
                                                                tours = TourMaker.makeTours(hotels, flights, companies);
                                                                Log.d(TAG, "onComplete: " + tours);
                                                                adapter.tours = tours;
                                                                adapter.notifyDataSetChanged();
//                                                                adapter = new ToursRvAdapter(tours, this);
//                                                                rvTours.setAdapter(adapter);
                                                            }
                                                    )
                                    )
                    );
//            Disposable subscribe = Observable.concat(hotelsObservable, flightsObservable, companiesObservable)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .cache()
//                    .subscribe(objects -> {
//                        Class<? extends List> objectsClass = objects.getClass();
//                        Log.d(TAG, "onNext: " + objectsClass);
//                    });

//            hotelsObservable
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .cache()
//                    .subscribe(hotels -> {
//                        this.hotels = hotels;
//                        Log.i(TAG, "onNext: " + this.hotels);
//                    });
        } else {
            Log.d(TAG, "onCreate: notNull");
            tours = (List<Tour>) savedInstanceState.getSerializable("tours");
            adapter.tours = tours;
            adapter.notifyDataSetChanged();
            Log.d(TAG, "onCreate: " + savedInstanceState.getSerializable("tours"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putSerializable("tours", (Serializable) tours);
    }

    @Override
    public void onTourClick(Tour tour) {

    }
}