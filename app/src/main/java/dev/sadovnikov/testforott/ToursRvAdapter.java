package dev.sadovnikov.testforott;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ToursRvAdapter extends RecyclerView.Adapter<ToursRvAdapter.TourViewHolder> {
    private static final String TAG = "ToursRvAdapter";

    private RvToursListener listener;
    List<Tour> tours;

    public ToursRvAdapter(List<Tour> tours, RvToursListener rvToursListener) {
        this.tours = tours;
        listener = rvToursListener;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tour_card, viewGroup, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder tourViewHolder, int i) {
        String hotelName = "Отель " + "\"" + tours.get(i).getHotel().getName() + "\"";
        tourViewHolder.tvNameOfHotel.setText(hotelName);
        if (tours.get(i).getFlights().size() % 10 == 1) {
            tourViewHolder.tvNumberOfFlights.setText(String.valueOf(tours.get(i).getFlights().size() + " вариант перелета"));
        } else {
            tourViewHolder.tvNumberOfFlights.setText(String.valueOf(tours.get(i).getFlights().size() + " варианта перелета"));
        }
        String startPrice = "от " + String.valueOf(tours.get(i).getChosenPrice()) + " р";
        tourViewHolder.tvPrice.setText(startPrice);
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    class TourViewHolder extends RecyclerView.ViewHolder {
        CardView cvTour;
        TextView tvNameOfHotel;
        TextView tvNumberOfFlights;
        TextView tvPrice;

        TourViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                Tour tour = tours.get(getLayoutPosition());
                listener.onTourClick(tour);
            });
            cvTour = itemView.findViewById(R.id.cv_tour);
            tvNameOfHotel = itemView.findViewById(R.id.tv_hotel_name);
            tvNumberOfFlights = itemView.findViewById(R.id.tv_number_of_flights);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }

    }

    interface RvToursListener {

        void onTourClick(Tour tour);
    }
}
