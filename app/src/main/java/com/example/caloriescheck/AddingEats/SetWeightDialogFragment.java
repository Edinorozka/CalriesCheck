package com.example.caloriescheck.AddingEats;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Food;

public class SetWeightDialogFragment extends DialogFragment {
    private static final String ARG_RECYCLE_ITEM = "recycle_item";
    public static SetWeightDialogFragment newInstance(Food item) {
        SetWeightDialogFragment dialogFragment = new SetWeightDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RECYCLE_ITEM, item);
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
                        int weight = Integer.parseInt(s.toString());
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
                .setPositiveButton("Добавить", null)
                .setNegativeButton("Отмена", null)
                .create();
    }
}
