package com.example.nenezoid.ruplibapp;

/**
 * Created by Nenezoid on 1/15/2018.
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static java.security.AccessController.getContext;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyGcmListenerService";

    @Override
    public void onMessageReceived(RemoteMessage message) {

        NotificationCompat.Builder notificationBuilder ;



        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String chanel_id = "3000";
            CharSequence name = "Channel Name";
            String description = "Chanel Description";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(mChannel);
            notificationBuilder = new NotificationCompat.Builder(this, chanel_id);
        }
        else {
            notificationBuilder = new NotificationCompat.Builder(this);
        }
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("Hearty365")
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_book) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
                .setContentTitle(message.getNotification().getTitle())
                .setContentText(message.getNotification().getBody())
                .setContentInfo("Info");
        Intent resultIntent= new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent= PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);


        notificationManager.notify(0, notificationBuilder.build());

        //System.out.println("Just message "+message.getNotification().getBody());
    }


}