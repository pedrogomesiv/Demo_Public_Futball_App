package com.example.c1733667.team10_football_app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.support.design.widget.NavigationView;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.classpack.ListViewClass;
import com.example.c1733667.team10_football_app.classpack.Navigation;
import com.example.c1733667.team10_football_app.classpack.ThemeSetting;

import java.util.Map;

public class ChampionshipLeague extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, NavigationView.OnNavigationItemSelectedListener {
    private String[] championLeague;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private ListViewCompat listViewCompat;
    private DrawerLayout navDrawer;
    private NavigationView navView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //event handling thing majig
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref1 = getSharedPreferences("High contrast", 0);
        ThemeSetting championSetting =new ThemeSetting(pref1,ChampionshipLeague.this);
        championSetting.setHighContrast(R.layout.activity_championship_league_outer);

        sharedPreferences = getSharedPreferences("ChampionPreference", Context.MODE_PRIVATE);

        ArrayAdapter<String> championAdapter;
        championLeague = getResources().getStringArray(R.array.EFLC);
        championAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, championLeague);
        listViewCompat = findViewById(R.id.championList);
        listViewCompat.setChoiceMode(ListViewCompat.CHOICE_MODE_MULTIPLE);

        ThemeSetting lv = new ThemeSetting(pref1,ChampionshipLeague.this);
        lv.setListView(R.id.championList, championAdapter);

        listViewCompat.setOnItemClickListener(this);
        listViewCompat.setOnItemLongClickListener(this);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        this.navDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, navDrawer, toolbar, R.string.open, R.string.close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navView = findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);

        Map map = sharedPreferences.getAll();
        for (Object key : map.keySet()) {
            listViewCompat.setItemChecked(Integer.valueOf((String) key), (Boolean) map.get((String) key));
        }

        NavigationView navigationView = navView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Navigation navigation = new Navigation(item, ChampionshipLeague.this);
                navigation.activityNavigation(getApplicationContext());
                return false;
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, String.format("Item clicked on = %d", position), Toast.LENGTH_SHORT).show();
        SparseBooleanArray checkeditems = listViewCompat.getCheckedItemPositions();
        sharedPreferences.edit().putBoolean(String.valueOf(position), checkeditems.get(position)).commit();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(getApplicationContext(), InfoActivity.class);
        intent.putExtra("Club Name", championLeague[position]);
        startActivity(intent);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
