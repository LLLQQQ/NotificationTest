package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                Uri soundUri = Uri.fromFile(new File("/system/media/audio/alarms/Alarm_Buzzer.ogg"));
                long[] vibrates = {0, 1000, 1000, 1000};
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("This is Joe's ticker text")
                        .setWhen(System.currentTimeMillis())
                        //          .setAutoCancel(true)
                        .setContentTitle("This is Joe's content title")
                        .setContentText("This is Joe's conient text")
                        .setSound(soundUri)
                        .setVibrate(vibrates)
                        .setLights(Color.GREEN, 1000, 1000)
                        .setContentIntent(pendingIntent);
                manager.notify(1, builder.build());
                break;
            default:
                break;
        }
    }


}
