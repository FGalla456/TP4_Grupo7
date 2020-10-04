package com.example.tp4_grupo7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.tp4_grupo7.fragments.fragmentAlta;
import com.example.tp4_grupo7.fragments.fragmentListado;
import com.example.tp4_grupo7.fragments.fragmentModificacion;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.Tabs);
        loadViewPager(viewPager);
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

    private void textColor(TabLayout.Tab tab, String color) {
        tab.getIcon().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
    }

    private void loadViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentAlta(), "ALTA");
        adapter.addFragment(new fragmentModificacion(), "MODIFICACIÓN");
        adapter.addFragment(new fragmentListado(), "LISTADO");
        viewPager.setAdapter(adapter);
    }
}
