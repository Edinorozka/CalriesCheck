package com.example.caloriescheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

public class TestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

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

    public void startTest(View view) {
        Intent intent = new Intent();
        /*switch (view.getId()){
            case R.id.button1:
                intent.putExtra("start", "start");
                intent.putExtra("title",  "Никакого шоколада");
                intent.putExtra("Description",  "Откажись от шоколада на 30 дней");
                break;
        }*/
        finish();
    }
}