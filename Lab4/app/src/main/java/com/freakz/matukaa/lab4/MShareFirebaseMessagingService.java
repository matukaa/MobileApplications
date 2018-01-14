package com.freakz.matukaa.lab4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.freakz.matukaa.lab4.Entities.Song;
import com.freakz.matukaa.lab4.Manager.AppManager;
import com.freakz.matukaa.lab4.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Matukaa on 2018-01-15.
 */

public class MShareFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null)
            sendNotification(remoteMessage.getNotification().getBody());
    }

    private void sendNotification(String body) {
        Log.d("Notification", body);
        Song song = null;
        for (Song s : AppManager.getInstance().songList)
            if (s.getId().equals(body))
                song = s;
        if (song == null)
            return;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(song.getLink()));
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, browserIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("NotificationChannel", "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "NotificationChannel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("MShare")
                .setContentText("\"" + song.getTitle() + "\" by " + "\"" + song.getArtist() + "\" has been added! Check it out.")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
