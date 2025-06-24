package com.example.caloriescheck.fragmentBotton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.enums.MealType;

import java.util.List;
import java.util.stream.Collectors;

public class FragmentButtonAddFood extends Fragment {
    private MainModelView mainModelView;
    private View view;

    private TextView typeOfFood;
    private TextView recCal;
    private TextView calNow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_button_add_food, container, false);

        typeOfFood = view.findViewById(R.id.TypeOfFood);
        recCal = view.findViewById(R.id.RecommendedCal);
        calNow = view.findViewById(R.id.CalNow);

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.getCurrentDay().observe(getViewLifecycleOwner(), day -> {
            int fragmentId = getId();
            if (fragmentId == R.id.breakfast) {
                typeOfFood.setText("Завтрак");
                int now = 0;
                List<Day.Meal> foods = day.getFoods().stream().filter(meal -> meal.getType() == MealType.BREAKFAST).collect(Collectors.toList());
                if (!foods.isEmpty()){
                    for(Day.Meal m : foods){
                        now += m.getFood().getCalories() * m.getWeight();
                    }
                }
                calNow.setText(now + " ккал");
                int cal = (int) (day.getCaloriesTarget() * 0.25f);
                recCal.setText("Рекомендуется:" + cal + " ккал");
            } else if (fragmentId == R.id.lunch) {
                typeOfFood.setText("Обед");
                int now = 0;
                List<Day.Meal> foods = day.getFoods().stream().filter(meal -> meal.getType() == MealType.LUNCH).collect(Collectors.toList());
                if (!foods.isEmpty()){
                    for(Day.Meal m : foods){
                        now += m.getFood().getCalories() * m.getWeight();
                    }
                }
                calNow.setText(now + " ккал");
                int cal = (int) (day.getCaloriesTarget() * 0.35f);
                recCal.setText("Рекомендуется:" + cal + " ккал");
            } else if (fragmentId == R.id.dinner) {
                typeOfFood.setText("Ужин");
                int now = 0;
                List<Day.Meal> foods = day.getFoods().stream().filter(meal -> meal.getType() == MealType.DINNER).collect(Collectors.toList());
                if (!foods.isEmpty()){
                    for(Day.Meal m : foods){
                        now += m.getFood().getCalories() * m.getWeight();
                    }
                }
                calNow.setText(now + " ккал");
                int cal = (int) (day.getCaloriesTarget() * 0.25f);
                recCal.setText("Рекомендуется:" + cal + " ккал");
            } else if (fragmentId == R.id.snack) {
                typeOfFood.setText("Перекус");
                int now = 0;
                List<Day.Meal> foods = day.getFoods().stream().filter(meal -> meal.getType() == MealType.SNACK).collect(Collectors.toList());
                if (!foods.isEmpty()){
                    for(Day.Meal m : foods){
                        now += m.getFood().getCalories() * m.getWeight();
                    }
                }
                calNow.setText(now + " ккал");
                int cal = (int) (day.getCaloriesTarget() * 0.15f);
                recCal.setText("Рекомендуется:" + cal + " ккал");
            }
        });
        return view;
    }

    public void afterClick(){
        view.setBackgroundResource(R.drawable.rounding_green);
        ImageView image = view.findViewById(R.id.ButtonAddFoodImage);
        image.setImageResource(R.drawable.ic_complitaddeat);
        typeOfFood.setTextAppearance(R.style.white_books_1);
        recCal.setTextAppearance(R.style.white_books_4);
        calNow.setTextAppearance(R.style.white_books_4);
    }

}
