package com.project.healthstatus.ui.gather_insights;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.project.healthstatus.R;
import com.project.healthstatus.display_summery;

public class GatherInsightsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gather_insights, container, false);

        Intent intent = new Intent(getContext() , display_summery.class);
        startActivity(intent);
        return root;
    }
}