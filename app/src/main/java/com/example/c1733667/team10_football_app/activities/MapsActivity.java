package com.example.c1733667.team10_football_app.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.c1733667.team10_football_app.BuildConfig;
import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.classpack.Navigation;
import com.example.c1733667.team10_football_app.classpack.ThemeSetting;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private String[] championLeague;
    private String[] premierLeague;
    private String[] leagueOne;
    private String[] leagueTwo;
    private ArrayList<String> clubName;
    private ArrayList<LatLng> visitedClubs;
    private DrawerLayout navDrawer;
    private NavigationView navView;
    private SharedPreferences pref1;
    private SharedPreferences pref2;
    private SharedPreferences pref3;
    private SharedPreferences pref4;
    private Intent intent;
    private File imagePath;
    private Button share;
    private View main;
    private ImageView imageView;
    private Button close;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //event handling thing majig
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        System.out.println("TEST");

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setTheme(R.style.HighContrastTheme);
        //setContentView(R.layout.activity_maps_outer);

//        setContentView(R.layout.activity_maps_outer);
        SharedPreferences pref = getSharedPreferences("High contrast", 0);
        ThemeSetting highcontrastmode = new ThemeSetting(pref,MapsActivity.this);
        highcontrastmode.setHighContrast(R.layout.activity_maps_outer);



        visitedClubs = new ArrayList<>();
        clubName = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        this.navDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,navDrawer,toolbar,R.string.open,R.string.close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navView = findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);

        pref1 = getSharedPreferences("ChampionPreference", 0);
        pref2 = getSharedPreferences("PremierPreference", 0);
        pref3 = getSharedPreferences("LeagueOnePreference", 0);
        pref4 = getSharedPreferences("LeagueTwoPreference", 0);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        premierLeague = getResources().getStringArray(R.array.PremierLeagueTeams);
        championLeague = getResources().getStringArray(R.array.EFLC);
        leagueOne = getResources().getStringArray(R.array.EFL1);
        leagueTwo = getResources().getStringArray(R.array.EFL2);
        getClubInfo();

        NavigationView navigationView = navView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Navigation navigation = new Navigation(item, MapsActivity.this);
                navigation.activityNavigation(getApplicationContext());
                return false;
            }
        });

        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        share = (Button)findViewById(R.id.share);
        close = (Button)findViewById(R.id.close);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bitmap bitmap = takeScreenshot();
                imageView.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);
                main.setBackgroundColor(Color.parseColor("#999999"));

                saveBitmap(bitmap);
                shareIt();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setVisibility(View.GONE  );
                close.setVisibility(View.GONE);
            }
        });

    }

        public Bitmap takeScreenshot() {
            View rootView = findViewById(android.R.id.content).getRootView();
            rootView.setDrawingCacheEnabled(true);
            return rootView.getDrawingCache();

        }

        public void saveBitmap(Bitmap bitmap) {
            imagePath = new File(this.getExternalFilesDir(null) + "/screenshot.png");
            FileOutputStream fos;

            try {
                fos = new FileOutputStream(imagePath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                Log.e("GREC", e.getMessage(), e);
            } catch (IOException e) {
                Log.e("GREC", e.getMessage(), e);
            }
        }

        private void shareIt() {
            Uri uri = FileProvider.getUriForFile(MapsActivity.this, BuildConfig.APPLICATION_ID + ".provider",imagePath);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            sharingIntent.setType("image/*");
            String shareBody = "Check out how many stadiums I've been too";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try beat me :D");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

            startActivity(Intent.createChooser(sharingIntent, "Share via"));
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
        for (int i = 0; i < championLeague.length; i++) {
            try {
                JSONObject root = new JSONObject(json);
                JSONObject visitedChampionClub = root.getJSONObject(championLeague[i]);
                String name = visitedChampionClub.getString("club name");
                double latitude = visitedChampionClub.getDouble("Latitude");
                double longitude = visitedChampionClub.getDouble("Longitude");
                LatLng location = new LatLng(latitude, longitude);
                clubName.add(name);
                visitedClubs.add(location);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < premierLeague.length; i++) {
            try {
                JSONObject root = new JSONObject(json);
                JSONObject visitedChampionClub = root.getJSONObject(premierLeague[i]);
                String name = visitedChampionClub.getString("club name");
                double latitude = visitedChampionClub.getDouble("Latitude");
                double longitude = visitedChampionClub.getDouble("Longitude");
                LatLng location = new LatLng(latitude, longitude);
                clubName.add(name);
                visitedClubs.add(location);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < leagueOne.length; i++) {
            try {
                JSONObject root = new JSONObject(json);
                JSONObject visitedChampionClub = root.getJSONObject(leagueOne[i]);
                String name = visitedChampionClub.getString("club name");
                double latitude = visitedChampionClub.getDouble("Latitude");
                double longitude = visitedChampionClub.getDouble("Longitude");
                LatLng location = new LatLng(latitude, longitude);
                clubName.add(name);
                visitedClubs.add(location);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < leagueTwo.length; i++) {
            try {
                JSONObject root = new JSONObject(json);
                JSONObject visitedChampionClub = root.getJSONObject(leagueTwo[i]);
                String name = visitedChampionClub.getString("club name");
                double latitude = visitedChampionClub.getDouble("Latitude");
                double longitude = visitedChampionClub.getDouble("Longitude");
                LatLng location = new LatLng(latitude, longitude);
                clubName.add(name);
                visitedClubs.add(location);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng defaultMap = new LatLng(52.644031, -2.321991);
        Map map = pref1.getAll();
        Map map1 = pref2.getAll();
        Map map2 = pref3.getAll();
        Map map3 = pref4.getAll();
        Iterator iterator = map.keySet().iterator();
        Iterator iterator1 = map1.keySet().iterator();
        Iterator iterator2 = map2.keySet().iterator();
        Iterator iterator3 = map3.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
//            Log.d("iterator", String.valueOf(iterator.next()));
            mMap.addMarker(new MarkerOptions()
                    .position(visitedClubs.get(Integer.parseInt(key)))
                    .visible((Boolean) map.get(key))
                    .title(clubName.get(Integer.parseInt(key))));
        }
        while (iterator1.hasNext()) {
            String key = (String) iterator1.next();
            mMap.addMarker(new MarkerOptions()
                    .position(visitedClubs.get(championLeague.length
                            + Integer.parseInt(key)))
                    .visible((Boolean) map1.get(key))
                    .title(clubName.get(championLeague.length
                            + Integer.parseInt(key))));
        }
        while (iterator2.hasNext()) {
            String key = (String) iterator2.next();
            mMap.addMarker(new MarkerOptions()
                    .position(visitedClubs.get(championLeague.length
                            + premierLeague.length
                            + Integer.parseInt(key)))
                    .visible((Boolean) map2.get(key))
                    .title(clubName.get(championLeague.length
                            + premierLeague.length
                            + Integer.parseInt(key))));
        }
        while (iterator3.hasNext()) {
            String key = (String) iterator3.next();
            mMap.addMarker(new MarkerOptions()
                    .position(visitedClubs.get(championLeague.length
                            + premierLeague.length
                            + leagueOne.length - 1
                            + Integer.parseInt(key)))
                    .visible((Boolean) map3.get(key))
                    .title(clubName.get(championLeague.length
                            + premierLeague.length
                            + leagueOne.length - 1
                            + Integer.parseInt(key))));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultMap, 6));
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}
