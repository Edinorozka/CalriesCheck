package com.example.caloriescheck.infoPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.caloriescheck.MainModelView;
import com.example.caloriescheck.R;
import com.example.caloriescheck.dto.Day;

public class FragmentConsumptionTable extends Fragment {
    private MainModelView mainModelView;
    
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_consumption_table, container, false);

        mainModelView = new ViewModelProvider(requireActivity()).get(MainModelView.class);
        mainModelView.getCurrentDay().observe(getViewLifecycleOwner(), day -> {
            TextView calories = view.findViewById(R.id.meaning1);
            TextView proteins = view.findViewById(R.id.meaning2);
            TextView carbohydrates = view.findViewById(R.id.meaning3);
            TextView dietaryFiber =  view.findViewById(R.id.meaning4);
            TextView sugar =  view.findViewById(R.id.meaning5);
            TextView fats =  view.findViewById(R.id.meaning6);
            TextView nFats =  view.findViewById(R.id.meaning7);
            TextView mFats =  view.findViewById(R.id.meaning8);
            TextView pFats =  view.findViewById(R.id.meaning9);
            TextView tFats =  view.findViewById(R.id.meaning10);

            int cal = 0;
            int bel = 0;
            int ygl = 0;
            int fiber = 0;
            int sugarNum = 0;
            int fat = 0;
            int nfat = 0;
            int mfat = 0;
            int pfat = 0;
            int tfat = 0;

            if (!day.getFoods().isEmpty()){
                for(Day.Meal m : day.getFoods()){
                    cal += m.getFood().getCalories() * m.getWeight();
                    bel += m.getFood().getSquirrels() * m.getWeight();
                    ygl += m.getFood().getCarbohydrates() * m.getWeight();
                    fiber += m.getFood().getFiber() * m.getWeight();
                    sugarNum += m.getFood().getSugar() * m.getWeight();
                    fat += m.getFood().getFats() * m.getWeight();
                    nfat += m.getFood().getSFats() * m.getWeight();
                    mfat += m.getFood().getMFats() * m.getWeight();
                    pfat += m.getFood().getPFats() * m.getWeight();
                    tfat += m.getFood().getTFats() * m.getWeight();
                }
            }

            calories.setText(cal + " ккал");
            proteins.setText(bel + " г");
            carbohydrates.setText(ygl + " г");
            dietaryFiber.setText(fiber + " г");
            sugar.setText(sugarNum + " г");
            fats.setText(fat + " г");
            nFats.setText(nfat + " г");
            mFats.setText(mfat + " г");
            pFats.setText(pfat + " г");
            tFats.setText(tfat + " г");
        });

        return view;
    }
}