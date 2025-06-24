package com.example.caloriescheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.caloriescheck.databinding.ActivityMainBinding;
import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.dto.User;
import com.example.caloriescheck.localStorage.EncryptedPreferences;
import com.example.caloriescheck.localStorage.Preferences;
import com.example.caloriescheck.settings.SettingsMainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainModelView mainModelView;
    private User user;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EncryptedPreferences.getEncryptedSharedPreferences(this);
        Preferences.getPreferences(this);
        mainModelView = new ViewModelProvider(this).get(MainModelView.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_recipes, R.id.navigation_account)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavDestination currentDestination = navController.getCurrentDestination();

                if (currentDestination != null && currentDestination.getId() == R.id.createAccountFragment) {
                    finish();
                } else {
                    setEnabled(false);
                    MainActivity.super.onBackPressed();
                }
            }
        });

        user = EncryptedPreferences.getUser();
        if (user == null){
            navController.navigate(R.id.createAccountFragment);
            binding.navView.setVisibility(View.GONE);
        } else {
            binding.navView.setVisibility(View.VISIBLE);
        }

        if (user.getStartWeight() == 0){
            user.setStartWeight(user.getWeightNow());
            EncryptedPreferences.saveUser(user.getJson());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String today = dateFormat.format(new Date());

        if (EncryptedPreferences.getDay(today) == null){
            Operation operation = new Operation();

            int cal = operation.sumKal(user);
            int bel = operation.sumBel(user);
            int ygl = operation.sumYGL(user);
            int fat = operation.sumGir(user);
            Day day = new Day(2, cal, bel, ygl, fat, today);
            EncryptedPreferences.saveDay(today, day.getJson());
            mainModelView.setCurrentDay(day);
        } else {
            mainModelView.setCurrentDay(EncryptedPreferences.getDay(today));
        }

        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}
