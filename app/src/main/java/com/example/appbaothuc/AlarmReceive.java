package com.example.appbaothuc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.legacy.content.WakefulBroadcastReceiver;

public class AlarmReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("iii", "xxx");
        String chuoi_string = intent.getExtras().getString("extra");
        Intent myIntent = new Intent(context, Music.class);
        myIntent.putExtra("extra", chuoi_string);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(myIntent);
        } else {
            context.startService(myIntent);
        }

    }
}
