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
import com.example.caloriescheck.databinding.FragmentDetailAnalysisBinding;


public class DetailAnalysis extends Fragment {

    private FragmentDetailAnalysisBinding analysis;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        analysis = FragmentDetailAnalysisBinding.inflate(inflater, container, false);
        View root = analysis.getRoot();

        Toolbar toolbar = root.findViewById(R.id.detailToolbar);
        getActivity().setActionBar(toolbar);
        TextView toolbarText = root.findViewById(R.id.toolbarText);
        toolbarText.setText("Анализ");

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
        analysis = null;
    }
}
