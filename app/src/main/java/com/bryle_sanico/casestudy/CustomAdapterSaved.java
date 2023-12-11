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

public class CustomAdapterSaved extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> itemList;

    public CustomAdapterSaved(Context context, List<String> itemList) {
        super(context, R.layout.listview_saved, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_saved, parent, false);

        ImageView imageView = rowView.findViewById(R.id.locImage);
        TextView locNameView = rowView.findViewById(R.id.locName);
        TextView amountView = rowView.findViewById(R.id.paymentamount);

        // Set data for each item
        imageView.setImageResource(R.drawable.docs_placeholder);
        locNameView.setText("Location " + (position + 1));
        amountView.setText("Amount: ₱" + itemList.get(position));

        return rowView;
    }
}
