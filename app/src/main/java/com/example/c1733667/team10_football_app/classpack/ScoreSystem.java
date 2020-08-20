package com.example.c1733667.team10_football_app.classpack;

import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by c1621693 on 4/17/2018.
 */

public class ScoreSystem {
    private int championsLeagueStadiumsVisited;
    private int premierLeagueStadiumsVisited;
    private int leagueOneStadiumsVisited;
    private int leagueTwoStadiumsVisited;
    private int totalStadiumsVisited;
    private long totalPercentageOfStadiumsVisited;




    // The constructor uses the  sharedprefereces as arguments to call the methods to caculated the data of stadiums visited.
    public ScoreSystem(SharedPreferences championPreference, SharedPreferences premierPreference, SharedPreferences leagueOnePreference, SharedPreferences leagueTwoPreference) {
        this.championsLeagueStadiumsVisited = this.calculateStadiumsVisited(championPreference);
        this.premierLeagueStadiumsVisited = this.calculateStadiumsVisited(premierPreference);
        this.leagueOneStadiumsVisited = this.calculateStadiumsVisited(leagueOnePreference);
        this.leagueTwoStadiumsVisited = this.calculateStadiumsVisited(leagueTwoPreference);


        this.totalStadiumsVisited = this.championsLeagueStadiumsVisited + this.premierLeagueStadiumsVisited + this.leagueOneStadiumsVisited + this.leagueTwoStadiumsVisited;

        this.totalPercentageOfStadiumsVisited = this.calculateTotalPercentage(this.totalStadiumsVisited);

    }




    // this method calculates the total of stadiums visited for each league.
    public int calculateStadiumsVisited(SharedPreferences sharedPreferences) {

        Map map = sharedPreferences.getAll();
        int total = 0;

        for (Object key: map.keySet()){
            if( (Boolean) map.get((String) key).equals(true)){
                total=total +1;
            }
        }

        return total;
    }

    // this methods calculates the total percentage of stadiums visisted.
    public long calculateTotalPercentage(int totalStadiumsVisited){

        long totalPerc = (long) Math.floor(100 * totalStadiumsVisited);
        long totalPerc1 = (long) Math.floor(totalPerc / 92 );


        return totalPerc1;
    }

    public int getChampionsLeagueStadiumsVisited() {
        return championsLeagueStadiumsVisited;
    }

    public int getPremierLeagueStadiumsVisited() {
        return premierLeagueStadiumsVisited;
    }

    public int getLeagueOneStadiumsVisited() {
        return leagueOneStadiumsVisited;
    }

    public int getLeagueTwoStadiumsVisited() {
        return leagueTwoStadiumsVisited;
    }

    public int getTotalStadiumsVisited() {
        return totalStadiumsVisited;
    }

    public long getTotalPercentageOfStadiumsVisited() {
        return totalPercentageOfStadiumsVisited;
    }
}
