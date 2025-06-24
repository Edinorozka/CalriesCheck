package com.example.caloriescheck.ui.CaloriesControl;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caloriescheck.R;

import org.jetbrains.annotations.NotNull;

public class NotesPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_panel);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}
