package com.example.c1733667.team10_football_app.classpack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.c1733667.team10_football_app.classpack.NotificationService;

/**
 * Created by c1621693 on 4/19/2018.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Log.i("Service Stops","ohhhhh");
        context.startService(new Intent(context, NotificationService.class));

    }

}
