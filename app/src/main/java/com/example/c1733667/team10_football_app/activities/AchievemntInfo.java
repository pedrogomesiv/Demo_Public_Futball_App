package com.example.c1733667.team10_football_app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.classpack.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class AchievemntInfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private TextView achievementInfo;
    private DrawerLayout navDrawer;
    private NavigationView navView;
    private ProgressBar progressBar;
    private Button btnShare;
    private Intent shareIntent = new Intent(Intent.ACTION_SEND);
    Intent intent;
    private String[] premierLeague;
    private String[] championLeague;
    private String[] leagueOne;
    private String[] leagueTwo;
    private ArrayList<String> visitedClubs;
    private ArrayList<String> theWanderer;
    boolean unlocked = false;
    int total = 0;
    int champion = 0;
    int premier = 0;
    int leagueone = 0;
    int leaguetwo = 0;
    int wanderer = 0;
    int rover = 0;
    int cymru = 0;
    int NESW = 0;
    int masterApprentice = 0;
    int northern = 0;
    int littleNLarge = 0;
    int sitDown = 0;
    int cockney = 0;
    int claretNBlue = 0;
    int city = 0;
    int ranger = 0;
    int thechampions = 0;
    int lechampions = 0;
    int eleders = 0;
    int neighbour = 0;
    int longestAndShortest = 0;
    int albion = 0;
    int athlete = 0;
    int compass = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences("High contrast", 0);
        ThemeSetting achievementInfoSetting =new ThemeSetting(pref,AchievemntInfo.this);
        achievementInfoSetting.setHighContrast(R.layout.activity_achievement_info_outer);
        toolbar = findViewById(R.id.my_toolbar);
        shareButtonListener();
        setSupportActionBar(toolbar);
        premierLeague = getResources().getStringArray(R.array.PremierLeagueTeams);
        championLeague = getResources().getStringArray(R.array.EFLC);
        leagueOne = getResources().getStringArray(R.array.EFL1);
        leagueTwo = getResources().getStringArray(R.array.EFL2);
        String achievementName = this.getIntent().getStringExtra("AchievementLogic Name");
        if (achievementName != null) {
            toolbar.setTitle(achievementName);
            getAchievementInfo();
        }
        this.navDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, navDrawer, toolbar, R.string.open, R.string.close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navView = findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);
        NavigationView navigationView =navView;

        TextView achievementInfo = findViewById(R.id.achievementInfo);

        ThemeSetting achievementContrast = new ThemeSetting(pref,AchievemntInfo.this);
        achievementContrast.setTextviewContrast(achievementInfo);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Navigation navigation = new Navigation(item,AchievemntInfo.this);
                navigation.activityNavigation(getApplicationContext());
                return false;
            }
        });

        SharedPreferences pref1 = getSharedPreferences("ChampionPreference", 0);
        SharedPreferences pref2 = getSharedPreferences("PremierPreference", 0);
        SharedPreferences pref3 = getSharedPreferences("LeagueOnePreference", 0);
        SharedPreferences pref4 = getSharedPreferences("LeagueTwoPreference", 0);

        Map map = pref1.getAll();
        Iterator iterator = map.keySet().iterator();
        for (Object key : map.keySet()) {
            if ((Boolean) map.get((String) key).equals(true)) {
                total = total + 1;
//                champion = champion + 1;
            }
            if (map.get(key).equals(true)
                    && (championLeague[Integer.parseInt(String.valueOf(key))].equals("Wolverhampton Wanderers")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Bolton Wanderers"))) {
                wanderer = wanderer + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Cardiff City")) {
                cymru = cymru + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Norwich City")) {
                NESW = NESW + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Sheffield United")) {
                masterApprentice = masterApprentice + 1;
            }
            if (map.get(key).equals(true)
                    && (championLeague[Integer.parseInt(String.valueOf(key))].equals("Sunderland")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Middlesbrough")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Preston North End")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Leeds United")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Hull City")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Sheffield United")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Sheffield Wednesday")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Barnsley")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Bolton Wanderers"))) {
                Log.d("championTeam", championLeague[Integer.parseInt(String.valueOf(key))]);
                northern = northern + 1;
            }
            if (map.get(key).equals(true)
                    && (championLeague[Integer.parseInt(String.valueOf(key))].equals("Millwall")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Queens Park Rangers")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Fulham")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Brentford"))) {
                cockney = cockney + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Aston Villa")) {
                claretNBlue = claretNBlue + 1;
            }
            if (map.get(key).equals(true)
                    && (championLeague[Integer.parseInt(String.valueOf(key))].equals("Cardiff City")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Birmingham City")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Hull City")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Norwich City"))) {
                city = city + 1;
            }
            if (map.get(key).equals(true)
                    && (championLeague[Integer.parseInt(String.valueOf(key))].equals("Aston Villa")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Brentford")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Derby County"))) {
                ranger = ranger + 1;
            }
            if (map.get(key).equals(true)
                    && (championLeague[Integer.parseInt(String.valueOf(key))].equals("Aston Villa")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Nottingham Forest"))) {
                lechampions = lechampions + 1;
            }
            if (map.get(key).equals(true)
                    && (championLeague[Integer.parseInt(String.valueOf(key))].equals("Sheffield United")
                    || championLeague[Integer.parseInt(String.valueOf(key))].equals("Preston North End"))) {
                eleders = eleders + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Nottingham Forest")) {
                neighbour = neighbour + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Wolverhampton Wanderers")) {
                longestAndShortest = longestAndShortest + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Burton Albion")) {
                albion = albion + 1;
            }
            if (map.get(key).equals(true)
                    && championLeague[Integer.parseInt(String.valueOf(key))].equals("Preston North End")) {
                compass = compass + 1;
            }

        }
        Map map2 = pref2.getAll();
        Iterator iterator1 = map2.keySet().iterator();
        for (Object key : map2.keySet()) {
            Log.d("Team", premierLeague[Integer.parseInt(String.valueOf(key))]);
            if ((Boolean) map2.get((String) key).equals(true)) {
                total = total + 1;
                premier = premier + 1;
            }
            if (map2.get(key).equals(true)
                    && premierLeague[Integer.parseInt(String.valueOf(key))].equals("Swansea City")) {
                cymru = cymru + 1;
            }
            if (map2.get(key).equals(true)
                    && premierLeague[Integer.parseInt(String.valueOf(key))].equals("Newcastle United")) {
                NESW = NESW + 1;
            }
            if (map2.get(key).equals(true)
                    && premierLeague[Integer.parseInt(String.valueOf(key))].equals("West Ham")) {
                masterApprentice = masterApprentice + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("Newcastle United")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Manchester United")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Manchester City")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Liverpool")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Burnley")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Everton")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Huddersfield Town"))) {
                northern = northern + 1;
            }
            if (map2.get(key).equals(true)
                    && premierLeague[Integer.parseInt(String.valueOf(key))].equals("Tottemham Hotspurs")) {
                littleNLarge = littleNLarge + 1;
            }
            if (map2.get(key).equals(true)
                    && premierLeague[Integer.parseInt(String.valueOf(key))].equals("Newcastle United")) {
                sitDown = sitDown + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("Arsenal")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Chelsea")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Crystal Palace")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Tottemham Hotspurs")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("West Ham"))) {
                cockney = cockney + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("West Ham")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Burnley"))) {
                claretNBlue = claretNBlue + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("Manchester City")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Leicester City")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Swansea City")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Stoke City"))) {
                city = city + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("Crystal Palace")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Everton")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Newcastle United"))) {
                ranger = ranger + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("Arsenal")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Chelsea")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Manchester United")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Manchester City")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Leicester City"))) {
                thechampions = thechampions + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("Manchester United")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Liverpool")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Chelsea"))) {
                lechampions = lechampions + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("Chelsea")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Newcastle United"))) {
                eleders = eleders + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("West Brom")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Brighton"))) {
                albion = albion + 1;
            }
            if (map2.get(key).equals(true)
                    && (premierLeague[Integer.parseInt(String.valueOf(key))].equals("West Ham")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("West Brom")
                    || premierLeague[Integer.parseInt(String.valueOf(key))].equals("Southampton"))) {
                compass = compass + 1;
            }
        }
        Map map3 = pref3.getAll();
        Iterator iterator2 = map3.keySet().iterator();
        for (Object key : map3.keySet()) {
            if ((Boolean) map3.get((String) key).equals(true)) {
                total = total + 1;
                leagueone = leagueone + 1;
            }
            if (map3.get(key).equals(true)
                    && (leagueOne[Integer.parseInt(String.valueOf(key))].equals("Blackburn Rovers")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Bristol Rovers")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Doncaster Rovers"))) {
                rover = rover + 1;
            }
            if (map3.get(key).equals(true)
                    && leagueOne[Integer.parseInt(String.valueOf(key))].equals("Plymouth Argyle")) {
                NESW = NESW + 1;
            }
            if (map3.get(key).equals(true)
                    && (leagueOne[Integer.parseInt(String.valueOf(key))].equals("Fleetwood Town")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Blackpool")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Blackburn Rovers")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Bradford City")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Scunthorpe United")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Doncaster Rovers")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Rotherham United")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Bury")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Rochdale")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Wigan Athletic")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Oldham Athletic"))) {
                Log.d("champion Team", leagueOne[Integer.parseInt(String.valueOf(key))]);
                northern = northern + 1;
            }
            if (map3.get(key).equals(true)
                    && leagueOne[Integer.parseInt(String.valueOf(key))].equals("AFC Wimbledon")) {
                littleNLarge = littleNLarge + 1;
            }
            if (map3.get(key).equals(true)
                    && (leagueOne[Integer.parseInt(String.valueOf(key))].equals("AFC Wimbledon")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Charlton Athletic"))) {
                cockney = cockney + 1;
            }
            if (map3.get(key).equals(true)
                    && leagueOne[Integer.parseInt(String.valueOf(key))].equals("Scunthorpe United")) {
                claretNBlue = claretNBlue + 1;
            }
            if (map3.get(key).equals(true)
                    && leagueOne[Integer.parseInt(String.valueOf(key))].equals("Bradford City")) {
                city = city + 1;
            }
            if (map3.get(key).equals(true)
                    && (leagueOne[Integer.parseInt(String.valueOf(key))].equals("Blackburn Rovers")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Oldham Athletic")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Plymouth Argyle")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Portsmouth")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Scunthorpe United"))) {
                ranger = ranger + 1;
            }
            if (map3.get(key).equals(true)
                    && leagueOne[Integer.parseInt(String.valueOf(key))].equals("Blackburn Rovers")) {
                thechampions = thechampions + 1;
            }
            if (map3.get(key).equals(true)
                    && leagueOne[Integer.parseInt(String.valueOf(key))].equals("Bury")) {
                longestAndShortest = longestAndShortest + 1;
            }
            if (map3.get(key).equals(true)
                    && (leagueOne[Integer.parseInt(String.valueOf(key))].equals("Oldham Athletic")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Wigan Athletic")
                    || leagueOne[Integer.parseInt(String.valueOf(key))].equals("Charlton Athletic"))) {
                athlete = athlete + 1;
            }
            if (map3.get(key).equals(true)
                    && (leagueOne[Integer.parseInt(String.valueOf(key))].equals("Northampton Town")
                    ||leagueOne[Integer.parseInt(String.valueOf(key))].equals("Southend United"))) {
                compass = compass + 1;
            }
        }
        Map map4 = pref4.getAll();
        Iterator iterator3 = map4.keySet().iterator();
        for (Object key : map4.keySet()) {
            if ((Boolean) map4.get((String) key).equals(true)) {
                total = total + 1;
                leaguetwo = leaguetwo + 1;
            }
            if (map4.get(key).equals(true)
                    && leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Wycombe Wanderers")) {
                wanderer = wanderer + 1;
            }
            if (map4.get(key).equals(true)
                    && leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Forest Green Rovers")) {
                rover = rover + 1;
            }
            if (map4.get(key).equals(true)
                    && leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Newport County")) {
                cymru = cymru + 1;
            }
            if (map4.get(key).equals(true)
                    && (leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Accrington Stanley")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Morcambe")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Grimsby Town")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Carlisle United"))) {
                Log.d("league 2 Team", leagueTwo[Integer.parseInt(String.valueOf(key))]);
                northern = northern + 1;
            }
            if (map4.get(key).equals(true)
                    && leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Exeter City")) {
                sitDown = sitDown + 1;
            }
            if (map4.get(key).equals(true)
                    && leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Barnet")) {
                cockney = cockney + 1;
            }
            if (map4.get(key).equals(true)
                    && (leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Coventry City")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Exeter City")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Lincoln City"))) {
                city = city + 1;
            }
            if (map4.get(key).equals(true)
                    && (leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Carlisle United")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Exeter City")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Grimsby Town")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Port Vale")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Wycombe Wanderers")
                    || leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Yeovil Town"))) {
                ranger = ranger + 1;
            }
            if (map4.get(key).equals(true)
                    && leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Mansfield Town")) {
                eleders = eleders + 1;
            }
            if (map4.get(key).equals(true)
                    && leagueTwo[Integer.parseInt(String.valueOf(key))].equals("Notts County")) {
                neighbour = neighbour + 1;
            }
        }

        if (achievementName.equals("Over 50%")) {
            AchievementClass overFifty = new AchievementClass("Over 50%", 46, total);
            overFifty.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = overFifty.unlocked();
        }
        if (achievementName.equals("Over 60%")) {
            Log.d("Total", String.valueOf(total));
            AchievementClass overSixty = new AchievementClass("Over 60%", 55, total);
            overSixty.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = overSixty.unlocked();
        }
        if (achievementName.equals("Over 70%")) {
            AchievementClass overSeventy = new AchievementClass("Over 70%", 65, total);
            overSeventy.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = overSeventy.unlocked();
        }
        if (achievementName.equals("Over 80%")) {
            AchievementClass overEighty = new AchievementClass("Over 80%", 74, total);
            overEighty.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = overEighty.unlocked();
        }
        if (achievementName.equals("Over 90%")) {
            AchievementClass overNinety = new AchievementClass("Over 90%", 83, total);
            overNinety.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = overNinety.unlocked();
        }
        if (achievementName.equals("Done the lot!")) {
            AchievementClass doneTheLot = new AchievementClass("Done The Lot", 92, total);
            doneTheLot.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = doneTheLot.unlocked();
        }
        if (achievementName.equals("The Wanderer")) {
            AchievementClass theWanderer = new AchievementClass("The Wanderer", 3, wanderer);
            theWanderer.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = theWanderer.unlocked();
        }
        if (achievementName.equals("The Wild Rover")) {
            AchievementClass theWildRover = new AchievementClass("The Wild Rover", 4, rover);
            theWildRover.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = theWildRover.unlocked();
        }
        if (achievementName.equals("Cymru am byth")) {
            AchievementClass cymruAmByth = new AchievementClass("Cymru am byth", 3, cymru);
            cymruAmByth.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = cymruAmByth.unlocked();
        }
        if (achievementName.equals("Far and Wide")) {
            AchievementClass farAndWide = new AchievementClass("Far and Wide", 3, NESW);
            farAndWide.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = farAndWide.unlocked();
        }
        if (achievementName.equals("Master and Apprentice")) {
            AchievementClass masterAndApprentice = new AchievementClass("Master and Apprentice", 2, masterApprentice);
            masterAndApprentice.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = masterAndApprentice.unlocked();
        }
        if (achievementName.equals("Oop North")) {
            AchievementClass oopNorth = new AchievementClass("Oop North", 31, northern);
            oopNorth.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = oopNorth.unlocked();
        }
        if (achievementName.equals("Perfect Premier")) {
            AchievementClass perfectPremier = new AchievementClass("Perfect Premier", 20, premier);
            perfectPremier.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = perfectPremier.unlocked();
        }
        if (achievementName.equals("Championship Champ")) {
            AchievementClass championshipChamp = new AchievementClass("Championship Champ", 24, champion);
            championshipChamp.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = championshipChamp.unlocked();
        }
        if (achievementName.equals("League 1 Done")) {
            AchievementClass leagueOneDone = new AchievementClass("League one done", 24, leagueone);
            leagueOneDone.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = leagueOneDone.unlocked();
        }
        if (achievementName.equals("League 2 Triumphed")) {
            AchievementClass leagueTwoTriumphed = new AchievementClass("League two triumphed", 24, leaguetwo);
            leagueTwoTriumphed.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = leagueTwoTriumphed.unlocked();
        }
        if (achievementName.equals("Little and large")) {
            AchievementClass littleAndLarge = new AchievementClass("Little and Large", 2, littleNLarge);
            littleAndLarge.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = littleAndLarge.unlocked();
        }
        if (achievementName.equals("Cockney Conqueror")) {
            AchievementClass cockneyConqueror = new AchievementClass("Cockney Conqueror", 12, cockney);
            cockneyConqueror.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = cockneyConqueror.unlocked();
        }
        if (achievementName.equals("All sit down")) {
            AchievementClass allSitDown = new AchievementClass("All sit down", 2, sitDown);
            allSitDown.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = allSitDown.unlocked();
        }
        if (achievementName.equals("Claret and Blue")) {
            AchievementClass claretAndBlue = new AchievementClass("Claret and blue", 4, claretNBlue);
            claretAndBlue.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = claretAndBlue.unlocked();
        }
        if (achievementName.equals("City Slicker")) {
            AchievementClass citySlicker = new AchievementClass("City Slicker", 12, city);
            citySlicker.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = citySlicker.unlocked();
        }
        if (achievementName.equals("Park Ranger")) {
            AchievementClass parkRanger = new AchievementClass("Park Ranger", 17, ranger);
            parkRanger.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = parkRanger.unlocked();
        }
        if (achievementName.equals("The Champions")) {
            AchievementClass theChampions = new AchievementClass("The Chhampions", 6, thechampions);
            theChampions.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = theChampions.unlocked();
        }
        if (achievementName.equals("Le Champions")) {
            AchievementClass leChampions = new AchievementClass("Le Champions", 5, lechampions);
            leChampions.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = leChampions.unlocked();
        }
        if (achievementName.equals("Respect your Elders")) {
            AchievementClass respectYourElders = new AchievementClass("Respect your Elders", 5, eleders);
            respectYourElders.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = respectYourElders.unlocked();
        }
        if (achievementName.equals("Neighbours")) {
            AchievementClass neighbours = new AchievementClass("Neighbours", 2, neighbour);
            neighbours.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = neighbours.unlocked();
        }
        if (achievementName.equals("Longest and Shortest")) {
            AchievementClass longestAndshortest = new AchievementClass("Longest and Shortest", 2, longestAndShortest);
            longestAndshortest.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = longestAndshortest.unlocked();
        }
        if (achievementName.equals("What even is an Albion?!")) {
            AchievementClass whatIsAnAlbion = new AchievementClass("What even is an Albion", 3, albion);
            whatIsAnAlbion.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = whatIsAnAlbion.unlocked();
        }
        if (achievementName.equals("Im an athlete!")) {
            AchievementClass imAnAthlete = new AchievementClass("Im an Athlete", 3, athlete);
            imAnAthlete.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = imAnAthlete.unlocked();
        }
        if (achievementName.equals("The Compass")) {
            AchievementClass theCompass = new AchievementClass("The compass", 6, compass);
            theCompass.createProgessBar(progressBar = findViewById(R.id.achievementProgressBar));
            unlocked = theCompass.unlocked();
        }
        if (!unlocked){
            btnShare.setClickable(false);
            btnShare.setTextColor(Color.parseColor("#A0A0A0"));
        }
    }


    public void getAchievementInfo() {
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.achievement);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        parseJson(builder.toString());
    }

    private void parseJson(String json) {
        achievementInfo = (TextView) findViewById(R.id.achievementInfo);
        String achievementName = this.getIntent().getStringExtra("AchievementLogic Name");
        StringBuilder info = new StringBuilder();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject achievementInfo = root.getJSONObject(achievementName);
            info.append("Description:")
                    .append(achievementInfo.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        achievementInfo.setText(info.toString());
    }
    public String getAchievementDetails(String field){
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.achievement);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        StringBuilder r = new StringBuilder();
        try {
            JSONObject root = new JSONObject(builder.toString());
            JSONObject achievementInfo = root.getJSONObject(this.getIntent().getStringExtra("AchievementLogic Name"));
            r.append(achievementInfo.getString(field));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r.toString();

    }
    public void shareButtonListener() {
        btnShare = findViewById(R.id.btnShareAch);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unlocked){
                    shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareContent = (getAchievementDetails("Share"));
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);

                    startActivity(Intent.createChooser(shareIntent, "Share via:"));
                }else{
                }
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}
