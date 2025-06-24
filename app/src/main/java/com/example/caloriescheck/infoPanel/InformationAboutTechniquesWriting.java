package com.example.caloriescheck.infoPanel;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.Operation;
import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.enums.MealType;
import com.example.caloriescheck.localStorage.EncryptedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InformationAboutTechniquesWriting extends Fragment{
    private MainModelView mainModelView;
    private View view;
    private ProgressBar progressBar, progressBar1, progressBar2, progressBar3;
    private ObjectAnimator progressAnimator;
    private float received, received1, received2, received3;
    private float max, max1, max2, max3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_information_about_techniques_writing, container, false);

        RadioGroup select = view.findViewById(R.id.radioGroup);

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.getCurrentDay().observe(getViewLifecycleOwner(), day -> {
            max = day.getCaloriesTarget() * 0.25f;
            max1 = day.getSquirrelsTarget() * 0.25f;
            max2 = day.getCarbohydratesTarget() * 0.25f;
            max3 = day.getFatsTarget() * 0.25f;
            List<Integer> result1 = findAllCal(day, MealType.BREAKFAST);
            received = result1.get(0);
            received1 = result1.get(1);
            received2 = result1.get(2);
            received3 = result1.get(3);
            showData();

            select.setOnCheckedChangeListener((group, checkedId) -> {
                RadioButton radioButton = group.findViewById(checkedId);
                if (radioButton.getId() == R.id.radioBreakfast){
                    max = day.getCaloriesTarget() * 0.25f;
                    max1 = day.getSquirrelsTarget() * 0.25f;
                    max2 = day.getCarbohydratesTarget() * 0.25f;
                    max3 = day.getFatsTarget() * 0.25f;
                    List<Integer> result = findAllCal(day, MealType.BREAKFAST);
                    received = result.get(0);
                    received1 = result.get(1);
                    received2 = result.get(2);
                    received3 = result.get(3);
                } else if (radioButton.getId() == R.id.radioLunch){
                    max = day.getCaloriesTarget() * 0.35f;
                    max1 = day.getSquirrelsTarget() * 0.35f;
                    max2 = day.getCarbohydratesTarget() * 0.35f;
                    max3 = day.getFatsTarget() * 0.35f;
                    List<Integer> result = findAllCal(day, MealType.LUNCH);
                    received = result.get(0);
                    received1 = result.get(1);
                    received2 = result.get(2);
                    received3 = result.get(3);
                } else if(radioButton.getId() == R.id.radioDinner){
                    max = day.getCaloriesTarget() * 0.25f;
                    max1 = day.getSquirrelsTarget() *0.25f;
                    max2 = day.getCarbohydratesTarget() * 0.25f;
                    max3 = day.getFatsTarget() * 0.25f;
                    List<Integer> result = findAllCal(day, MealType.DINNER);
                    received = result.get(0);
                    received1 = result.get(1);
                    received2 = result.get(2);
                    received3 = result.get(3);
                } else if(radioButton.getId() == R.id.radioSnack){
                    max = day.getCaloriesTarget() * 0.15f;
                    max1 = day.getSquirrelsTarget() * 0.15f;
                    max2 = day.getCarbohydratesTarget() * 0.15f;
                    max3 = day.getFatsTarget() * 0.15f;
                    List<Integer> result = findAllCal(day, MealType.SNACK);
                    received = result.get(0);
                    received1 = result.get(1);
                    received2 = result.get(2);
                    received3 = result.get(3);
                }
                showData();
            });
        });
        return view;
    }

    private void showData(){
        int progr, progr1, progr2, progr3;
        TextView fats, proteins, carbohydrates, calories;

        calories = view.findViewById(R.id.caloriesZnach);
        carbohydrates = view.findViewById(R.id.carbohydratesZnach);
        proteins = view.findViewById(R.id.proteinZnach);
        fats = view.findViewById(R.id.fatsZnach);

        progr = (int) (received / max * 100);

        calories.setText((int) received + "/" + (int) max + " ккал");
        progressBar = view.findViewById(R.id.progressBar1);
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, progr);
        progressAnimator.setDuration(2000);
        progressAnimator.start();

        progr1 = (int) (received1 / max1 * 100);

        carbohydrates.setText((int) received1 + "/" + (int) max1 + " ккал");
        progressBar1 = view.findViewById(R.id.progressBar2);
        progressAnimator = ObjectAnimator.ofInt(progressBar1, "progress", 0, progr1);
        progressAnimator.setDuration(2000);
        progressAnimator.start();

        progr2 = (int) (received2 / max2 * 100);

        proteins.setText((int) received2 + "/" + (int) max2 + " ккал");
        progressBar2 = view.findViewById(R.id.progressBar3);
        progressAnimator = ObjectAnimator.ofInt(progressBar2, "progress", 0, progr2);
        progressAnimator.setDuration(2000);
        progressAnimator.start();

        progr3 = (int) (received3 / max3 * 100);

        fats.setText((int) received3 + "/" + (int) max3 + " ккал");
        progressBar3 = view.findViewById(R.id.progressBar4);
        progressAnimator = ObjectAnimator.ofInt(progressBar3, "progress", 0, progr3);
        progressAnimator.setDuration(2000);
        progressAnimator.start();
    }

    private List<Integer> findAllCal(Day day, MealType type){
        List<Day.Meal> foods = day.getFoods().stream().filter(meal -> meal.getType() == type).collect(Collectors.toList());
        ArrayList<Integer> nowList = new ArrayList<>();
        int nowCal =0;
        int nowBel = 0;
        int nowYgl = 0;
        int nowFat = 0;
        if (!foods.isEmpty()){
            for(Day.Meal m : foods){
                nowCal += m.getFood().getCalories() * m.getWeight();
                nowBel += m.getFood().getSquirrels() * m.getWeight();
                nowYgl += m.getFood().getCarbohydrates() * m.getWeight();
                nowFat += m.getFood().getFats() * m.getWeight();
            }
        }
        nowList.add(nowCal);
        nowList.add(nowBel);
        nowList.add(nowYgl);
        nowList.add(nowFat);
        return nowList;
    }
}