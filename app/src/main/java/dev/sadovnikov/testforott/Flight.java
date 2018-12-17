package dev.sadovnikov.testforott;

import android.support.annotation.NonNull;

import java.util.Objects;

public class Flight {
    private long id;
    private long companyId;
    private String companyName;
    private String departure;
    private String arrival;
    private long price;

    Flight(long id, long companyId, String departure, String arrival, long price) {
        this.id = id;
        this.companyId = companyId;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public long getPrice() {
        return price;
    }

    @NonNull
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @NonNull
    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return getId() == flight.getId() &&
                getCompanyId() == flight.getCompanyId() &&
                getPrice() == flight.getPrice() &&
                Objects.equals(getDeparture(), flight.getDeparture()) &&
                Objects.equals(getArrival(), flight.getArrival());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCompanyId(), getDeparture(), getArrival(), getPrice());
    }
}
