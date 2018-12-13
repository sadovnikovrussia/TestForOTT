package dev.sadovnikov.testforott;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Tour implements Serializable {
    //long id;
    private Hotel hotel;
    private ArrayList<Flight> flights;
    private Flight chosenFlight;
    private long chosenPrice;

    Tour(Hotel hotel, ArrayList<Flight> flights) {
        this.hotel = hotel;
        this.flights = flights;
        this.chosenFlight = this.flights.get(0);
        this.chosenPrice = this.chosenFlight.getPrice() + hotel.getPrice();
        long cheapestFlightPrice = this.flights.get(0).getPrice();
        for (Flight f : this.flights) {
            if (f.getPrice() < cheapestFlightPrice) {
                chosenFlight = f;
            }
        }
        this.chosenPrice = hotel.getPrice() + chosenFlight.getPrice();
    }

    int getIndexOfChosenFlight() {
        return flights.indexOf(chosenFlight);
    }

    Flight getFlightByIndex(int i) {
        return flights.get(i);
    }

    long getPriceByFlight(Flight flight) {
        return 0;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Flight getChosenFlight() {
        return chosenFlight;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public long getChosenPrice() {
        return chosenPrice;
    }

    @NonNull
    @Override
    public String toString() {
        return "Tour{" +
                "hotel=" + hotel +
                ", flights=" + flights +
                '}';
    }
}
