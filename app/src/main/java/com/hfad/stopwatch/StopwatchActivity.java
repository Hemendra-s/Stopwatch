package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {

    private int second = 0;
    private Boolean running = false;

    Button StartBtn, StopBtn, ResetBtn;
    TextView TimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        StartBtn = findViewById(R.id.start_btn);
        StopBtn = findViewById(R.id.stop_btn);
        ResetBtn = findViewById(R.id.reset_btn);
        TimeView = findViewById(R.id.time_view);

        if(savedInstanceState != null){
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
        }

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStart();
            }
        });

        StopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStop();
            }
        });

        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReset();
            }
        });

        runTimer();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("second",second);
        outState.putBoolean("running", running);

    }

    private void onClickStart() {
        running = true;
    }

    private void onClickStop() {
        running = false;
    }

    private void onClickReset() {
        running = true;
        second = 0;
    }


    //To control time in textView
    private void runTimer() {

        final TextView timeView = findViewById(R.id.time_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second / 3600) / 60;
                int sec = second % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec);
                timeView.setText(time);
                if (running) {
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
