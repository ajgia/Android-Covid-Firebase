package com.example.assignment2_covidfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseCases;
    List<Case> caseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the SectionsPageAdapter to the ViewPager
        SectionsPageAdapter pagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        // Attach the ViewPager to the TabLayout
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        
        databaseCases
                = FirebaseDatabase.getInstance().getReference("cases");
        caseList = new ArrayList<>();



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

    public class SectionsPageAdapter extends FragmentPagerAdapter {
        public SectionsPageAdapter(FragmentManager fm) { super(fm); }

        @Override
        public int getCount() { return 4; }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new GenderFragment();
                case 1:
                    return new AgeFragment();
                case 2:
                    return new HAFragment();
                case 3:
                    return new DateFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.gender_tab);
                case 1:
                    return getResources().getText(R.string.age_tab);
                case 2:
                    return getResources().getText(R.string.ha_tab);
                case 3:
                    return getResources().getText(R.string.date_tab);
            }
            return null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseCases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                caseList.clear();
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                   Case casefile = studentSnapshot.getValue(Case.class);
                    caseList.add(casefile);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}

