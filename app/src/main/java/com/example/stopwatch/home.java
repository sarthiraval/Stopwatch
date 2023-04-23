package com.example.stopwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {
    Button alaram,stopwatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getSupportActionBar().hide();

        setContentView( R.layout.home );

        alaram = findViewById( R.id.alaram );
        stopwatch = findViewById( R.id.stopwatch );

        alaram.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alaram = new Intent(getApplicationContext(),alaram.class);
                startActivity( alaram );
            }
        } );

        stopwatch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stop = new Intent(getApplicationContext(),MainActivity.class);
                startActivity( stop );
            }
        } );

    }
}