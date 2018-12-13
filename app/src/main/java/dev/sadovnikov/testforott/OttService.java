package dev.sadovnikov.testforott;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface OttService {

    @GET("/bins/12q3ws.json")
    Observable<Map<String, List<Hotel>>> getHotels();

    @GET("/bins/zqxvw.json")
    Observable<Map<String, List<Flight>>> getFlights();

    @GET("/bins/8d024.json")
    Observable<Map<String, List<Company>>> getCompanies();

}
