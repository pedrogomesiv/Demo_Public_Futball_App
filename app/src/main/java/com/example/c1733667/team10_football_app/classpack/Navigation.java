package com.example.c1733667.team10_football_app.classpack;

import com.example.c1733667.team10_football_app.activities.HelpActivity;
/**
 * Created by c1733667 on 19/04/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.activities.Achievement;
import com.example.c1733667.team10_football_app.activities.HelpActivity;
import com.example.c1733667.team10_football_app.activities.InfoActivity;
import com.example.c1733667.team10_football_app.activities.MainActivity;
import com.example.c1733667.team10_football_app.activities.MapsActivity;
import com.example.c1733667.team10_football_app.activities.Score;
import com.example.c1733667.team10_football_app.activities.SettingActivity;
import com.example.c1733667.team10_football_app.activities.StadiumActivity;

/**
 * Created by c1733667 on 22/04/2018.
 */

public class Navigation extends Activity {
    private Intent intent;
    private MenuItem menuItem;
    Activity activity;

    public Navigation(MenuItem menuItem, Activity activity) {
        this.menuItem = menuItem;
        this.activity = activity;
    }

    public void activityNavigation(Context context) {
        switch (menuItem.getItemId()) {
            case R.id.stad:
                intent = new Intent(context, StadiumActivity.class);
                context.startActivity(intent);
                break;

            case R.id.scores:
                intent = new Intent(context, Score.class);
                context.startActivity(intent);
                break;


            case R.id.maps:
                intent = new Intent(context, MapsActivity.class);
                context.startActivity(intent);
                break;


            case R.id.exit:
                System.exit(0);


            case R.id.home:
                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;

            case R.id.achievements:
                intent = new Intent(context, Achievement.class);
                context.startActivity(intent);
                break;

            case R.id.settings:
                intent = new Intent(context, SettingActivity.class);
                context.startActivity(intent);
                break;
            case R.id.help:
                intent = new Intent(context, HelpActivity.class);
                context.startActivity(intent);
                break;

        }
    }
}
