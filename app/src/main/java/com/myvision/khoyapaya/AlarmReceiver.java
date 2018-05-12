package com.myvision.khoyapaya;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.myvision.khoyapaya.splash.Splash;

/**
 * Created by Rahul BANSAL on 2/24/2017.
 */
public class AlarmReceiver extends BroadcastReceiver {
    public static SharedPreferences life;
    @Override
    public void onReceive(Context context, Intent intent) {
        life =context.getSharedPreferences(null, Context.MODE_PRIVATE);
        life.edit().putBoolean("life", true).apply();
        createNotification(context , "KhoyaPaya Life","1  min Has Passed"," Alert KhoyaPaya");
        // TODO Auto-generated method stub
        //String phoneNumberReciver="987654321";
        //String message="Hi I will be there later, See You Soon";
        //SmsManager sms=SmsManager.getDefault();
        ////sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
        Toast.makeText(context, "ComeOn Play Again", Toast.LENGTH_LONG).show();
    }

    public void createNotification(Context context, String s, String s1, String khoyaPaya) {

        PendingIntent notificationIntent=PendingIntent.getActivity(context,0,new Intent(context , Home.class),0);
        NotificationCompat.Builder mBuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(getNotificationIcon())
                .setContentTitle(s)
                .setTicker(khoyaPaya)
                .setColor(getcolor())
                .setContentText(s1);

        mBuilder.setContentIntent(notificationIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1,mBuilder.build());
    }
    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.life : R.drawable.life1;
    }

    public int getcolor() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);

        return useWhiteIcon ? Color.RED : Color.BLACK;
    }
}
