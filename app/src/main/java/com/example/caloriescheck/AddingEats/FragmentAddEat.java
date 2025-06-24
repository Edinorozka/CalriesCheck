package com.example.caloriescheck.AddingEats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.caloriescheck.dto.Food;
import com.example.caloriescheck.localStorage.Preferences;
import com.example.caloriescheck.toolbars.FindFoodToolbarManager;

import java.util.ArrayList;
import java.util.List;


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

        Toolbar toolbar = root.findViewById(R.id.addEatToolbar);
        getActivity().setActionBar(toolbar);
        TextView toolbarText = root.findViewById(R.id.toolbarText);
        toolbarText.setText("Завтрак");

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
        mainModelView.setFoods(Preferences.getAllFood());
        mainModelView.getFoods().observe(getViewLifecycleOwner(), foods -> {
            adapter = new Adapter(foods);
            recyclerView.setAdapter(adapter);
        });

        View findFoodToolbarView = root.findViewById(R.id.toolbar3);
        toolbarManager = new FindFoodToolbarManager(findFoodToolbarView, mainModelView);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}