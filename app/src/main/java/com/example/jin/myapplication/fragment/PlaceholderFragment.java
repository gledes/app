package com.example.jin.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jin.myapplication.R;

public class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);
        rootView.findViewById(R.id.btnOpenAnohterFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, new AnotherFragment())
                        .commit();

            }
        });

        rootView.findViewById(R.id.btnOpenSliderActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), SliderActivity.class));
                Intent intent = new Intent(getActivity(), SliderActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
