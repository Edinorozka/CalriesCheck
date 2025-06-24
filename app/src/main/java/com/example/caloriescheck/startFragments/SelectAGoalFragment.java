package com.example.caloriescheck.startFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.FragmentSelectAGoalBinding;
import com.example.caloriescheck.enums.Purpose;

public class SelectAGoalFragment extends Fragment {

    private FragmentSelectAGoalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectAGoalBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        String name = getArguments().getString("name");
        String email = getArguments().getString("email");
        String password = getArguments().getString("password");

        binding.addMuscles.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("email", email);
            bundle.putString("password", password);
            bundle.putByte("type", Purpose.ADD_MUSCLES.getValue());

            navController.navigate(R.id.setStartParametersFragment, bundle);
        });

        binding.dropWeight.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("email", email);
            bundle.putString("password", password);
            bundle.putByte("type", Purpose.DROP_WEIGHT.getValue());

            navController.navigate(R.id.setStartParametersFragment, bundle);
        });

        binding.saveWeight.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("email", email);
            bundle.putString("password", password);
            bundle.putByte("type", Purpose.SAVE_WEIGHT.getValue());

            navController.navigate(R.id.setStartParametersFragment, bundle);
        });

        return view;
    }
}