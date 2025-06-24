package com.example.caloriescheck.ui.recipes;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.FragmentCreateNewFoodBinding;
import com.example.caloriescheck.dto.Food;
import com.example.caloriescheck.localStorage.Preferences;


public class CreateNewFoodFragment extends Fragment {
    private View view;
    private FragmentCreateNewFoodBinding binding;
    private Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateNewFoodBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        binding.setImageButton.setOnClickListener(v -> {

        });

        binding.CreateFoodButton.setOnClickListener(v -> {
            String name = binding.FoodNameText.getText().toString();
            String cal = binding.CalText.getText().toString();
            String bel = binding.BelText.getText().toString();
            String yql = binding.YglText.getText().toString();
            String fat = binding.FatText.getText().toString();

            String fider = binding.fiberText.getText().toString();
            String sugar = binding.SugarText.getText().toString();
            String water = binding.WaterText.getText().toString();
            String mfat = binding.MFatText.getText().toString();
            String nfat = binding.NFatText.getText().toString();
            String pfat = binding.PFatsText.getText().toString();
            String tfat = binding.TFatsText.getText().toString();

            String recipe = binding.ReIpeText.getText().toString();

            if (!name.equals("") && !cal.equals("") && !bel.equals("") && !yql.equals("") && !fat.equals("")){
                Food food = new Food(name,
                        Float.parseFloat(cal) / 100,
                        Float.parseFloat(bel) / 100,
                        Float.parseFloat(yql) / 100,
                        Float.parseFloat(fat) / 100) ;
                if (!fider.equals("")) food.setFiber(Float.parseFloat(fider) / 100);
                if (!sugar.equals("")) food.setSugar(Float.parseFloat(sugar) / 100);
                if (!water.equals("")) food.setWater(Integer.parseInt(water) / 100);
                if (!mfat.equals("")) food.setMFats(Float.parseFloat(mfat) / 100);
                if (!nfat.equals("")) food.setSFats(Float.parseFloat(nfat) / 100);
                if (!pfat.equals("")) food.setPFats(Float.parseFloat(pfat) / 100);
                if (!tfat.equals("")) food.setTFats(Float.parseFloat(tfat) / 100);
                if (bitmap != null) food.setImage(bitmap);
                food.setPublic(binding.IsPublicSwitch.isChecked());
                food.setRecipe(recipe);

                Preferences.saveNew(food, name);

                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.popBackStack();
            }
        });

        return view;
    }
}