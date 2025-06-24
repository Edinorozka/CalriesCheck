package com.example.caloriescheck.localStorage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.dto.User;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class EncryptedPreferences {
    private static final String PREFS_FILENAME = "encrypted_prefs";
    private static EncryptedSharedPreferences preferences;

    public static void getEncryptedSharedPreferences(Context context){
        try {
            preferences = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(
                    context,
                    PREFS_FILENAME,
                    new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveUser(String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("User", value);
        editor.apply();
    }

    public static User getUser(){
        String json = preferences.getString("User", null);
        if (json != null){
            return User.fromJson(json);
        }
        else
            return null;
    }

    public static void deleteObject(String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(value);
        editor.apply();
    }

    public static void saveDay(String day, String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(day, value);
        editor.apply();
    }

    public static Day getDay(String day){
        String json = preferences.getString(day, null);
        if (json != null){
            return Day.fromJson(json);
        }
        else
            return null;
    }
}
