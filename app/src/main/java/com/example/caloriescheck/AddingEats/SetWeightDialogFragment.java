package com.example.caloriescheck.AddingEats;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Day;
import com.example.caloriescheck.dto.Food;
import com.example.caloriescheck.enums.MealType;
import com.example.caloriescheck.localStorage.EncryptedPreferences;

import org.jspecify.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SetWeightDialogFragment extends DialogFragment {
    private static final String ARG_RECYCLE_ITEM = "recycle_item";

    private MainModelView mainModelView;
    private  int weight = 0;

    public static SetWeightDialogFragment newInstance(Food item, MealType type) {
        SetWeightDialogFragment dialogFragment = new SetWeightDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RECYCLE_ITEM, item);
        args.putString("type", type.toString());
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getLayoutInflater().inflate(R.layout.set_weight_dialog, null);

        Bundle args = getArguments();
        Food item = args.getParcelable(ARG_RECYCLE_ITEM);
        MealType type = MealType.valueOf(args.getString("type"));

        TextView calRes = dialogView.findViewById(R.id.calResult);
        calRes.setText(item.getCalories() * 100 + "ккал");

        EditText setVes = dialogView.findViewById(R.id.weightText);

        setVes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    try {
                        weight = Integer.parseInt(s.toString());
                        float calories = item.getCalories() * weight;
                        calRes.setText(calories + " ккал");
                    } catch (NumberFormatException e) {
                        calRes.setText("Некорректное значение");
                    }
                } else {
                    calRes.setText("0 ккал");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return builder
                .setView(dialogView)
                .setTitle(item.getName())
                .setPositiveButton("Добавить", (dialog, which) -> {
                    mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
                    if (mainModelView.geNeedToAddFoods().getValue() == null)
                        mainModelView.setNeedToAddFoods(new ArrayList<>());
                    List<Day.Meal> foods = mainModelView.geNeedToAddFoods().getValue();
                    foods.add(new Day.Meal(type, item, weight));
                    mainModelView.setNeedToAddFoods(foods);
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
