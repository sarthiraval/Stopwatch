package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


TextView time_lapse, t_View;
    private int sec = 0,i;
    private boolean is_running;
    private boolean was_running;
    private int lap=0;
    public  int lapcount=0;
    protected int hrs ;
    protected int mins ;
    protected int secs;
    protected int ms;
    protected int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        time_lapse = (TextView)findViewById(R.id.time_lapse);
        t_View = (TextView) findViewById(R.id.time_view);



        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("seconds");
            is_running = savedInstanceState.getBoolean("running");
            was_running = savedInstanceState .getBoolean("wasRunning");
        }
        running_Timer();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", sec);
        outState.putBoolean("running", is_running);
        outState.putBoolean("wasRunning", was_running);
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        was_running = is_running;
        is_running = false;
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if (was_running) {
            is_running = true;
        }
    }


    public void onClickStart(View view)
    {
        is_running = true;
    }
    public void onClickStop(View view)
    {
        is_running = false;
    }
    public void onClickReset(View view)
    {
        is_running = false;
        sec = 0;
    }
    public void onClickLap(View view)
    {
        String arr[] = {};

        counter = counter+1;
        is_running = false;
        time_lapse.setText( String.format( Locale.getDefault(), "    %d:%02d:%02d   ", hrs, mins, secs ) );
        is_running = true;
    }
    private void running_Timer() {

         Handler handler = new Handler(); // handler class of android:os

         handler.post(new Runnable() {
            @Override
            public void run() {
                hrs = sec / 3600;
                mins = (sec % 3600) / 60;
                secs = sec % 60;
                String time_t = String.format(Locale.getDefault(), "    %d:%02d:%02d   ", hrs, mins, secs);
                t_View.setText(time_t);
                if (is_running) {
                    sec++;
                }
               handler.postDelayed(this, 1000);
            }
        });


    }
}