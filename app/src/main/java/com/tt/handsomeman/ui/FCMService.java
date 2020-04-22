package com.tt.handsomeman.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.MediaStoreSignature;
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
    private String authorizationCode;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        LocalBroadcastManager broadcaster = LocalBroadcastManager.getInstance(getBaseContext());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        authorizationCode = sharedPreferences.getString("token", "");

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data: " + remoteMessage.getData());

            switch (NotificationType.valueOf(remoteMessage.getData().get("type"))) {
                case SEND_MESSAGE:
                    int receiveId = Conversation.receiveDefaultId == null ? 0 : Conversation.receiveDefaultId;
                    if (receiveId != (Integer.parseInt(remoteMessage.getData().get("accountId")))) {
                        sendMessageNotification(remoteMessage.getData().get("body"),
                                remoteMessage.getData().get("accountName"),
                                remoteMessage.getData().get("avatar"),
                                remoteMessage.getData().get("updateDate"));
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
                    sendMadeABidNotification(remoteMessage.getData().get("accountName"),
                            remoteMessage.getData().get("jobName"),
                            remoteMessage.getData().get("avatar"),
                            remoteMessage.getData().get("updateDate"));
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
                    sendAcceptBidNotification(remoteMessage.getData().get("accountName"),
                            remoteMessage.getData().get("jobName"),
                            remoteMessage.getData().get("avatar"),
                            remoteMessage.getData().get("updateDate"));
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
                    sendPaidPaymentNotification(remoteMessage.getData().get("accountName"),
                            remoteMessage.getData().get("milestoneOrder"),
                            remoteMessage.getData().get("avatar"),
                            remoteMessage.getData().get("updateDate"));
                    Intent intent3 = new Intent(REQUEST_PAID_PAYMENT);
                    Bundle bundle3 = new Bundle();
                    Map<String, String> dataMap3 = remoteMessage.getData();
                    for (String key : dataMap3.keySet()) {
                        bundle3.putString(key, dataMap3.get(key));
                    }
                    intent3.putExtras(bundle3);
                    broadcaster.sendBroadcast(intent3);
                    break;
            }
        }
    }

    private void sendPaidPaymentNotification(String accountName,
                                             String milestoneOrder,
                                             String avatar,
                                             String updateDate) {
        createNotificationChannel("Paid payment", "Receive customer paid payment", "Paid payment");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "Paid payment");

        int paymentMilestoneOrder = Integer.parseInt(milestoneOrder);
        String result;
        switch (paymentMilestoneOrder) {
            case 1:
                result = getString(R.string.first_milestone, paymentMilestoneOrder);
                break;
            case 2:
                result = getString(R.string.second_milestone, paymentMilestoneOrder);
                break;
            case 3:
                result = getString(R.string.third_milestone, paymentMilestoneOrder);
                break;
            default:
                result = getString(R.string.default_milestone, paymentMilestoneOrder);
                break;
        }

        GlideUrl glideUrl = new GlideUrl((avatar),
                new LazyHeaders.Builder().addHeader("Authorization", authorizationCode).build());

        Glide.with(this)
                .asBitmap()
                .load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .placeholder(R.drawable.custom_progressbar)
                .error(R.drawable.logo)
                .signature(new MediaStoreSignature("", Long.parseLong(updateDate), 0))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {
                        try {
                            notificationBuilder
                                    .setSmallIcon(R.drawable.ic_notification)
                                    .setColor(getResources().getColor(R.color.colorPrimary))
                                    .setLargeIcon(resource)
                                    .setContentTitle(URLDecoder.decode(accountName, "UTF-8"))
                                    .setContentText(getString(R.string.paid_payment_notification, URLDecoder.decode(accountName, "UTF-8"), result))
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setAutoCancel(true);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(FCMService.this);
                        notificationManager.notify(47, notificationBuilder.build());
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    private void sendAcceptBidNotification(String accountName,
                                           String jobName,
                                           String avatar,
                                           String updateDate) {
        createNotificationChannel("Accept bid", "Receive customer accept bid", "Accept bid");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "Accept bid");

        GlideUrl glideUrl = new GlideUrl((avatar),
                new LazyHeaders.Builder().addHeader("Authorization", authorizationCode).build());

        Glide.with(this)
                .asBitmap()
                .load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .placeholder(R.drawable.custom_progressbar)
                .error(R.drawable.logo)
                .signature(new MediaStoreSignature("", Long.parseLong(updateDate), 0))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {
                        try {
                            notificationBuilder
                                    .setSmallIcon(R.drawable.ic_notification)
                                    .setColor(getResources().getColor(R.color.colorPrimary))
                                    .setLargeIcon(resource)
                                    .setContentTitle(URLDecoder.decode(accountName, "UTF-8"))
                                    .setContentText(getString(R.string.accept_bid_notification, URLDecoder.decode(accountName, "UTF-8"), URLDecoder.decode(jobName, "UTF-8")))
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setAutoCancel(true);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(FCMService.this);
                        notificationManager.notify(37, notificationBuilder.build());
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    private void sendMadeABidNotification(String accountName,
                                          String jobName,
                                          String avatar,
                                          String updateDate) {
        createNotificationChannel("Made a bid", "Receive handyman bid job", "Made a bid");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "Made a bid");

        GlideUrl glideUrl = new GlideUrl((avatar),
                new LazyHeaders.Builder().addHeader("Authorization", authorizationCode).build());

        Glide.with(this)
                .asBitmap()
                .load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .placeholder(R.drawable.custom_progressbar)
                .error(R.drawable.logo)
                .signature(new MediaStoreSignature("", Long.parseLong(updateDate), 0))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {
                        try {
                            notificationBuilder
                                    .setSmallIcon(R.drawable.ic_notification)
                                    .setColor(getResources().getColor(R.color.colorPrimary))
                                    .setLargeIcon(resource)
                                    .setContentTitle(URLDecoder.decode(accountName, "UTF-8"))
                                    .setContentText(getString(R.string.made_bid_notification, URLDecoder.decode(accountName, "UTF-8"), URLDecoder.decode(jobName, "UTF-8")))
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setAutoCancel(true);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(FCMService.this);
                        notificationManager.notify(27, notificationBuilder.build());
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    private void sendMessageNotification(String message,
                                         String accountName,
                                         String avatar,
                                         String updateDate) {
        createNotificationChannel("Message", "Receive message notification", "Message");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "Message");

        GlideUrl glideUrl = new GlideUrl((avatar),
                new LazyHeaders.Builder().addHeader("Authorization", authorizationCode).build());

        Glide.with(this)
                .asBitmap()
                .load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .placeholder(R.drawable.custom_progressbar)
                .error(R.drawable.logo)
                .signature(new MediaStoreSignature("", Long.parseLong(updateDate), 0))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {
                        try {
                            notificationBuilder
                                    .setSmallIcon(R.drawable.ic_notification)
                                    .setColor(getResources().getColor(R.color.colorPrimary))
                                    .setLargeIcon(resource)
                                    .setContentTitle(getString(R.string.sent, URLDecoder.decode(accountName, "UTF-8")))
                                    .setContentText(URLDecoder.decode(message, "UTF-8"))
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setAutoCancel(true);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(FCMService.this);
                        notificationManager.notify(17, notificationBuilder.build());
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    private void createNotificationChannel(CharSequence name,
                                           String description,
                                           String channelId) {
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
