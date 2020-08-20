package com.example.c1733667.team10_football_app.classpack;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.example.c1733667.team10_football_app.R;

/**
 * Created by c1733667 on 12/04/2018.
 */

public class AchievementClass {
    private String name;
    private Integer targetNumber;
    private Integer actualNumber;
    private ProgressBar progressBar;

    public AchievementClass(String name, Integer targetNumber, Integer actualNumber) {
        this.name = name;
        this.targetNumber = targetNumber;
        this.actualNumber = actualNumber;
    }
    public boolean unlocked(){
        if (targetNumber > actualNumber){
            return false;
        }else{
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(Integer targetNumber) {
        this.targetNumber = targetNumber;
    }

    public Integer getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Integer actualNumber) {
        this.actualNumber = actualNumber;
    }

    public void createProgessBar(ProgressBar progressBar){
        Log.d("actual number", String.valueOf(actualNumber));
        Log.d("target number", String.valueOf(targetNumber));
        ObjectAnimator anim =ObjectAnimator.ofInt(progressBar, "progress", 0, (actualNumber*100)/targetNumber);
        anim.setDuration(850);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }
}
