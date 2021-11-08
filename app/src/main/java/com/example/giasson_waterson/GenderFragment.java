package com.example.giasson_waterson;

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

public class GenderFragment extends Fragment {
    ArrayList<Case> caseList;
    ListView lvGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);
        MainActivity activity = (MainActivity) getActivity();
        caseList = activity.getCaseList();

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Case c: caseList) {
            String key = c.getSex();
            map.merge(key, 1, Integer::sum);
        }

        ArrayList<String> resultList = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue().toString();
            String listEntry = k + " : " + v;
            resultList.add(listEntry);
        }

        lvGender = view.findViewById(R.id.lvGender);
        CountingListAdapter adapter = new CountingListAdapter(getActivity(), resultList);
        lvGender.setAdapter(adapter);


        return view;
    }
}
