package com.example.assignment2_covidfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class AgeFragment extends Fragment {
    ArrayList<Case> caseList;

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

        int z = 1;
        return view;
    }

}
