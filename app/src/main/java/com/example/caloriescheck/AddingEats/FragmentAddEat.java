package com.example.caloriescheck.AddingEats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.FragmentAddEatBinding;
import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.dto.Food;
import com.example.caloriescheck.enums.MealType;
import com.example.caloriescheck.localStorage.EncryptedPreferences;
import com.example.caloriescheck.localStorage.Preferences;
import com.example.caloriescheck.toolbars.FindFoodToolbarManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class FragmentAddEat extends Fragment {
    private FragmentAddEatBinding binding;

    private RecyclerView recyclerView;
    private Adapter adapter;

    private MainModelView mainModelView;

    private FindFoodToolbarManager toolbarManager;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddEatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle args = getArguments();
        MealType type = MealType.valueOf(args.getString("type"));

        Toolbar toolbar = root.findViewById(R.id.addEatToolbar);
        getActivity().setActionBar(toolbar);
        TextView toolbarText = root.findViewById(R.id.toolbarText);
        switch (type){
            case BREAKFAST:
                toolbarText.setText("Завтрак");
                break;
            case LUNCH:
                toolbarText.setText("Обед");
                break;
            case DINNER:
                toolbarText.setText("Ужин");
                break;
            case SNACK:
                toolbarText.setText("Перекус");
                break;
        }


        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            getActivity().getActionBar().setDisplayShowHomeEnabled(true);
            getActivity().getActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });

        recyclerView = root.findViewById(R.id.MyFoodsView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);

        mainModelView.geNeedToAddFoods().observe(getViewLifecycleOwner(), foods -> {
            List<Day.Meal> meals = foods.stream().filter(meal -> meal.getType() == type).collect(Collectors.toList());
            binding.symator.setText("+" + meals.size());
        });

        mainModelView.setFoods(Preferences.getAllFood());
        mainModelView.getFoods().observe(getViewLifecycleOwner(), foods -> {
            adapter = new Adapter(foods, type);
            recyclerView.setAdapter(adapter);
        });

        View findFoodToolbarView = root.findViewById(R.id.toolbar3);
        toolbarManager = new FindFoodToolbarManager(findFoodToolbarView, mainModelView);

        binding.EatsAddButton.setOnClickListener(v -> {
            Day day = mainModelView.getCurrentDay().getValue();
            mainModelView.geNeedToAddFoods().observe(getViewLifecycleOwner(), foods -> {
                for (Day.Meal meal : foods){
                    day.getFoods().add(new Day.Meal(meal.getType(), meal.getFood(), meal.getWeight()));
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String today = dateFormat.format(new Date());
                EncryptedPreferences.saveDay(today, day.getJson());
            });
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_home);
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}