package com.example.giasson_waterson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseCases;
    public ArrayList<Case> caseList;
    TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up database
        databaseCases = FirebaseDatabase.getInstance().getReference();

        caseList = new ArrayList<Case>();

//        UI Controls
        count = findViewById(R.id.count);
        // Attach the TabPageAdapter to the ViewPager
        TabPageAdapter pagerAdapter = new TabPageAdapter(getSupportFragmentManager(), getApplicationContext() );
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        // Attach the ViewPager to the TabLayout
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);


        Button logoutBtn = findViewById(R.id.mainLogoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, AuthenticationActivity.class);
                startActivity(i);
            }
        });
    }

    public ArrayList<Case> getCaseList() {
        return caseList;
    }

    @Override
    protected void onStart() {
        super.onStart();

            databaseCases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    caseList.clear();
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {

                        Case c = dss.getValue(Case.class);
                        caseList.add(c);
                    }
                    count.setText(Long.toString(dataSnapshot.getChildrenCount()) + " " + getString(R.string.items_loaded));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }



}

