package com.example.assignment2_covidfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

public class CaseListAdapter extends ArrayAdapter<Case> {

    private Activity context;
    private List<Case> caseList;

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
}
