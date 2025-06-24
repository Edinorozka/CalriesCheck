package com.example.caloriescheck.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.caloriescheck.R;

import org.jetbrains.annotations.NotNull;

public class SettingsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    public void OpenSettings(View view){
        Intent intent;
        /*switch (view.getId()){
            case R.id.account:
                intent = new Intent(this,     AccountSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.profil:
                intent = new Intent(this,     ProfileSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.my_purposes:
                intent = new Intent(this,     SettingGoalActivity.class);
                startActivity(intent);
                break;
            case R.id.water_follow:
                intent = new Intent(this,     SettingsWaterActivity.class);
                startActivity(intent);
                break;
            case R.id.notifications:
                intent = new Intent(this,     SettingsNotificationsActivity.class);
                startActivity(intent);
                break;
        }*/
    }
}