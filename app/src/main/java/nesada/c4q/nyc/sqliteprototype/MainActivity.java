package nesada.c4q.nyc.sqliteprototype;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import nesada.c4q.nyc.sqliteprototype.notification.NotificationBroadcaster;
import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {
    EditText mImage, mName;
    Button mBtnAdd, mBtnShow;
    TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = (TextView)findViewById(R.id.tv);
        mImage = (EditText) findViewById(R.id.image);
        mName = (EditText) findViewById(R.id.name);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnShow = (Button) findViewById(R.id.btn_show);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetupDB setupDB = new SetupDB(MainActivity.this);
                SQLiteDatabase db = setupDB.getWritableDatabase();

                Turtle turtle = new Turtle(mName.getText().toString(), mImage.getText().toString());

                long id = cupboard().withDatabase(db).put(turtle);


            }
        });

        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



             
                SetupDB setupDB = new SetupDB(MainActivity.this);
                SQLiteDatabase db = setupDB.getReadableDatabase();

               
                Cursor bunnies = cupboard().withDatabase(db).query(Turtle.class).getCursor();
                try {
               
                    QueryResultIterable<Turtle> itr = cupboard().withCursor(bunnies).iterate(Turtle.class);

                    String a= "";
                    for (Turtle bunny : itr) {
                        a = a + bunny.image  + bunny.name;
                       mTv.setText(a);
                    }
                } finally {
                    
                    bunnies.close();
                }




//               
            }
        });


        scheduleNotification();
    }


    private void scheduleNotification() {

        Notification notification = createNotification(getString(R.string.app_name), "A turtle just arrived!");
        Intent notificationIntent = getIntent(notification);
        PendingIntent pendingIntent = getBroadcast(notificationIntent);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 20);

        AlarmManager alarmManager = getSystemService();

        
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

        int imageId = R.drawable.turtle1;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.turtle1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.turtle1);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        Intent resultIntent = new Intent(this, AddTurtle.class);
        resultIntent.putExtra("image", b);
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
