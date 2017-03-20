package theftalarm.sunil.com.theftalarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

/**
 * Created by Sonu on 2/20/2017.
 */

public class AlarmService extends Service {

    SensorManager smanager;
    MediaPlayer mplayer;
    TextView tv1,tv2;
    Vibrator vib;
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        smanager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mplayer=MediaPlayer.create(getApplicationContext(),R.raw.alarm);
        tv1=(TextView)MainActivity.mainActivity.findViewById(R.id.tv1);
        tv2=(TextView)MainActivity.mainActivity.findViewById(R.id.tv2);
        vib=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        builder=new NotificationCompat.Builder(this);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        builder.setTicker("Alarm is running!!");
        builder.setSmallIcon(R.drawable.alarmsmall);
        builder.setContentTitle("Theft Alarm");
        builder.setContentText("Theft alarm is running");
        Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.alarmbig);
        builder.setLargeIcon(bmp);
        builder.setOngoing(true);
        Intent i=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,i,0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        notificationManager.notify(123,builder.build());
        smanager.registerListener(new SensorListener() {
            @Override
            public void onSensorChanged(int sensor, float[] values) {
                tv1.setText("Values X-"+values[0]);
                tv2.setText("Values Y-"+values[1]);
                if(values[0]>2 || values[1]>2){
                    mplayer.start();
                }
            }
            @Override
            public void onAccuracyChanged(int sensor, int accuracy) {

            }
        },SensorManager.SENSOR_ACCELEROMETER);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mplayer.stop();
        notificationManager.cancel(123);
    }
}
