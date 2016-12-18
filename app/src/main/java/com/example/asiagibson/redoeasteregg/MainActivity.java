package com.example.asiagibson.redoeasteregg;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.asiagibson.redoeasteregg.adapters.RecAdapter;
import com.example.asiagibson.redoeasteregg.notification.NotificationBroadcaster;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView mTv;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = (TextView)findViewById(R.id.tv);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new RecAdapter(this));

        scheduleNotification();
    }


    private void scheduleNotification() {

        Notification notification = createNotification(getString(R.string.app_name), "A turtle9 just arrived!");
        Intent notificationIntent = getIntent(notification);
        PendingIntent pendingIntent = getBroadcast(notificationIntent);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 20);

        AlarmManager alarmManager = getSystemService();

//
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 2*60*1000, pendingIntent);
    }

    private AlarmManager getSystemService() {
        return (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    private PendingIntent getBroadcast(Intent notificationIntent) {
        return PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @NonNull
    private Intent getIntent(Notification notification) {
        Intent notificationIntent = new Intent(this, NotificationBroadcaster.class);
        notificationIntent.putExtra(NotificationBroadcaster.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationBroadcaster.NOTIFICATION_KEY, notification);
        return notificationIntent;
    }

    private Notification createNotification(String title, String message) {

        List<Integer> mTurtleList = Arrays.asList(
                R.drawable.turtle1,
                R.drawable.turtle2,
                R.drawable.turtle3,
                R.drawable.turtle4,
                R.drawable.turtle5,
                R.drawable.turtle6,
                R.drawable.turtle7,
                R.drawable.turtle8,
                R.drawable.turtle9,
                R.drawable.turtle10,
                R.drawable.turtle11,
                R.drawable.turtle12,
                R.drawable.turtle13
        );
        Random rand = new Random();
        int randomTurtle = mTurtleList.get(rand.nextInt(8) + 0);

        String turtleName = this.getResources().getResourceEntryName(randomTurtle);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), randomTurtle);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(randomTurtle);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        Intent resultIntent = new Intent(this, AddTurtle.class);
        resultIntent.putExtra("image", b);
        resultIntent.putExtra("name", turtleName);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(this, 0, resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        builder.setAutoCancel(true);
        return builder.build();
    }

    private void cancelNotification() {
        Intent notificationIntent = new Intent(this, NotificationBroadcaster.class);
        notificationIntent.putExtra(NotificationBroadcaster.NOTIFICATION_ID, 1);
        PendingIntent pendingIntent = getBroadcast(notificationIntent);
        AlarmManager alarmManager = getSystemService();
        alarmManager.cancel(pendingIntent);
    }


}
