package com.example.c1733667.team10_football_app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.c1733667.team10_football_app.R;
import com.example.c1733667.team10_football_app.classpack.Navigation;
import com.example.c1733667.team10_football_app.classpack.ThemeSetting;

import java.util.Map;
import java.util.Set;

public class SettingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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
        if (pref1 != null) {
            ThemeSetting settingTheme = new ThemeSetting(pref1, SettingActivity.this);
            settingTheme.setHighContrast(R.layout.activity_setting_outer);
        }


        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        this.navDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, navDrawer, toolbar, R.string.open, R.string.close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navView = findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);

        TextView highContrastTitle = findViewById(R.id.highContrastTitle);
        TextView textSize = findViewById(R.id.textSizeSetting);

        CheckBox HighContrastcheckBox = findViewById(R.id.highContrast);
        HighContrastcheckBox.setChecked(getSharedPreferences("High contrast",
                Context.MODE_PRIVATE)
                .getBoolean(String.valueOf(R.id.highContrast),
                        false));

        CheckBox smallTextCheckBox = findViewById(R.id.smallcheckbox);
        smallTextCheckBox.setChecked(getSharedPreferences("High contrast",
                Context.MODE_PRIVATE)
                .getBoolean(String.valueOf(R.id.smallcheckbox),
                        false));

        CheckBox mediumTextCheckBox =findViewById(R.id.mediumcheckbox);
        mediumTextCheckBox.setChecked(getSharedPreferences("High contrast",
                Context.MODE_PRIVATE)
                .getBoolean(String.valueOf(R.id.mediumcheckbox),
                        false));

        CheckBox largeTextCheckBox = findViewById(R.id.largecheckbox);
        largeTextCheckBox.setChecked(getSharedPreferences("High contrast",
                Context.MODE_PRIVATE)
                .getBoolean(String.valueOf(R.id.largecheckbox),
                        false));

//        Map map = pref1.getAll();
//        if (map.size() > 0) {
//            for (Object key : map.keySet()) {
//                if (map.get(String.valueOf(R.id.highContrast)) != null
//                        && map.get(String.valueOf(R.id.highContrast)).equals(true)) {
//                    highContrastTitle.setBackgroundColor(Color.BLUE);
//                    textSize.setBackgroundColor(Color.BLUE);
//                    HighContrastcheckBox.setBackgroundColor(Color.BLUE);
//                    smallTextCheckBox.setBackgroundColor(Color.BLUE);
//                    mediumTextCheckBox.setBackgroundColor(Color.BLUE);
//                    largeTextCheckBox.setBackgroundColor(Color.BLUE);
//                } else {
//                    highContrastTitle.setBackgroundColor(Color.WHITE);
//                    textSize.setBackgroundColor(Color.WHITE);
//                    HighContrastcheckBox.setBackgroundColor(Color.WHITE);
//                    smallTextCheckBox.setBackgroundColor(Color.WHITE);
//                    mediumTextCheckBox.setBackgroundColor(Color.WHITE);
//                    largeTextCheckBox.setBackgroundColor(Color.WHITE);
//                }
//            }
//        }
        ThemeSetting highContrastSetting = new ThemeSetting(pref1,SettingActivity.this);
        highContrastSetting.setTextviewContrast(highContrastTitle);

        ThemeSetting textSizeSetting = new ThemeSetting(pref1,SettingActivity.this);
        textSizeSetting.setTextviewContrast(textSize);

        ThemeSetting highContrastCheckbox = new ThemeSetting(pref1, SettingActivity.this);
        highContrastCheckbox.setCheckboxContrast(HighContrastcheckBox);

        ThemeSetting smallTextCheckboxContrast = new ThemeSetting(pref1,SettingActivity.this);
        smallTextCheckboxContrast.setCheckboxContrast(smallTextCheckBox);

        ThemeSetting mediumTextCheckboxContrast = new ThemeSetting(pref1,SettingActivity.this);
        mediumTextCheckboxContrast.setCheckboxContrast(mediumTextCheckBox);

        ThemeSetting largeTextCheckboxContrast = new ThemeSetting(pref1,SettingActivity.this);
        largeTextCheckboxContrast.setCheckboxContrast(largeTextCheckBox);

        NavigationView navigationView = navView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Navigation navigation = new Navigation(item, SettingActivity.this);
                navigation.activityNavigation(getApplicationContext());
                return false;
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        SharedPreferences sharedPreference = getSharedPreferences("High contrast", Context.MODE_PRIVATE);
        switch (view.getId()) {
            case R.id.highContrast:
                sharedPreference.edit().putBoolean(String.valueOf(R.id.highContrast), checked).commit();
                Intent intent = new Intent(getBaseContext(), SettingActivity.class);
                intent.putExtra("CHECK_BOX", checked);
                Log.d("CheckBox", String.valueOf(checked));
                finish();
                startActivity(intent);
                break;
            case R.id.smallcheckbox:
                sharedPreference.edit().putBoolean(String.valueOf(R.id.smallcheckbox), checked).commit();
                Intent smallIntent = new Intent(getBaseContext(), SettingActivity.class);
                smallIntent.putExtra("SMALL_CHECK", checked);
                finish();
                startActivity(smallIntent);
                break;
            case R.id.mediumcheckbox:
                sharedPreference.edit().putBoolean(String.valueOf(R.id.mediumcheckbox), checked).commit();
                Intent mediumIntent = new Intent(getBaseContext(), SettingActivity.class);
                mediumIntent.putExtra("MEDIUM_CHECK", checked);
                finish();
                startActivity(mediumIntent);
                break;
            case R.id.largecheckbox:
                sharedPreference.edit().putBoolean(String.valueOf(R.id.largecheckbox), checked).commit();
                Intent largeIntent = new Intent(getBaseContext(), SettingActivity.class);
                largeIntent.putExtra("LARGE_CHECK", checked);
                finish();
                startActivity(largeIntent);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}


