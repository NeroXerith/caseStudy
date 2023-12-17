package com.bryle_sanico.casestudy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
public class CustomAdapterMainHousings extends ArrayAdapter<UnitModel> {
    private Context context;
    private List<UnitModel> unitList;
    private LayoutInflater inflater;

    public CustomAdapterMainHousings(Context context, List<UnitModel> unitList) {
        super(context, 0, unitList);
        this.context = context;
        this.unitList = unitList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_main_housings, null);

            viewHolder = new ViewHolder();
            viewHolder.locName = convertView.findViewById(R.id.locName);
            viewHolder.locAddress = convertView.findViewById(R.id.locAddress);
            viewHolder.paymentAmount = convertView.findViewById(R.id.paymentamount);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UnitModel unitModel = getItem(position);

        if (unitModel != null) {
            viewHolder.locName.setText(unitModel.getLocName());
            viewHolder.locAddress.setText(unitModel.getLocAddress());
            viewHolder.paymentAmount.setText(unitModel.getPaymentAmount());
        }

        // Set an OnClickListener for each item in the ListView
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click here
                // Extract the unit ID and pass it to LocationDetailActivity
                String unitID = unitModel.getUnitID(); // Change this based on your UnitModel structure
                Intent intent = new Intent(context, locationDetail.class);
                intent.putExtra("UNIT_ID", unitID);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView locName;
        TextView locAddress;
        TextView paymentAmount;
    }
}
