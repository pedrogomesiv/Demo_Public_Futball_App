package com.example.c1733667.team10_football_app.classpack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.c1733667.team10_football_app.activities.Achievement;
import com.example.c1733667.team10_football_app.activities.HelpActivity;
import com.example.c1733667.team10_football_app.activities.MapsActivity;
import com.example.c1733667.team10_football_app.activities.Score;
import com.example.c1733667.team10_football_app.activities.StadiumActivity;

/**
 * Created by c1733667 on 17/04/2018.
 */

public class ButtonClass {
    private String name;
    private Intent intent;

    public ButtonClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void stadiumButtonListener(Button btnStadium, final Context context) {
        btnStadium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, StadiumActivity.class);
                context.startActivity(intent);
            }
        });
    }
    public void helpButtonListener(Button btnHelp, final Context context) {
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, HelpActivity.class);
                context.startActivity(intent);
            }
        });
    }
    public void scoreButtonListener(Button btnScore, final Context context) {
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, Score.class);
                context.startActivity(intent);
            }
        });
    }
    public void mapbuttonListener(Button btnMap, final Context context) {
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, MapsActivity.class);
                context.startActivity(intent);
            }
        });
    }
    public void achievementButtonListener(Button btnAchievement, final Context context) {
        btnAchievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, Achievement.class);
                context.startActivity(intent);
            }
        });
    }
}
