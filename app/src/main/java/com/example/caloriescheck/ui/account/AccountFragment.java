package com.example.caloriescheck.ui.account;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.caloriescheck.databinding.FragmentAccountBinding;
import com.example.caloriescheck.dto.User;
import com.example.caloriescheck.localStorage.EncryptedPreferences;
import com.example.caloriescheck.ui.CaloriesControl.Calendar;
import com.example.caloriescheck.ui.CaloriesControl.DetailAnalysis;
import com.example.caloriescheck.Operation;
import com.example.caloriescheck.R;
import com.example.caloriescheck.settings.SettingGoalActivity;

public class AccountFragment extends Fragment {
    private User user;
    private View root;
    private FragmentAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        user = EncryptedPreferences.getUser();

        if (user.getPurpose() == 3) binding.GoalProgress.setVisibility(GONE);
        else binding.GoalProgress.setVisibility(VISIBLE);

        binding.userName.setText(user.getName());

        TextView goal = root.findViewById(R.id.weightTextView);
        TextView finish = root.findViewById(R.id.targetTextView);
        binding.activityTextView.setText("Активность: " + getResources().getStringArray(R.array.activity)[user.getActivity()]);

        goal.setText("Вес: " + user.getWeightNow() + " кг");

        switch(user.getPurpose()){
            case 1:
                finish.setText("Цель: Потеря веса");
                break;
            case 2:
                finish.setText("Цель: Нарастить мышщы");
                break;
            case 3:
                finish.setText("Цель: Поддержание веса");
                break;
        }

        binding.Analiz.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.detailAnalysis);
        });

        return root;
    }
}
