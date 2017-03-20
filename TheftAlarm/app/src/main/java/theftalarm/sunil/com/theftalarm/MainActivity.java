package theftalarm.sunil.com.theftalarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   static MainActivity mainActivity;
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;
    }

    public void start(View v){
        Intent i=new Intent(this,AlarmService.class);
        startService(i);
        Toast.makeText(this,"Alarm Set",Toast.LENGTH_SHORT).show();
    }

    public void stop(View v){
        Intent i=new Intent(this,AlarmService.class);
        stopService(i);
        Toast.makeText(this,"Alarm Stopped",Toast.LENGTH_SHORT).show();
    }
}
