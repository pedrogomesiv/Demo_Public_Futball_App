package com.example.c1733667.team10_football_app.classpack;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.activities.MainActivity;

import java.util.Map;

/**
 * Created by c1733667 on 22/04/2018.
 */

public class ThemeSetting {
    private SharedPreferences sharedPreferences;
    private AppCompatActivity activity;

    public ThemeSetting(SharedPreferences sharedPreferences, AppCompatActivity activity) {
        this.sharedPreferences = sharedPreferences;
        this.activity = activity;
    }


    public void setTheme(int style, int contentView, Map map) {
        activity.setTheme(style);
        setTextTheme(map);
        activity.setContentView(contentView);
    }

    public void setTextTheme(Map map) {
        if (map.get(String.valueOf(R.id.smallcheckbox)) != null
                && map.get(String.valueOf(R.id.smallcheckbox)).equals(true)) {
            activity.setTheme(R.style.smallText);

        }
        if (map.get(String.valueOf(R.id.mediumcheckbox)) != null
                && map.get(String.valueOf(R.id.mediumcheckbox)).equals(true)) {
            activity.setTheme(R.style.mediumText);
        }
        if (map.get(String.valueOf(R.id.largecheckbox)) != null
                && map.get(String.valueOf(R.id.largecheckbox)).equals(true)) {
            activity.setTheme(R.style.largeText);
            System.out.println("SETTING LARGE TEXT");
        }
    }

    public void setHighContrast(int contentView) {
        Map map = sharedPreferences.getAll();
        if (map.size() > 0) {
            if (map.get(String.valueOf(R.id.highContrast)) != null
                    && map.get(String.valueOf(R.id.highContrast)).equals(true)) {

                setTheme(
                        R.style.HighContrastTheme,
                        contentView,
                        map
                );

            } else {

                setTheme(
                        R.style.AppTheme,
                        contentView,
                        map
                );
            }
        } else {
            activity.setTheme(R.style.AppTheme);
            activity.setContentView(contentView);
        }
    }

    public void setTextviewContrast(TextView textviewContrast) {
        Map map = sharedPreferences.getAll();
        if (map.size() > 0) {
            for (Object key : map.keySet()) {
                if (map.get(String.valueOf(R.id.highContrast)) != null
                        && map.get(String.valueOf(R.id.highContrast)).equals(true)) {
                    textviewContrast.setBackgroundColor(Color.BLUE);
                } else {
                    textviewContrast.setBackgroundColor(Color.WHITE);
                }
            }
        }
    }

    public void setListView(int lv, ArrayAdapter adapter) {
        ListViewCompat listView = activity.findViewById(lv);
        listView.setAdapter(adapter);
        Map map = sharedPreferences.getAll();
        if (map.size() > 0) {
            for (Object key : map.keySet()) {
                if (map.get(String.valueOf(R.id.highContrast)) != null
                        && map.get(String.valueOf(R.id.highContrast)).equals(true)) {
                    listView.setBackgroundColor(Color.BLUE);
                } else
                    listView.setBackgroundColor(Color.WHITE);
            }
        }
    }

    public void setCheckboxContrast(CheckBox checkBox) {
        Map map = sharedPreferences.getAll();
        if (map.size() > 0) {
            for (Object key : map.keySet()) {
                if (map.get(String.valueOf(R.id.highContrast)) != null
                        && map.get(String.valueOf(R.id.highContrast)).equals(true)) {
                    checkBox.setBackgroundColor(Color.BLUE);
                } else
                    checkBox.setBackgroundColor(Color.WHITE);
            }
        }
    }
}
