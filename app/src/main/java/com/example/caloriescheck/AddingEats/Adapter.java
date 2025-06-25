package com.example.caloriescheck.AddingEats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Food;
import com.example.caloriescheck.enums.MealType;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<Food> listItem;
    private MealType type;

    public Adapter(List<Food> listItem, MealType type) {
        this.listItem = listItem;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        final Food item = listItem.get(position);
        holder.textTitle.setText(item.getName());
        holder.textDescription.setText("В 100 гр./мм.");
        holder.cal.setText(item.getCalories() * 100 + " ккал.");
        holder.itemView.setOnClickListener(v -> {
            FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
            SetWeightDialogFragment dialogFragment = SetWeightDialogFragment.newInstance(item, type);
            dialogFragment.show(fragmentManager, item.getName());
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textTitle;
        public TextView textDescription;
        public TextView cal;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.text_Description);
            cal = itemView.findViewById(R.id.kkalories);
        }
    }

}
