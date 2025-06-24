package com.example.caloriescheck.toolbars;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Food;
import com.example.caloriescheck.localStorage.Preferences;

import java.util.ArrayList;
import java.util.List;

public class FindFoodToolbarManager {
    private SearchView searchView;
    private ImageButton imageButton;
    private List<Food> listFoods;
    private List<Food> results;

    public FindFoodToolbarManager(View view, MainModelView mainModelView){
        searchView = view.findViewById(R.id.findFood);
        imageButton = view.findViewById(R.id.addFoodButtonInToolbar);

        listFoods = Preferences.getAllFood();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { // после нажатия на поиск
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { // при изменении содержимого строки
                results =  new ArrayList<>();
                for(Food f : listFoods){
                    if (f.getName().toLowerCase().contains(newText)){
                        results.add(f);
                    }
                }
                if (results.isEmpty()){
                    mainModelView.setFoods(Preferences.getAllFood());
                    return false;
                } else {
                    mainModelView.setFoods(results);
                    return true;
                }
            }
        });

        imageButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment);
            navController.navigate(R.id.createNewFoodFragment);
        });
    }

    public List<Food> GetResults(){
        if(results != null)
            return results;
        else
            return listFoods;
    }
}
