package dev.sadovnikov.testforott;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FlightsAdapter extends ArrayAdapter<Flight> {

    private final LayoutInflater inflater;
    private Tour tour;
    private ArrayList<Company> companies;

    public FlightsAdapter(@NonNull Context context, int resource, Tour tour, @NonNull List<Company> companies) {
        super(context, resource, tour.getFlights());
        inflater = LayoutInflater.from(context);
        this.tour = tour;
        this.companies = (ArrayList<Company>) companies;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);
            final ViewHolder holder = new ViewHolder();
            holder.tvCompany = convertView.findViewById(R.id.tv_company);
            holder.tvPrice = convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        final Flight flight = getItem(position);
        for (Company company : companies) {
            if (flight.getCompanyId() == company.getId()){
                flight.setCompanyName(company.getName());
            }
        }
        holder.tvCompany.setText(flight.getCompanyName());
        holder.tvPrice.setText(String.valueOf(tour.getHotel().getPrice() + flight.getPrice()));
        return convertView;
    }

    private class ViewHolder {
        public TextView tvPrice;
        public TextView tvCompany;
    }
}
