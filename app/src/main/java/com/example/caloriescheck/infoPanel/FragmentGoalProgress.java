package com.example.caloriescheck.infoPanel;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.User;
import com.example.caloriescheck.localStorage.EncryptedPreferences;

public class FragmentGoalProgress extends Fragment {
    private User user;
    private View view;

    private ProgressBar progressBar;
    private ObjectAnimator progressAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goal_progress, container, false);

        user = EncryptedPreferences.getUser();
        float startWeight, targetWeight, nowWeight, weight = 0;
        startWeight = user.getStartWeight();
        targetWeight = user.getTargetWeight();
        nowWeight = user.getWeightNow();
        int progr = 0, days = 0;

        TextView progressText = view.findViewById(R.id.progressText);
        TextView timeToFinish = view.findViewById(R.id.timeToFinish);
        switch (user.getPurpose()){
            case 1:
                weight = startWeight - nowWeight;
                progr = (int)((weight / (targetWeight - startWeight)) * 100);
                days = (int) ((nowWeight - targetWeight) / 0.5f);
                progressText.setText("Вы потеряли " +  weight + " кг!");
                break;
            case 2:
                weight = nowWeight - startWeight;
                progr = (int)((weight / (targetWeight - startWeight)) * 500);
                days = (int) ((targetWeight - nowWeight) / 0.5f);
                progressText.setText("Вы набрали " +  weight + " кг!");
                break;
        }

        if (days == 1){
            timeToFinish.setText("Всего лишь " + days + " неделя до достижения цели");
        } else if (days <= 4){
            timeToFinish.setText("Всего лишь " + days + " недели до достижения цели");
        } else {
            timeToFinish.setText("Всего лишь " + days + " недель до достижения цели");
        }

        progressBar = view.findViewById(R.id.progressBar);
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, progr);
        progressAnimator.setDuration(2000);
        progressAnimator.start();

        return view;
    }
}