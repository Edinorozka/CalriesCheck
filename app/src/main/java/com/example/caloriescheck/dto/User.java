package com.example.caloriescheck.dto;

import com.google.gson.Gson;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private int id;
    private String login;
    private String name;
    private float weightNow;
    private int targetWeight;
    private float startWeight;
    private boolean gender;
    private float height;
    private Date birthday;
    private byte purpose;
    private String ImageName;
    private int normalCaloriesInDay;
    private byte activity;

    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }
}
