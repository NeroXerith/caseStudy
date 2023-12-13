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

public class CustomAdapterMessages extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> itemList;

    public CustomAdapterMessages(Context context, List<String> itemList) {
        super(context, R.layout.listview_messages, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_messages, parent, false);

        ImageView imageView = rowView.findViewById(R.id.userPic);
        TextView userNameView = rowView.findViewById(R.id.userName);
        TextView lastMessageView = rowView.findViewById(R.id.lastmessage);

        // Set data for each item
        imageView.setImageResource(R.drawable.docs_placeholder);
        userNameView.setText("");
        lastMessageView.setText("");

        return rowView;
    }
}
