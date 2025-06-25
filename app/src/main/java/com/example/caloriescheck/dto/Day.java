package com.example.caloriescheck.dto;

import com.example.caloriescheck.enums.MealType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Day {
    private String date;

    private float water;
    private float waterTarget;

    private int caloriesTarget;
    private int squirrelsTarget;
    private int carbohydratesTarget;
    private int fatsTarget;

    private List<Meal> foods = new ArrayList<>();

    @Data
    public static class Meal{
        private MealType type;
        private Food food;
        private int weight;

        public Meal(MealType type, Food food, int weight) {
            this.type = type;
            this.food = food;
            this.weight = weight;
        }
    }

    public Day(float waterTarget, int caloriesTarget, int squirrelsTarget, int carbohydratesTarget, int fatsTarget, String date) {
        this.waterTarget = waterTarget;
        this.caloriesTarget = caloriesTarget;
        this.squirrelsTarget = squirrelsTarget;
        this.carbohydratesTarget = carbohydratesTarget;
        this.fatsTarget = fatsTarget;
        this.date = date;
    }

    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Day fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Day.class);
    }
}
