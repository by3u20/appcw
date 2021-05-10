package com.example.delivery1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.delivery1.AddDeliveryActivity;
import com.example.delivery1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.delivery1.fragments.AddDeliveries;

public class Deliveries extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deliveries,container,false);
        FloatingActionButton addbutton = view.findViewById(R.id.add_delivery_button);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDeliveryActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}