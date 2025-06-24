package com.example.caloriescheck.ui.CaloriesControl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.caloriescheck.R;
import com.example.caloriescheck.databinding.FragmentCalendarBinding;


public class Calendar extends Fragment {

    private FragmentCalendarBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Toolbar toolbar = root.findViewById(R.id.calendarToolbar);
        getActivity().setActionBar(toolbar);
        TextView toolbarText = root.findViewById(R.id.toolbarText);
        toolbarText.setText("Календать");

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            getActivity().getActionBar().setDisplayShowHomeEnabled(true);
            getActivity().getActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
