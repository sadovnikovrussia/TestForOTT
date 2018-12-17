package dev.sadovnikov.testforott;

import java.util.ArrayList;
import java.util.List;

public class TourMaker {

    public static ArrayList<Tour> makeTours(List<Hotel> hotels, List<Flight> flights) {
        ArrayList<Tour> tours = new ArrayList<>();
        for (Hotel hotel : hotels) {
            ArrayList<Flight> flightArrayList = new ArrayList<>();
            for (Flight flight : flights){
                if (hotel.getFlightsIds().contains(flight.getId())){
                    flightArrayList.add(flight);
                }
            }
            tours.add(new Tour(hotel, flightArrayList));
        }
        return tours;
    }
}
