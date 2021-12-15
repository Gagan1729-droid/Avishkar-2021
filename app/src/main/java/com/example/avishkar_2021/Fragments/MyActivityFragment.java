package com.example.avishkar_2021.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avishkar_2021.Adapters.FragmentsAdapter;
import com.example.avishkar_2021.R;
import com.example.avishkar_2021.databinding.FragmentHomeBinding;
import com.example.avishkar_2021.databinding.FragmentMyActivityBinding;

public class MyActivityFragment extends Fragment {
FragmentMyActivityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyActivityBinding.inflate(inflater, container, false);
//        binding.viewpager.setAdapter(new FragmentsAdapter((getSupportFragmentManager())));
//        binding.tablayout.setupWithViewPager(binding.viewpager);
        return binding.getRoot();
    }
}