package com.example.c1733667.team10_football_app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.adapterpack.AchievementCustomAdapter;
import com.example.c1733667.team10_football_app.classpack.ListViewClass;
import com.example.c1733667.team10_football_app.classpack.Navigation;
import com.example.c1733667.team10_football_app.classpack.ThemeSetting;

import java.util.Map;

public class Achievement extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private String[] achievements;
    private Integer[] imageID;
    private DrawerLayout navDrawer;
    private NavigationView navView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref1 = getSharedPreferences("High contrast", 0);
        ThemeSetting achievementSetting = new ThemeSetting(pref1, Achievement.this);
        achievementSetting.setHighContrast(R.layout.activity_achievement_outer);


        achievements = getResources().getStringArray(R.array.achievements);
        listView = (ListView) findViewById(R.id.achievementList);
        AchievementCustomAdapter customAdapter = new AchievementCustomAdapter(Achievement.this, achievements, imageID);
        ThemeSetting listViewClass = new ThemeSetting(pref1,Achievement.this);
        listViewClass.setListView(R.id.achievementList,customAdapter);
        listView.setOnItemClickListener(this);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        this.navDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, navDrawer, toolbar, R.string.open, R.string.close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navView = findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);

        NavigationView navigationView = navView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Navigation navigation = new Navigation(item, Achievement.this);
                navigation.activityNavigation(getApplicationContext());
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(getApplicationContext(), AchievemntInfo.class);
        intent.putExtra("AchievementLogic Name", achievements[position]);
        startActivity(intent);
    }
}
