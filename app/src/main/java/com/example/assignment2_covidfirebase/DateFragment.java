package com.example.assignment2_covidfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

public class DateFragment extends Fragment {
    List<Case> caseList;
    ListView lvCases;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        MainActivity activity = (MainActivity) getActivity();
        caseList = activity.getCaseList();

        lvCases = view.findViewById(R.id.lvCases);
        CaseListAdapter adapter = new CaseListAdapter(getActivity(), caseList);
        lvCases.setAdapter(adapter);
        return view;
    }
}
