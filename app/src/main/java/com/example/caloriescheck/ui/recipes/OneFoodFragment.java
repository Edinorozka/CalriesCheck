package com.example.caloriescheck.ui.recipes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.FragmentOneFoodBinding;
import com.example.caloriescheck.dto.Food;

public class OneFoodFragment extends Fragment {
    private FragmentOneFoodBinding binding;
    private View view;
    private Food food;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        binding = FragmentOneFoodBinding.inflate(inflater, container, false);
        view = binding.getRoot();


        food = args.getParcelable("food");

        Toolbar toolbar = view.findViewById(R.id.oneFoodToolbar);
        getActivity().setActionBar(toolbar);
        TextView toolbarText = view.findViewById(R.id.toolbarText);
        toolbarText.setText(food.getName());

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

        if (food.getImage() != null) binding.OneFoodImageView.setImageBitmap(food.getImage());
        binding.CalTextView.setText((int) food.getCalories() * 100 + " ккал");
        binding.BelTextView.setText((int) (food.getSquirrels() * 100) + " гр");
        binding.YglTextView.setText((int) (food.getCarbohydrates() * 100) + " гр");

        binding.FiberTextView.setText((int) (food.getFiber() * 100) + " гр");
        binding.SugarTextView.setText((int) (food.getSugar() * 100) + " гр");

        binding.FatTextView.setText((int) (food.getFats() * 100) + " гр");
        binding.NFatTextView.setText((int) (food.getSFats() * 100) + " гр");
        binding.MFATTextView.setText((int) (food.getMFats() * 100) + " гр");
        binding.PFatTextView.setText((int) (food.getPFats() * 100) + " гр");
        binding.TFatTextView.setText((int) (food.getTFats() * 100) + " гр");

        binding.WaterTextView.setText(food.getWater() + " мм");

        binding.RecipeTextView.setText(food.getRecipe());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}