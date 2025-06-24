package com.example.caloriescheck.startFragments;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.SetStartParametersFragmentBinding;
import com.example.caloriescheck.dto.User;
import com.example.caloriescheck.localStorage.EncryptedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SetStartParametersFragment extends Fragment {

    private SetStartParametersFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = SetStartParametersFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Editable EWeight = binding.sendWeight.getText();
        Editable ETargetWeight = binding.sendTargetWeight.getText();
        Editable EHeight = binding.sendHeight.getText();


        binding.endCreateAccountButton.setOnClickListener(v -> {
            if (EWeight.length() > 0 &&
                    ETargetWeight.length() > 0 &&
                    EHeight.length() > 0 &&
                    binding.selectGender.getSelectedItem() != null &&
                    binding.selectActivity.getSelectedItem() != null){
                getArguments().getString("password");

                User user = new User();
                user.setName(getArguments().getString("name"));
                user.setLogin(getArguments().getString("email"));
                user.setPurpose(getArguments().getByte("type"));
                user.setWeightNow(Integer.parseInt(EWeight.toString()));
                user.setTargetWeight(Integer.parseInt(ETargetWeight.toString()));
                user.setHeight(Float.parseFloat(EHeight.toString()));
                user.setGender(binding.selectGender.getSelectedItem().toString().equals("мужчина"));
                user.setActivity((byte) binding.selectActivity.getSelectedItemPosition());

                Editable birthday = binding.sendBirthday.getText();
                if (birthday.length() > 0){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                    try {
                        user.setBirthday(dateFormat.parse(birthday.toString()));
                    } catch (ParseException e) {
                        binding.ErrorTextView.setText("Дата введена некоректно");
                        binding.ErrorTextView.setVisibility(VISIBLE);
                    }
                }
                user.setNormalCaloriesInDay(1);

                EncryptedPreferences.saveUser(user.getJson());
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navigation_home);

            } else {
                binding.ErrorTextView.setText("Обязательные поля не заполнены");
                binding.ErrorTextView.setVisibility(VISIBLE);
            }

        });

        return view;
    }
}