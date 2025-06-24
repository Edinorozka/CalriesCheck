package com.example.caloriescheck.localStorage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.caloriescheck.dto.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class Preferences {
    private static final String PREFS_FILENAME = "Foods_preferences";
    private static SharedPreferences sharedPreferences;

    public static void getPreferences(Context context) {
        try{
            sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Food> getAllFood(){
        Map<String, ?> allEntries = sharedPreferences.getAll();
        List<Food> filteredEntries = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().contains("Food")) {
                filteredEntries.add(Food.fromJson(entry.getValue().toString()));
            }
        }
        return filteredEntries;
    }

    public static Food getOneFood(String name){
        return Food.fromJson(sharedPreferences.getString("Food_" + name, null));
    }

    public static boolean saveNew(Food food, String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Food_" + name, food.getJson());
        return editor.commit();
    }
}
