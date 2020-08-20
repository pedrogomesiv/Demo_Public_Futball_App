package com.example.c1733667.team10_football_app.classpack;



import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.c1733667.team10_football_app.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by c1621693 on 4/19/2018.
 */

public class NotificationService extends Service {

    public static final String EXAMPLE_CHANNEL_ID = "type1";
    private NotificationManager nManager;
    private Timer  mTimer;
    private TimerTask timerTask;

    @Override
    public IBinder onBind(Intent intent){
        return null;

    }

    @Override
    public void onCreate(){

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.e("Log", "Running");
                notifiy();
            }
        };

        super.onCreate();
//        mTimer = new Timer();
//        mTimer.schedule(timerTask,2000, 2 * 1000);




    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        try {

        }catch(Exception e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }





    public void onDestroy() {
        try {
            mTimer = new Timer();
            mTimer.schedule(timerTask,3600000*24 , 3600000*24);
        }catch(Exception e){
            e.printStackTrace();
        }
        Intent intent = new Intent("com.example.c1733667.team10_football_app.activities");
        intent.putExtra("yourvalue", "torestore");
        sendBroadcast(intent);
    }




    public void notifiy(){
        Context c = this.getApplicationContext();

        nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            if (nManager != null && nManager.getNotificationChannel(EXAMPLE_CHANNEL_ID)== null){

                NotificationChannel channel = new NotificationChannel(
                        EXAMPLE_CHANNEL_ID,
                        "my_example_chanel",
                        NotificationManager.IMPORTANCE_DEFAULT);


                channel.setDescription("Using as an example");
                nManager.createNotificationChannel(channel);

            }
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(c, EXAMPLE_CHANNEL_ID)
               .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Team 10 FootBall")
                .setContentText("Let's check some stadiums off your list")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notificationCompat =mBuilder.build();
        nManager.notify(0,notificationCompat);



    }

}
