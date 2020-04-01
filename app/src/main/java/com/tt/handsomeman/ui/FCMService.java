package com.tt.handsomeman.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tt.handsomeman.R;
import com.tt.handsomeman.ui.messages.Conversation;
import com.tt.handsomeman.util.NotificationType;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

public class FCMService extends FirebaseMessagingService {
    public static final String REQUEST_MESSAGE = "Message";
    public static final String REQUEST_MADE_BID = "Made Bid";
    public static final String REQUEST_ACCEPT_BID = "Accept Bid";
    public static final String REQUEST_PAID_PAYMENT = "Paid Payment";
    private final String TAG = "JSA-FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        LocalBroadcastManager broadcaster = LocalBroadcastManager.getInstance(getBaseContext());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data: " + remoteMessage.getData());

            switch (NotificationType.valueOf(remoteMessage.getData().get("type"))) {
                case SEND_MESSAGE:
                    int receiveId = Conversation.receiveDefaultId == null ? 0 : Conversation.receiveDefaultId;
                    if (receiveId != (Integer.parseInt(remoteMessage.getData().get("accountId")))) {
                        sendMessageNotification(remoteMessage.getData().get("body"), remoteMessage.getData().get("accountName"));
                    }
                    Intent intent = new Intent(REQUEST_MESSAGE);
                    Bundle bundle = new Bundle();
                    Map<String, String> dataMap = remoteMessage.getData();
                    for (String key : dataMap.keySet()) {
                        bundle.putString(key, dataMap.get(key));
                    }
                    intent.putExtras(bundle);
                    broadcaster.sendBroadcast(intent);
                    break;
                case MADE_A_BID:
                    sendMadeABidNotification(remoteMessage.getData().get("accountName"), remoteMessage.getData().get("jobName"));
                    Intent intent1 = new Intent(REQUEST_MADE_BID);
                    Bundle bundle1 = new Bundle();
                    Map<String, String> dataMap1 = remoteMessage.getData();
                    for (String key : dataMap1.keySet()) {
                        bundle1.putString(key, dataMap1.get(key));
                    }
                    intent1.putExtras(bundle1);
                    broadcaster.sendBroadcast(intent1);
                    break;
                case ACCEPT_BID:
                    sendAcceptBidNotification(remoteMessage.getData().get("accountName"), remoteMessage.getData().get("jobName"));
                    Intent intent2 = new Intent(REQUEST_ACCEPT_BID);
                    Bundle bundle2 = new Bundle();
                    Map<String, String> dataMap2 = remoteMessage.getData();
                    for (String key : dataMap2.keySet()) {
                        bundle2.putString(key, dataMap2.get(key));
                    }
                    intent2.putExtras(bundle2);
                    broadcaster.sendBroadcast(intent2);
                    break;
                case PAID_PAYMENT:
                    break;
            }
        }
    }

    private void sendAcceptBidNotification(String accountName, String jobName) {
        createNotificationChannel("Accept bid", "Receive customer accept bid", "Accept bid");
        NotificationCompat.Builder notificationBuilder = null;

        try {
            notificationBuilder = new NotificationCompat.Builder(this, "Accept bid")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setColor(getResources().getColor(R.color.colorPrimary))
                    .setContentTitle(URLDecoder.decode(accountName, "UTF-8"))
                    .setContentText(getString(R.string.accept_bid_notification, URLDecoder.decode(accountName, "UTF-8"), URLDecoder.decode(jobName, "UTF-8")))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(37, notificationBuilder.build());
    }

    private void sendMadeABidNotification(String accountName, String jobName) {
        createNotificationChannel("Made a bid", "Receive handyman bid job", "Made a bid");
        NotificationCompat.Builder notificationBuilder = null;

        try {
            notificationBuilder = new NotificationCompat.Builder(this, "Made a bid")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setColor(getResources().getColor(R.color.colorPrimary))
                    .setContentTitle(URLDecoder.decode(accountName, "UTF-8"))
                    .setContentText(getString(R.string.made_bid_notification, URLDecoder.decode(accountName, "UTF-8"), URLDecoder.decode(jobName, "UTF-8")))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(27, notificationBuilder.build());
    }

    private void sendMessageNotification(String message, String accountName) {
        createNotificationChannel("Message", "Receive message notification", "Message");
        NotificationCompat.Builder notificationBuilder = null;

        try {
            notificationBuilder = new NotificationCompat.Builder(this, "Message")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setColor(getResources().getColor(R.color.colorPrimary))
                    .setContentTitle(getString(R.string.sent, URLDecoder.decode(accountName, "UTF-8")))
                    .setContentText(URLDecoder.decode(message, "UTF-8"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(17, notificationBuilder.build());
    }

    private void createNotificationChannel(CharSequence name, String description, String channelId) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().putString("firebase token", s).apply();
    }
}
