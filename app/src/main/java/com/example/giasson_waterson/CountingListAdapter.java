package com.example.giasson_waterson;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.List;


public class CountingListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private List<String> countList;

    public CountingListAdapter(Activity context, List<String> countList) {
        super(context, R.layout.list_layout, countList);
        this.context = context;
        this.countList = countList;
        Collections.sort(countList);
    }

    public CountingListAdapter(Context context, int resource, List<String> objects, Activity context1, List<String> countList) {
        super(context, resource, objects);
        this.context = context1;
        this.countList = countList;
        Collections.sort(countList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout_count, null, true);
        TextView tvName = listViewItem.findViewById(R.id.textViewCount);
        String countString = countList.get(position);
        tvName.setText(countString);
        return listViewItem;
    }
}
