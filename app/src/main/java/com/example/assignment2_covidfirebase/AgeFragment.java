package com.example.assignment2_covidfirebase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgeFragment extends Fragment {
    ArrayList<Case> caseList;
    ListView lvAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);
        MainActivity activity = (MainActivity) getActivity();
        caseList = activity.getCaseList();

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Case c: caseList) {
            String key = c.getAge_Group();
            map.merge(key, 1, Integer::sum);
        }

        ArrayList<String> resultList = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue().toString();
            String listEntry = k + " : " + v;
            resultList.add(listEntry);
        }

        lvAge = view.findViewById(R.id.lvAge);
        CountingListAdapter adapter = new CountingListAdapter(getActivity(), resultList);
        lvAge.setAdapter(adapter);

        return view;
    }
}
