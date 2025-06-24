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

public class HomeScreenInfoPanel extends Fragment {
    private MainModelView mainModelView;

    private ProgressBar progressBar1,progressBar2, progressBar3, progressBar;
    private ObjectAnimator progressAnimator;
    private float received1, received2, received3, received;
    private int progr1, progr2, progr3, progr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_screen_info_panel, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.getCurrentDay().observe(getViewLifecycleOwner(), day -> {
            int max = day.getCaloriesTarget();
            int max1 = day.getSquirrelsTarget();
            int max2 = day.getCarbohydratesTarget();
            int max3 = day.getFatsTarget();

            received = 0;
            received1 = 0;
            received2 = 0;
            received3 = 0;

            if (!day.getFoods().isEmpty()){
                for (Day.Meal m: day.getFoods()) {
                    received += m.getFood().getCalories() * m.getWeight();
                    received1 += m.getFood().getSquirrels() * m.getWeight();
                    received2 += m.getFood().getCarbohydrates() * m.getWeight();
                    received3 += m.getFood().getFats() * m.getWeight();
                }
            }

            //Анимация заполния шкал прогресса и отображение значений на экране
            TextView fats, proteins, carbohydrates, numberCalories, outCalories;

            proteins = getView().findViewById(R.id.numberProteins);
            carbohydrates = getView().findViewById(R.id.amountCarbohydrates);
            fats = getView().findViewById(R.id.amountFat);
            numberCalories = getView().findViewById(R.id.numberCalories);
            outCalories = getView().findViewById(R.id.outCalories);

            progr = (int) (received / max * 100);

            numberCalories.setText(String.valueOf((int)received));
            outCalories.setText("из " + max + " ККАЛ");
            progressBar = getView().findViewById(R.id.progressBar);
            progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, progr);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            progr1 = (int) (received1 / max1 * 100);

            proteins.setText((int) received1 + "/" + max1 + " г");
            progressBar1 = getView().findViewById(R.id.progressBar1);
            progressAnimator = ObjectAnimator.ofInt(progressBar1, "progress", 0, progr1);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            progr2 = (int) (received2 / max2 * 100);

            carbohydrates.setText((int) received2 + "/" + max2 + " г");
            progressBar2 = getView().findViewById(R.id.progressBar2);
            progressAnimator = ObjectAnimator.ofInt(progressBar2, "progress", 0, progr2);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            progr3 = (int) (received3 / max3 * 100);

            fats.setText((int) received3 + "/" + max3 + " г");
            progressBar3 = getView().findViewById(R.id.progressBar3);
            progressAnimator = ObjectAnimator.ofInt(progressBar3, "progress", 0, progr3);
            progressAnimator.setDuration(2000);
            progressAnimator.start();
        });
    }
}
