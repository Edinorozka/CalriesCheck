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
import com.example.caloriescheck.databinding.CreateAccountFragmentBinding;

public class CreateAccountFragment extends Fragment {

    private CreateAccountFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = CreateAccountFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Editable name = binding.sendName.getText();
        Editable email = binding.sendEmail.getText();
        Editable password = binding.sendPassword.getText();
        Editable password2 = binding.sendPassword2.getText();

        binding.endCreateAccountButton.setOnClickListener(v -> {
            if (email.length() > 0 && password.length() > 0 && password2.length() > 0){
                if (password.toString().equals(password2.toString())){
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

                    Bundle bundle = new Bundle();
                    bundle.putString("name", name.length() > 0 ? name.toString() : email.toString());
                    bundle.putString("email", email.toString());
                    bundle.putString("password", password.toString());

                    navController.navigate(R.id.selectAGoalFragment, bundle);
                } else {
                    binding.ErrorTextView.setText("пароли не совпадают");
                    binding.ErrorTextView.setVisibility(VISIBLE);
                }
            } else {
                binding.ErrorTextView.setText("Обязательные поля не заполнены");
                binding.ErrorTextView.setVisibility(VISIBLE);
            }
        });

        binding.EnterAccountButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.authenticationAccountFragment);
        });

        return view;
    }
}