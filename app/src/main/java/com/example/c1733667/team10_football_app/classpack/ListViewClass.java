package com.example.c1733667.team10_football_app.classpack;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.activities.StadiumActivity;

import java.util.Map;

/**
 * Created by c1733667 on 25/04/2018.
 */

public class ListViewClass {
    private SharedPreferences sharedPreferences;
    private AppCompatActivity activity;

    public ListViewClass(SharedPreferences sharedPreferences, AppCompatActivity activity) {
        this.sharedPreferences = sharedPreferences;
        this.activity = activity;
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
//                    listView.setOnItemClickListener(this);
                } else
                    listView.setBackgroundColor(Color.WHITE);
//                listView.setOnItemClickListener(this);
            }
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(activity, String.format("Item clicked on = %d", position), Toast.LENGTH_SHORT).show();
//        LeagueSelector selector = new LeagueSelector(position);
//        selector.selectLeague(position, activity);
//    }

    public void onItemClick(StadiumActivity stadiumActivity) {
    }

    public void setItemChecked(Integer integer, Boolean aBoolean) {
    }
}
