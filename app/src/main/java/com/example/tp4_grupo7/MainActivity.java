package com.example.tp4_grupo7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        loadViewPager(viewPager);
        tabLayout=(TabLayout)findViewById(R.id.Tabs);
        tabLayout.setupWithViewPager(viewPager);
         /*textColor(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()),"#3b5998");*/
     /*   tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                textColor(tab,"#3b5998");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                textColor(tab,"#E0E0E0");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

    private void textColor(TabLayout.Tab tab , String color){
        tab.getIcon().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
    }

    private void loadViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(newInstance("Alta"));
        adapter.addFragment(newInstance("Modificacion"));
        adapter.addFragment(newInstance("Listado"));
        viewPager.setAdapter(adapter);
    }

    private fragmentAlta newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        fragmentAlta fragment = new fragmentAlta();
        fragment.setArguments(bundle);

        return fragment;
    }
}