package com.example.caloriescheck.infoPanel;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.caloriescheck.R;

public class FragmentTest extends Fragment {

    private int seconds;
    private boolean running;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        running = true;
        runTimer();
    }

    @Override
    public void onResume() {
        super.onResume();

        Button stop  = getView().findViewById(R.id.buttonStop);

        if (stop != null){
            stop.setOnClickListener( v -> {
                running = false;
            });
        }
    }

    private void runTimer(){
        final TextView textView = (TextView) getView().findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int days = seconds / 86400;
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int sec = seconds%60;
                String time = String.format("%d %d %02d %02d",days, hours, minutes, sec);
                textView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}