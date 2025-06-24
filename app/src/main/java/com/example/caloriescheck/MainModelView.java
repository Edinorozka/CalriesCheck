package com.example.caloriescheck;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.dto.Food;

import java.util.List;

public class MainModelView extends ViewModel {
    private MutableLiveData<Day> day = new MutableLiveData<>();
    private MutableLiveData<List<Food>> listFoods = new MutableLiveData<>();

    public LiveData<Day> getCurrentDay() {
        return day;
    }

    public void setCurrentDay(Day day) {
        this.day.setValue(day);
    }

    public LiveData<List<Food>> getFoods() {
        return listFoods;
    }

    public void setFoods(List<Food> foods) {
        listFoods.setValue(foods);
    }
}
