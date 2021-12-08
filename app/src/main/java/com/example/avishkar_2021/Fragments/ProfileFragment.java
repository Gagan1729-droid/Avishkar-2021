package com.example.avishkar_2021.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avishkar_2021.EditProfileDialogFragment;
import com.example.avishkar_2021.Models.UserModel;
import com.example.avishkar_2021.R;
import com.example.avishkar_2021.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    String uid;
    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getUid();
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Users").child(uid).child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                binding.nameProfile.setText(userModel.getName());
//                if(!userModel.getLocation().isEmpty())
//                    binding.location.setText(userModel.getLocation());
//                else
                    binding.location.setText("Location not added");
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        binding.editProfile.setOnClickListener(v -> {
            EditProfileDialogFragment dialogFragment = new EditProfileDialogFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if(prev!=null){
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            dialogFragment.show(ft, "dialog");
        });

        return binding.getRoot();
    }
}