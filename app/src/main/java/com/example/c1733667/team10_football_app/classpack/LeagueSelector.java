package com.example.c1733667.team10_football_app.classpack;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.c1733667.team10_football_app.activities.ChampionshipLeague;
import com.example.c1733667.team10_football_app.activities.LeagueOne;
import com.example.c1733667.team10_football_app.activities.LeagueTwo;
import com.example.c1733667.team10_football_app.activities.PremierLeague;

/**
 * Created by c1733667 on 16/04/2018.
 */

public class LeagueSelector extends AppCompatActivity {

    private Intent intent;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public LeagueSelector(int position) {
        this.position = position;
    }

    public void selectLeague(int position, Context context) {
        switch (position) {
            case 0:
                intent = new Intent(context, PremierLeague.class);
                context.startActivity(intent);
                break;
            case 1:
                intent = new Intent(context, ChampionshipLeague.class);
                context.startActivity(intent);
                break;
            case 2:
                intent = new Intent(context, LeagueOne.class);
                context.startActivity(intent);
                break;
            case 3:
                intent = new Intent(context, LeagueTwo.class);
                context.startActivity(intent);
                break;
        }
    }
}
