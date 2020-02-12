package com.tt.handsomeman.ui.handyman.messages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FCMService extends FirebaseMessagingService {
    public static final String REQUEST_ACCEPT = "1";
    private final String TAG = "JSA-FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        LocalBroadcastManager broadcaster = LocalBroadcastManager.getInstance(getBaseContext());
        Intent intent = new Intent(REQUEST_ACCEPT);
        Bundle bundle = new Bundle();

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Body: " + remoteMessage.getNotification().getBody());
            bundle.putString("Title", remoteMessage.getNotification().getTitle());
            bundle.putString("Body", remoteMessage.getNotification().getBody());

        }

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data: " + remoteMessage.getData());
            Map<String, String> dataMap = remoteMessage.getData();
            for (String key : dataMap.keySet()) {
                bundle.putString(key, dataMap.get(key));
            }
        }

        intent.putExtras(bundle);
        broadcaster.sendBroadcast(intent);

    }
}
