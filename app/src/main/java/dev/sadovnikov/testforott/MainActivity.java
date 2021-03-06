package dev.sadovnikov.testforott;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends MvpActivity<Contract.SerpView, SerpPresenter> implements Contract.SerpView, ToursRvAdapter.RvToursListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rv_tours)
    RecyclerView rvTours;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ToursRvAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new ToursRvAdapter(this);
        rvTours.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvTours.setAdapter(adapter);
        getPresenter().onMainActivityCreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    public MainActivity() {
        super();
        Log.d(TAG, "onConstructor: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: ");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
    }

    @NonNull
    @Override
    public SerpPresenter createPresenter() {
        Log.d(TAG, "createPresenter: ");
        return new SerpPresenter();
    }

    @Override
    public void onTourClick(Tour tour) {
        Log.d(TAG, "onTourClick: " + tour);
    }


    @Override
    public void showTours(List<Tour> tours) {
        Log.d(TAG, "showTours: ");
        adapter.setTours(tours);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading: ");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hideLoading: ");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showFlightsDialog() {
        Log.d(TAG, "showFlightsDialog: ");
    }
}