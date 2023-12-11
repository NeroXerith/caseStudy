package com.bryle_sanico.casestudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapterTransactionHistory extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> itemList;

    public CustomAdapterTransactionHistory(Context context, List<String> itemList) {
        super(context, R.layout.listview_th, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_th, parent, false);

        TextView ownerName = rowView.findViewById(R.id.ownerName);
        TextView locationName = rowView.findViewById(R.id.locationName);
        TextView pMethod = rowView.findViewById(R.id.paymentMethod);
        TextView pAmount = rowView.findViewById(R.id.paymentamount);
        TextView date = rowView.findViewById(R.id.dateText);

        // Set data for each item
        ownerName.setText("Owner: " );
        locationName.setText("Location: " );
        pMethod.setText("Payment Method: ");
        pAmount.setText("Amount: " );
        date.setText("Date: ");

        return rowView;
    }

}
