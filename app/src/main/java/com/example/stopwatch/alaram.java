package com.example.stopwatch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class alaram  extends AppCompatActivity {
    TimePicker alarmTimePicker;
    TextView time_lapse;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    public int hrs ;
    public int mins ;
    public int secs;
    private boolean is_running;

    TextView text1;//text2,text3,text4,text5;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getSupportActionBar().hide();

        setContentView( R.layout.alaram );
        time_lapse = (TextView)findViewById(R.id.time_lapse);

        alarmTimePicker = (TimePicker)findViewById(R.id.timePicker);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //text2 = findViewById( R.id.text2 );
        //text3 = findViewById( R.id.text3 );
        //text4 = findViewById( R.id.text4 );
        //text5 = findViewById( R.id.text5 );
        time_lapse.setText( String.format( Locale.getDefault(), "    %d:%02d:%02d   ", hrs, mins, secs ) );
        is_running = true;
    }

    public void OnToggleClicked(View view)
    {
        long time;
        if (((ToggleButton) view).isChecked()) {
            //counter = counter + 1;
            Toast.makeText( alaram.this, "ALARM ON", Toast.LENGTH_SHORT ).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set( Calendar.HOUR_OF_DAY, alarmTimePicker.getHour() );
            calendar.set( Calendar.MINUTE, alarmTimePicker.getMinute() );
            Intent intent = new Intent( this, Receiver.class );
            pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

           // if (counter <= 5) {
                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > time) {
                    if (calendar.AM_PM == 0) {
                        time = time + (1000 * 60 * 60 * 12);
                    }
                    else {
                        time = time + (1000 * 60 * 60 * 24);
                    }
                }

                alarmManager.setRepeating( AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent );

        }
        else
        {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(alaram.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
