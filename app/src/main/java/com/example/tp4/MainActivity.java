package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tp4.fragments.fragmentAlta;
import com.example.tp4.fragments.fragmentListado;
import com.example.tp4.fragments.fragmentModificacion;
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
    }

    private void loadViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentAlta(), "ALTA");
        adapter.addFragment(new fragmentModificacion(), "MODIFICACIÓN");
        adapter.addFragment(new fragmentListado(), "LISTADO");
        viewPager.setAdapter(adapter);
    }
}
