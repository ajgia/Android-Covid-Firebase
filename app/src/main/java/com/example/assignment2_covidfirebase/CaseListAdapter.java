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
import java.util.List;

public class CaseListAdapter extends ArrayAdapter<Case> implements Filterable {

    private Activity context;
    private List<Case> originalList;
    private List<Case > caseList;
    private Filter filter;

    public CaseListAdapter(Activity context, List<Case> originalList) {
        super(context, R.layout.list_layout, originalList);
        this.context = context;
        this.originalList = originalList;
        this.caseList = originalList;
    }

    public CaseListAdapter(Context context, int resource, List<Case> objects,
                           Activity context1, List<Case> originalList) {
        super(context, resource, objects);
        this.context = context1;
        this.originalList = originalList;
        this.caseList = originalList;
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
                if (results.count != 0)
                    caseList = (List<Case>) results.values;
                else
                    caseList = originalList;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterSeq = constraint.toString();
                FilterResults filterResults = new FilterResults();
                if (filterSeq == null || filterSeq.isEmpty() ) {
                    filterResults.values = originalList;
                    filterResults.count = originalList.size();
                }

                else {
                    List<Case> filtered = new ArrayList<Case>();
                    for (Case c: originalList) {
                        if (c.getReported_Date().contains(filterSeq))
                            filtered.add(c);
                    }
                    filterResults.values = filtered;
                    filterResults.count = filtered.size();
                }

                return filterResults;
            }


        };
        return filter;
    }
}


