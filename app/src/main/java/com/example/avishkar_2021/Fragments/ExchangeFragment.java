package com.example.avishkar_2021.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avishkar_2021.ExchangeActivity;
import com.example.avishkar_2021.R;
import com.example.avishkar_2021.databinding.FragmentExchangeBinding;

public class ExchangeFragment extends Fragment {
    FragmentExchangeBinding binding;

    public ExchangeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExchangeBinding.inflate(inflater, container, false);
        binding.sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ExchangeActivity.class));
                getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        });
        return binding.getRoot();
    }
}