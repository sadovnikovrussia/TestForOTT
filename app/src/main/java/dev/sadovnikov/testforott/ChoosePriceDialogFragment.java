package dev.sadovnikov.testforott;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;

public class ChoosePriceDialogFragment extends DialogFragment {
    private static final String TAG = "ChoosePriceDialogFragme";

    ChoosePriceDialogFragmentListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        Tour tour;
        ArrayList<Flight> flights;
        ArrayList<Company> companies;
        Bundle arguments = getArguments();
        Log.d(TAG, "onCreateDialog: " + arguments);
        tour = (Tour) arguments.get("tour");
        companies = (ArrayList<Company>) arguments.get("companies");
        flights = tour.getFlights();
        String[] sFlights = flights != null ? new String[flights.size()] : new String[0];
        for (int i = 0; i < flights.size(); i++) {
            for (int j = 0; j < companies.size(); j++) {
                if (companies.get(j).getId() == flights.get(i).getCompanyId()) {
                    flights.get(i).setCompanyName(companies.get(j).getName());
                }
            }
            long price = tour.getHotel().getPrice() + flights.get(i).getPrice();
            sFlights[i] = flights.get(i).getCompanyName() + " " + price;
        }
        Log.i(TAG, "onCreateDialog: " + String.valueOf(sFlights));

        FlightsAdapter adapter = new FlightsAdapter(getContext(), R.layout.flight_item, tour, companies);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выбор авиакомпании");
        builder.setSingleChoiceItems(sFlights, tour.getIndexOfChosenFlight(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "onClick: ");
            }
        });
        builder.setPositiveButton("Выбрать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "onClick: ");
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(TAG, "onAttach");
        if (context instanceof ChoosePriceDialogFragmentListener) {
            listener = (ChoosePriceDialogFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ChoosePriceDialogFragmentListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "onActivityCreated");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    interface ChoosePriceDialogFragmentListener {

    }
}
