package com.example.assignment2_covidfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaseListAdapter extends ArrayAdapter<Case> implements Filterable {

    private Activity context;
    private List<Case> caseList;
    private Filter filter;

    public CaseListAdapter(Activity context, List<Case> caseList) {
        super(context, R.layout.list_layout, caseList);
        this.context = context;
        this.caseList = caseList;
    }

    public CaseListAdapter(Context context, int resource, List<Case> objects,
                           Activity context1, List<Case> caseList) {
        super(context, resource, objects);
        this.context = context1;
        this.caseList = caseList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView tvAge = listViewItem.findViewById(R.id.textViewAge);
        TextView tvDate = listViewItem.findViewById(R.id.textViewDate);
        TextView tvGender = listViewItem.findViewById(R.id.textViewGender);
        TextView tvHA = listViewItem.findViewById(R.id.textViewHA);

        Case c = caseList.get(position);
        tvAge.setText(c.getAge_Group());
        tvDate.setText(c.getReported_Date().toString());
        tvGender.setText(c.getSex());
        tvHA.setText(c.getHA());

        return listViewItem;
    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                caseList = (List<Case>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterSeq = constraint.toString();
                FilterResults results = new FilterResults();
                ArrayList<Case> filtered = new ArrayList<Case>();

                if (filterSeq != null && filterSeq.length() > 0 ) {
                    for (Case c: caseList) {
                        if (c.getReported_Date().contains(filterSeq))
                            filtered.add(c);
                    }
                    results.count = filtered.size();
                    results.values = filtered;
                } else {
                    synchronized (this) {
                        results.values = caseList;
                        results.count = caseList.size();
                    }
                }


                return results;
            }


        };
        return filter;
    }
}
