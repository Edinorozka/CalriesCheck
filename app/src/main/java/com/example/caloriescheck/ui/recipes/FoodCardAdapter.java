package com.example.caloriescheck.ui.recipes;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Food;

import java.util.List;

public class FoodCardAdapter extends RecyclerView.Adapter<FoodCardAdapter.FoodViewHolder>{
    private List<Food> foods;

    public FoodCardAdapter(List<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.bind(food);

        holder.itemView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment);
            Bundle bundle = new Bundle();
            bundle.putParcelable("food", food);
            navController.navigate(R.id.oneFoodFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView calTextView;
        private TextView nameText;

        public FoodViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.FoodImageView);
            calTextView = itemView.findViewById(R.id.CalNumText);
            nameText = itemView.findViewById(R.id.NameText);
        }

        public void bind(Food item) {
            nameText.setText(item.getName());
            calTextView.setText((int)(item.getCalories() * 100) + " ккал");
            if (item.getImage() != null) imageView.setImageBitmap(item.getImage());
        }
    }
}
