package com.example.caloriescheck.dto;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Food implements Parcelable {
    private String name;
    private Bitmap image;
    private String recipe;
    private float calories;
    private float squirrels; //белки
    private float carbohydrates; //углеводы
    private float fats;
    private float sugar;
    private float fiber; //пищевые волокна
    private float sFats; //насыщенные жиры
    private float mFats; //мононенасыщенные жиры
    private float pFats; //полиненасыщенные жиры
    private float tFats; //трансжиры
    private int water;
    private boolean isMain;
    private boolean isPublic;

    protected Food(Parcel in) {
        name = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
        recipe = in.readString();
        calories = in.readFloat();
        squirrels = in.readFloat();
        carbohydrates = in.readFloat();
        fats = in.readFloat();
        sugar = in.readFloat();
        fiber = in.readFloat();
        sFats = in.readFloat();
        mFats = in.readFloat();
        pFats = in.readFloat();
        tFats = in.readFloat();
        water = in.readInt();
        isMain = in.readByte() != 0;
        isPublic = in.readByte() != 0;
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Food(String name, float calories, float squirrels, float carbohydrates, float fats) {
        this.name = name;
        this.calories = calories;
        this.squirrels = squirrels;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Food fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Food.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(image, flags);
        dest.writeString(recipe);
        dest.writeFloat(calories);
        dest.writeFloat(squirrels);
        dest.writeFloat(carbohydrates);
        dest.writeFloat(fats);
        dest.writeFloat(sugar);
        dest.writeFloat(fiber);
        dest.writeFloat(sFats);
        dest.writeFloat(mFats);
        dest.writeFloat(pFats);
        dest.writeFloat(tFats);
        dest.writeInt(water);
        dest.writeByte((byte) (isMain ? 1 : 0));
        dest.writeByte((byte) (isPublic ? 1 : 0));
    }
}
