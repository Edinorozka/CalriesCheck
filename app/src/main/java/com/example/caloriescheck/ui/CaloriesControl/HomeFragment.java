package com.example.caloriescheck.ui.CaloriesControl;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.FragmentHomeBinding;
import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.dto.User;
import com.example.caloriescheck.enums.MealType;
import com.example.caloriescheck.fragmentBotton.FragmentButtonAddFood;
import com.example.caloriescheck.localStorage.EncryptedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment{

    private View root;
    private User user;
    private FragmentHomeBinding binding;
    private MainModelView mainModelView;
    private List<ImageButton> waterButtons = new ArrayList<>();

    private int lastClickedIndex = -1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        binding.openCalendarButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.calendarFragment);
        });

        binding.openDetailsButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.detailAnalysis);
        });

        binding.breakfast.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            Bundle args = new Bundle();
            args.putString("type", MealType.BREAKFAST.toString());
            navController.navigate(R.id.fragmentAddEat, args);
            FragmentButtonAddFood breakfast = (FragmentButtonAddFood) getChildFragmentManager().findFragmentById(R.id.breakfast);
            if (breakfast != null) {
                breakfast.afterClick();
            }
        });

        binding.lunch.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            Bundle args = new Bundle();
            args.putString("type", MealType.LUNCH.toString());
            navController.navigate(R.id.fragmentAddEat, args);
            FragmentButtonAddFood lunch = (FragmentButtonAddFood) getChildFragmentManager().findFragmentById(R.id.lunch);
            if (lunch != null) {
                lunch.afterClick();
            }
        });

        binding.dinner.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            Bundle args = new Bundle();
            args.putString("type", MealType.DINNER.toString());
            navController.navigate(R.id.fragmentAddEat, args);
            FragmentButtonAddFood dinner = (FragmentButtonAddFood) getChildFragmentManager().findFragmentById(R.id.dinner);
            if (dinner != null) {
                dinner.afterClick();
            }
        });

        binding.snack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            Bundle args = new Bundle();
            args.putString("type", MealType.SNACK.toString());
            navController.navigate(R.id.fragmentAddEat, args);
            FragmentButtonAddFood snack = (FragmentButtonAddFood) getChildFragmentManager().findFragmentById(R.id.snack);
            if (snack != null) {
                snack.afterClick();
            }
        });

        user = EncryptedPreferences.getUser();
        if (user != null) {
            binding.goal.setText("Цель: " + user.getTargetWeight() + "кг");
            binding.weight.setText(String.valueOf(user.getWeightNow()));
        } else {
            throw new RuntimeException("Пользователь не найден");
        }

        binding.addWeight.setOnClickListener(v -> {
            final TextView weight = root.findViewById(R.id.weight);
            float value = Float.parseFloat(weight.getText().toString());
            value = value + 0.1f;
            value = (float) (Math.round(value * 100.0) / 100.0);
            weight.setText(String.valueOf(value));
            user.setWeightNow(value);
            EncryptedPreferences.saveUser(user.getJson());
        });

        binding.reduceWeight.setOnClickListener(v -> {
            final TextView weight = root.findViewById(R.id.weight);
            float value = Float.parseFloat(weight.getText().toString());
            if (value - 0.1f >= 0){
                value = value - 0.1f;
                value = (float) (Math.round(value * 100.0) / 100.0);
            }
            weight.setText(String.valueOf(value));
            user.setWeightNow(value);
            EncryptedPreferences.saveUser(user.getJson());
        });

        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.breakfast, FragmentButtonAddFood.class, null)
                    .commit();
        }

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.getCurrentDay().observe(getViewLifecycleOwner(), day -> {
            binding.targetWaterValue.setText("Цель: " + String.format("%.2f", day.getWaterTarget()) + " литра");
            binding.waterOnsumed.setText(String.format("%.2f", day.getWater()));
            int waterFromFood = 0;
            for (Day.Meal m : day.getFoods()){
                waterFromFood += m.getFood().getWater();
            }
            binding.waterFromFood.setText("+ вода из пищи: " + waterFromFood + " мл");

            ConstraintLayout waterFragment = root.findViewById(R.id.water_fragment);
            int bottlesNumber = 8;

            if (waterButtons.isEmpty()){
                float water = day.getWater();
                int usedButtons = (int) (water / 0.25);

                for (int i = 0; i < bottlesNumber; i++) {
                    ImageButton bottle = new ImageButton(getContext());
                    bottle.setId(View.generateViewId());
                    bottle.setBackgroundColor(Color.TRANSPARENT);
                    if (usedButtons != 0){
                        lastClickedIndex = usedButtons - 1;
                        if (i < usedButtons)
                            bottle.setImageResource(R.drawable.botle3);
                        else if (i == usedButtons)
                            bottle.setImageResource(R.drawable.botle2);
                        else
                            bottle.setImageResource(R.drawable.botle);
                    } else {
                        if (i == 0)
                            bottle.setImageResource(R.drawable.botle2);
                        else
                            bottle.setImageResource(R.drawable.botle);
                    }


                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                    );

                    int marginTop = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

                    if (i == 0) {
                        int marginStart = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                        layoutParams.setMargins(marginStart, marginTop, 0, 0);
                        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

                    } else {
                        int marginStart = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
                        layoutParams.setMargins(marginStart, marginTop, 0, 0);
                        layoutParams.startToEnd = waterButtons.get(i - 1).getId();
                        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

                    }
                    bottle.setLayoutParams(layoutParams);

                    final int index = i;
                    bottle.setOnClickListener(v -> {
                        if (index == lastClickedIndex + 1) {
                            lastClickedIndex = index;
                            bottle.setImageResource(R.drawable.botle3);
                            if (index + 1 < waterButtons.size()){
                                ImageButton button = waterButtons.get(index + 1);
                                button.setImageResource(R.drawable.botle2);
                            }
                            day.setWater(day.getWater() + 0.25f);
                            binding.waterOnsumed.setText(String.format("%.2f", day.getWater()));

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                            String today = dateFormat.format(new Date());
                            EncryptedPreferences.saveDay(today, day.getJson());
                        }
                    });

                    waterButtons.add(bottle);
                    waterFragment.addView(bottle);
                }
            } else {
                for(ImageButton b : waterButtons){
                    if (b.getParent() != null) {
                        ((ViewGroup) b.getParent()).removeView(b);
                    }
                    waterFragment.addView(b);
                }
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}