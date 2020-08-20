package com.example.c1733667.team10_football_app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.classpack.ScoreSystem;
import com.example.c1733667.team10_football_app.classpack.Navigation;
import com.example.c1733667.team10_football_app.classpack.ThemeSetting;

import java.util.Map;


public class Score extends AppCompatActivity implements AdapterView.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private Button btnShare;
    private int totalStadiumsVisited ;
    private Intent shareIntent = new Intent(Intent.ACTION_SEND);
    ProgressBar mprogressBar;
    ProgressBar mprogressBar2;
    ProgressBar mprogressBar3;
    ProgressBar mprogressBar4;
    ProgressBar mprogressBar5;
    DrawerLayout navDrawer;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences setting = getSharedPreferences("High contrast", 0);
        ThemeSetting scoreSetting = new ThemeSetting(setting,Score.this);
        scoreSetting.setHighContrast(R.layout.activity_scoring_system_outer);

        shareButtonListener();

        SharedPreferences championPreference = getSharedPreferences("ChampionPreference", 0);
        SharedPreferences premierPreference = getSharedPreferences("PremierPreference", 0);
        SharedPreferences leagueOnePreference = getSharedPreferences("LeagueOnePreference", 0);
        SharedPreferences leagueTwoPreference = getSharedPreferences("LeagueTwoPreference", 0);

        ScoreSystem scoreSystem = new ScoreSystem(championPreference, premierPreference, leagueOnePreference, leagueTwoPreference);

        int championsLeagueStadiumsVisited = scoreSystem.getChampionsLeagueStadiumsVisited();
        int premierLeagueStadiumsVisited = scoreSystem.getPremierLeagueStadiumsVisited();
        int leagueOneStadiumsVisited = scoreSystem.getLeagueOneStadiumsVisited();
        int leagueTwoStadiumsVisited = scoreSystem.getLeagueTwoStadiumsVisited();
        long totalPercentageOfStadiumsVisited = scoreSystem.getTotalPercentageOfStadiumsVisited();
        totalStadiumsVisited = scoreSystem.getTotalStadiumsVisited();



        TextView textView = (TextView) findViewById(R.id.textViewName);
        textView.setText("Your percentage of clubs you have been too :" + totalPercentageOfStadiumsVisited + "%");
        TextView textView2 = (TextView) findViewById(R.id.textViewName2);
        textView2.setText("Your Score is :" + totalStadiumsVisited );
        TextView textView3 = (TextView) findViewById(R.id.championsleagueperc);
        textView3.setText(championsLeagueStadiumsVisited+"/24");

        TextView textView4 = (TextView) findViewById(R.id.premierleagueperc);
        textView4.setText(premierLeagueStadiumsVisited +"/20");
        TextView textView5 = (TextView) findViewById(R.id.leagueoneperc);
        textView5.setText(leagueOneStadiumsVisited+"/24");
        TextView textView6 = (TextView) findViewById(R.id.leaguetwoperc);
        textView6.setText(leagueTwoStadiumsVisited+"/24");

        ThemeSetting textViewContrast = new ThemeSetting(setting,Score.this);
        textViewContrast.setTextviewContrast(textView);

        ThemeSetting textView2Contrast = new ThemeSetting(setting,Score.this);
        textView2Contrast.setTextviewContrast(textView2);
//
//        ThemeSetting textView3Contrast = new ThemeSetting(setting,Score.this);
//        textView3Contrast.setTextviewContrast(textView3);
//
//        ThemeSetting textView4Contrast = new ThemeSetting(setting,Score.this);
//        textView4Contrast.setTextviewContrast(textView4);
//
//        ThemeSetting textView5Contrast = new ThemeSetting(setting,Score.this);
//        textView5Contrast.setTextviewContrast(textView5);
//
//        ThemeSetting textView6Contrast = new ThemeSetting(setting,Score.this);
//        textView6Contrast.setTextviewContrast(textView6);

        TextView premierTextView = findViewById(R.id.premiertitle);
        TextView championTextView = findViewById(R.id.championtitle);
        TextView leagueOneTextView = findViewById(R.id.leagueonetitle);
        TextView leagueTwoTextView = findViewById(R.id.leaguetwotitle);

        ThemeSetting premierTitle = new ThemeSetting(setting,Score.this);
        premierTitle.setTextviewContrast(premierTextView);

        ThemeSetting championTitle = new ThemeSetting(setting,Score.this);
        championTitle.setTextviewContrast(championTextView);

        ThemeSetting leagueOneTitle = new ThemeSetting(setting,Score.this);
        leagueOneTitle.setTextviewContrast(leagueOneTextView);

        ThemeSetting leagueTwoTitle = new ThemeSetting(setting,Score.this);
        leagueTwoTitle.setTextviewContrast(leagueTwoTextView);



        mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, championsLeagueStadiumsVisited);
        anim.setDuration(850);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        mprogressBar2 = (ProgressBar) findViewById(R.id.circular_progress_bar2);
        ObjectAnimator anim2 = ObjectAnimator.ofInt(mprogressBar2, "progress", 0, premierLeagueStadiumsVisited);
        anim2.setDuration(850);
        anim2.setInterpolator(new DecelerateInterpolator());
        anim2.start();

        mprogressBar3 = (ProgressBar) findViewById(R.id.circular_progress_bar3);
        ObjectAnimator anim3 = ObjectAnimator.ofInt(mprogressBar3, "progress", 0, leagueOneStadiumsVisited);
        anim3.setDuration(850);
        anim3.setInterpolator(new DecelerateInterpolator());
        anim3.start();

        mprogressBar4 = (ProgressBar) findViewById(R.id.circular_progress_bar4);
        ObjectAnimator anim4 = ObjectAnimator.ofInt(mprogressBar4, "progress", 0, leagueTwoStadiumsVisited);
        anim4.setDuration(850);
        anim4.setInterpolator(new DecelerateInterpolator());
        anim4.start();

        mprogressBar5 = (ProgressBar) findViewById(R.id.progress_bar);
        ObjectAnimator anim5 = ObjectAnimator.ofInt(mprogressBar5, "progress", 0, totalStadiumsVisited);
        anim5.setDuration(850);
        anim5.setInterpolator(new DecelerateInterpolator());
        anim5.start();


        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        this.navDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,navDrawer,toolbar,R.string.open,R.string.close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navView = findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);

        NavigationView navigationView = navView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Navigation navigation = new Navigation(item, Score.this);
                navigation.activityNavigation(getApplicationContext());
                return false;
            }
        });


    }




    @Override
    public void onClick(View v) {

    }
    public void shareButtonListener() {
        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareContent = ("I have visited "+ totalStadiumsVisited  +" out of the 92 football stadiums in the UK!\nHow many have you visited?");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);

                startActivity(Intent.createChooser(shareIntent, "Share via:"));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
