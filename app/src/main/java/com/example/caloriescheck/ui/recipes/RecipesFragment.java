package com.example.caloriescheck.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.FragmentRecipesBinding;
import com.example.caloriescheck.dto.Food;
import com.example.caloriescheck.localStorage.Preferences;
import com.example.caloriescheck.toolbars.FindFoodToolbarManager;

import java.util.List;

public class RecipesFragment extends Fragment {
    private View root;
    private FragmentRecipesBinding binding;
    private FindFoodToolbarManager toolbarManager;
    private MainModelView mainModelView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        View findFoodToolbarView = root.findViewById(R.id.toolbar3);

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.setFoods(Preferences.getAllFood());

        toolbarManager = new FindFoodToolbarManager(findFoodToolbarView, mainModelView);

        RecyclerView horizontalView = binding.MyFoodsView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalView.setLayoutManager(layoutManager);
        horizontalView.addItemDecoration(new HorizontalSpaceItemDecoration(20));
        List<Food> foods = Preferences.getAllFood();
        FoodCardAdapter myFoodCardAdapter = new FoodCardAdapter(foods);
        horizontalView.setAdapter(myFoodCardAdapter);

        mainModelView.getFoods().observe(getViewLifecycleOwner(), allFoods -> {
            if (Preferences.getAllFood().size() != allFoods.size()){
                binding.textView14.setText("Найденые блюда");
            } else {
                binding.textView14.setText("Все блюда");
            }
            RecyclerView AllFoodsView = binding.AllFoodsView;
            GridLayoutManager gridlayoutManager = new GridLayoutManager(getContext(), 2);
            AllFoodsView.setLayoutManager(gridlayoutManager);
            AllFoodsView.addItemDecoration(new HorizontalSpaceItemDecoration(20));
            FoodCardAdapter allFoodsCardAdapter = new FoodCardAdapter(allFoods);
            AllFoodsView.setAdapter(allFoodsCardAdapter);
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
