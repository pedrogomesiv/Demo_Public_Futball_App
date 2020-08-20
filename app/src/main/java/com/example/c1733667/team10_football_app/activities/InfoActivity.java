package com.example.c1733667.team10_football_app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.classpack.Navigation;
import com.example.c1733667.team10_football_app.classpack.ThemeSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {
    private TextView clubInfo;
    private TextView clubName;
    private TextView clubStadium;
    private TextView clubLocation;
    private Intent intent;
    private Toolbar toolbar;
    private DrawerLayout navDrawer;
    private NavigationView navView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref1 = getSharedPreferences("High contrast", 0);
        ThemeSetting infoSetting = new ThemeSetting(pref1,InfoActivity.this);
        infoSetting.setHighContrast(R.layout.activity_info_outer);

        clubInfo = findViewById(R.id.clubName);
        clubLocation = findViewById(R.id.clubLocation);
        toolbar = findViewById(R.id.my_toolbar);
        String clubFromOtherActivity = this.getIntent().getStringExtra("Club Name");
        if (clubFromOtherActivity != null) {
            toolbar.setTitle(clubFromOtherActivity);
            getClubInfo();
        }
        clubLocation.setOnClickListener(this);
        setSupportActionBar(toolbar);
        this.navDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, navDrawer, toolbar, R.string.open, R.string.close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navView = findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);

        ThemeSetting clubInfoContrast = new ThemeSetting(pref1,InfoActivity.this);
        clubInfoContrast.setTextviewContrast(clubInfo);
        ThemeSetting clubLocationContrast = new ThemeSetting(pref1,InfoActivity.this);
        clubLocationContrast.setTextviewContrast(clubLocation);
        ThemeSetting clubNameContrast = new ThemeSetting(pref1,InfoActivity.this);
        clubNameContrast.setTextviewContrast(clubName);
        ThemeSetting clubStadiumContrast = new ThemeSetting(pref1,InfoActivity.this);
        clubStadiumContrast.setTextviewContrast(clubStadium);


        NavigationView navigationView = navView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Navigation navigation = new Navigation(item, InfoActivity.this);
                navigation.activityNavigation(getApplicationContext());
                return false;
            }
        });
    }


    public void getClubInfo() {
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.clubs);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        parseJson(builder.toString());
    }

    private void parseJson(String json) {
        clubInfo = (TextView) findViewById(R.id.clubInfo);
        clubName = (TextView) findViewById(R.id.clubName);
        clubLocation = (TextView) findViewById(R.id.clubLocation);
        clubStadium = (TextView) findViewById(R.id.clubStadium);

        String clubFromOtherActivity = this.getIntent().getStringExtra("Club Name");
        StringBuilder info = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder location = new StringBuilder();
        StringBuilder stadium = new StringBuilder();

        try {
            JSONObject root = new JSONObject(json);
            JSONObject clubInfo = root.getJSONObject(clubFromOtherActivity);

            info.append("Stadium capacity: ")
                    .append(clubInfo.getString("Stadium capacity"))
                    .append("\n")
                    .append("Manager: ")
                    .append(clubInfo.getString("Manager"))
                    .append("\n")
                    .append("History: ")
                    .append(clubInfo.getString("History"));
            name.append("Name: ")
                    .append(clubInfo.getString("club name"));
            location.append("Location: ")
                    .append(clubInfo.getString("Stadium Location"));
            stadium.append("Stadium: ")
                    .append(clubInfo.getString("Stadium "));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        clubInfo.setText(info.toString());
        clubName.setText(name.toString());
        clubLocation.setText(location.toString());
        clubStadium.setText(stadium.toString());
    }


    @Override
    public void onClick(View v) {
        String str = clubName.getText().toString();
        String[] location = str.split(" ", 2);
        Uri gmmIntentUri = Uri.parse("geo:51.488762, -3.174134?q=" + location[1].toString() + " " + "Football Stadium");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}

