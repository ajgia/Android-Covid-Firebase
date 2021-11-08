package com.example.assignment2_covidfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AgeFragment extends Fragment {
    List<Case> caseList;
    int u10, _10,_20,_30,_40,_50,_60,_70,_80,_90 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);

        MainActivity activity = (MainActivity) getActivity();
        caseList = activity.getCaseList();
        TextView countu10 = view.findViewById(R.id.countu10);
        TextView count10 = view.findViewById(R.id.count10);
        TextView count20 = view.findViewById(R.id.count20);
        TextView count30 = view.findViewById(R.id.count30);
        TextView count40 = view.findViewById(R.id.count40);
        TextView count50 = view.findViewById(R.id.count50);
        TextView count60 = view.findViewById(R.id.count60);
        TextView count70 = view.findViewById(R.id.count70);
        TextView count80 = view.findViewById(R.id.count80);
        TextView count90 = view.findViewById(R.id.count90);

//        assert activity != null
        for (Case c : caseList) {
            if (c.getAge_Group().equals("<10")) {
                u10++;
            }
            if (c.getAge_Group().equals("10-19")) {
                _10++;
            }
            if (c.getAge_Group().equals("20-29")) {
                _20++;
            }
            if (c.getAge_Group().equals("30-39")) {
                _30++;
            }
            if (c.getAge_Group().equals("40-49")) {
                _40++;
            }
            if (c.getAge_Group().equals("50-59")) {
                _50++;
            }
            if (c.getAge_Group().equals("60-69")) {
                _60++;
            }
            if (c.getAge_Group().equals("70-79")) {
                _70++;
            }
            if (c.getAge_Group().equals("80-89")) {
                _80++;
            }
            if (c.getAge_Group().equals(">90")) {
                _90++;
            }
       }
        countu10.setText(String.valueOf(_10));
//        count10.setText(_10);
//        count20.setText(_20);
//        count30.setText(_30);
//        count40.setText(_40);
//        count50.setText(_50);
//        count60.setText(_60);
//        count70.setText(_70);
//        count80.setText(_80);
//        count90.setText(_90);
            return view;


        }
    }