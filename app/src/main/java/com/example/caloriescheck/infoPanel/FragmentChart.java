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

public class FragmentChart extends Fragment {
    private MainModelView mainModelView;
    private View view;

    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6;
    private ObjectAnimator progressAnimator;
    int percentZnach1, percentZnach2, percentZnach3, percentZnach4, percentZnach5, percentZnach6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart, container, false);

        TextView percent1 = view.findViewById(R.id.percent1);
        TextView percent2 = view.findViewById(R.id.percent2);
        TextView percent3 = view.findViewById(R.id.percent3);
        TextView percent4 = view.findViewById(R.id.percent4);
        TextView percent5 = view.findViewById(R.id.percent5);
        TextView percent6 = view.findViewById(R.id.percent6);

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.getCurrentDay().observe(getViewLifecycleOwner(), day -> {
            percentZnach1 = 0;
            percentZnach3 = 0;
            percentZnach5 = 0;

            if (!day.getFoods().isEmpty()){
                for(Day.Meal m : day.getFoods()){
                    percentZnach1 += m.getFood().getCarbohydrates() * m.getWeight();
                    percentZnach3 += m.getFood().getSquirrels() * m.getWeight();
                    percentZnach5 += m.getFood().getFats() * m.getWeight();
                }
            }

            percentZnach2 = (int) (((float)day.getCarbohydratesTarget() / day.getCaloriesTarget()) * 500);
            percentZnach4 = (int) (((float)day.getSquirrelsTarget() / day.getCaloriesTarget()) * 500);
            percentZnach6 = (int) (((float)day.getFatsTarget() / day.getCaloriesTarget()) * 400);

            percent1.setText(percentZnach1 + "%");
            progressBar1 = view.findViewById(R.id.progressBar);
            progressAnimator = ObjectAnimator.ofInt(progressBar1, "progress", 0, percentZnach1);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            percent2.setText(percentZnach2 + "%");
            progressBar2 = view.findViewById(R.id.progressBar1);
            progressAnimator = ObjectAnimator.ofInt(progressBar2, "progress", 0, percentZnach2);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            percent3.setText( percentZnach3 + "%");
            progressBar3 = view.findViewById(R.id.progressBar2);
            progressAnimator = ObjectAnimator.ofInt(progressBar3, "progress", 0, percentZnach3);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            percent4.setText(percentZnach4 + "%");
            progressBar4 = view.findViewById(R.id.progressBar3);
            progressAnimator = ObjectAnimator.ofInt(progressBar4, "progress", 0, percentZnach4);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            percent5.setText(percentZnach5 + "%");
            progressBar5 = view.findViewById(R.id.progressBar4);
            progressAnimator = ObjectAnimator.ofInt(progressBar5, "progress", 0, percentZnach5);
            progressAnimator.setDuration(2000);
            progressAnimator.start();

            percent6.setText(percentZnach6 + "%");
            progressBar6 = view.findViewById(R.id.progressBar5);
            progressAnimator = ObjectAnimator.ofInt(progressBar6, "progress", 0, percentZnach6);
            progressAnimator.setDuration(2000);
            progressAnimator.start();
        });



        return view;
    }
}