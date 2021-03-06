package com.example.avishkar_2021.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.avishkar_2021.Adapters.ItemsAdapter;
import com.example.avishkar_2021.Models.ExchangeItemModel;
import com.example.avishkar_2021.Models.ItemModel;
import com.example.avishkar_2021.Models.UserModel;
import com.example.avishkar_2021.R;
import com.example.avishkar_2021.UtilityClasses.SpaceItemDecoration;
import com.example.avishkar_2021.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
FragmentHomeBinding binding;
ArrayList<ExchangeItemModel> list = new ArrayList<>();
FirebaseAuth fAuth;
DatabaseReference database;
UserModel userModel;
    public HomeFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        ItemsAdapter adapter = new ItemsAdapter(getContext(), list);
        binding.homeRecyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager  = new GridLayoutManager(getContext(), 2);
        binding.homeRecyclerView.setLayoutManager(gridLayoutManager);
        binding.homeRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        binding.homeRecyclerView.setItemAnimator(new DefaultItemAnimator());

        database = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        String uid = fAuth.getUid();
        if(uid!=null) {
            database.child("Users").child(uid).child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    userModel = snapshot.getValue(UserModel.class);
                    binding.welcomeMessage.setText("Welcome " + userModel.getName());
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }
        else binding.welcomeMessage.setText("Welcome User");

        database.child("Exchanges Available").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                int i=1;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ExchangeItemModel model = dataSnapshot.getValue(ExchangeItemModel.class);
                    list.add(model);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        adapter.notifyDataSetChanged();
        return binding.getRoot();
    }
}