package com.example.giasson_waterson;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class DateFragment extends Fragment {
    ArrayList<Case> caseList;
    ListView lvCases;
    EditText textFilter;
    Button findBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        MainActivity activity = (MainActivity) getActivity();
        caseList = activity.getCaseList();

        lvCases = view.findViewById(R.id.lvCases);
        textFilter = view.findViewById(R.id.datePicker);
        findBtn = view.findViewById(R.id.findBtn);

        CaseListAdapter adapter = new CaseListAdapter(getActivity(), caseList);
        lvCases.setAdapter(adapter);
        lvCases.setTextFilterEnabled(true);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getFilter().filter(textFilter.getText().toString());
            }
        });

        return view;
    }
}
