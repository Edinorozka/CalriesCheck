package com.example.caloriescheck.infoPanel;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Day;

public class fragmentGoalIndicators extends Fragment {
    private MainModelView mainModelView;

    private ProgressBar progressBar, progressBar1, progressBar2, progressBar3;
    private ObjectAnimator progressAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goal_indicators, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Анимация заполния шкал прогресса и отображение значений на экране
        TextView fats, proteins, carbohydrates, calories;

        calories = getView().findViewById(R.id.caloriesZnach);
        carbohydrates = getView().findViewById(R.id.carbohydratesZnach);
        proteins = getView().findViewById(R.id.proteinZnach);
        fats = getView().findViewById(R.id.fatsZnach);

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.getCurrentDay().observe(getViewLifecycleOwner(), day -> {
            float max = day.getCaloriesTarget();
            float max1 = day.getSquirrelsTarget();
            float max2 = day.getCarbohydratesTarget();
            float max3 = day.getFatsTarget();

            float received = 0;
            float received1 = 0;
            float received2 = 0;
            float received3 = 0;

            if (!day.getFoods().isEmpty()){
                for (Day.Meal m: day.getFoods()) {
                    received += m.getFood().getCalories() * m.getWeight();
                    received1 += m.getFood().getSquirrels() * m.getWeight();
                    received2 += m.getFood().getCarbohydrates() * m.getWeight();
                    received3 += m.getFood().getFats() * m.getWeight();
                }
            }

            int progr = (int) (received / max * 100);

            calories.setText((int) received + "/" + (int) max + " ккал");
            progressBar = getView().findViewById(R.id.progressBar1);
            progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, progr);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            int progr1 = (int) (received1 / max1 * 100);

            carbohydrates.setText((int) received1 + "/" + (int) max1 + " ккал");
            progressBar1 = getView().findViewById(R.id.progressBar2);
            progressAnimator = ObjectAnimator.ofInt(progressBar1, "progress", 0, progr1);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            int progr2 = (int) (received2 / max2 * 100);

            proteins.setText((int) received2 + "/" + (int) max2 + " ккал");
            progressBar2 = getView().findViewById(R.id.progressBar3);
            progressAnimator = ObjectAnimator.ofInt(progressBar2, "progress", 0, progr2);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            int progr3 = (int) (received3 / max3 * 100);

            fats.setText((int) received3 + "/" + (int) max3 + " ккал");
            progressBar3 = getView().findViewById(R.id.progressBar4);
            progressAnimator = ObjectAnimator.ofInt(progressBar3, "progress", 0, progr3);
            progressAnimator.setDuration(2000);
            progressAnimator.start();
        });
    }
}