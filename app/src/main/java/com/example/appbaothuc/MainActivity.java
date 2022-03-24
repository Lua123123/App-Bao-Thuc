package com.example.appbaothuc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TimePicker timePicker;
    Button btnDung, btnHenGio;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(getApplicationContext(), AlarmReceive.class);


        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();
                String str_gio = String.valueOf(gio);
                String str_phut = String.valueOf(phut);

                if (gio > 12) {
                    str_gio = String.valueOf(gio - 12);
                }

                if (phut < 10) {
                    str_phut = "0" + String.valueOf(phut);
                }

                intent.putExtra("extra", "on");

                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10, pendingIntent);

                textView.setText(str_gio + ":" + str_phut);
            }
        });

        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Time has stopped!!!");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra", "off");
                sendBroadcast(intent);
            }
        });
    }


    private void initView() {
        textView = findViewById(R.id.textView);
        timePicker = findViewById(R.id.timePicker);
        btnHenGio = findViewById(R.id.btnHenGio);
        btnDung = findViewById(R.id.btnDung);
        calendar = Calendar.getInstance();
    }
}