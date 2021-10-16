package com.example.avishkar_2021.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.avishkar_2021.Adapters.ItemsAdapter;
import com.example.avishkar_2021.Models.ItemModel;
import com.example.avishkar_2021.Models.UserModel;
import com.example.avishkar_2021.R;
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
ArrayList<ItemModel> list = new ArrayList<>();
FirebaseAuth fAuth;
DatabaseReference database;
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
        binding.homeRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));

        database = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        String uid = fAuth.getUid();
        database.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UserModel model = dataSnapshot.getValue(UserModel.class);
                    binding.welcomeMessage.setText("Welcome " + model.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        ItemModel model = new ItemModel(330, "The Taj", "https://firebasestorage.googleapis.com/v0/b/avishkar-e4e7d.appspot.com/o/taj.png?alt=media&token=a0392db2-ef5d-4ff0-af62-7894bae8fa46");
        list.add(model);
        model = new ItemModel(232, "hfadsfdf", "https://firebasestorage.googleapis.com/v0/b/avishkar-e4e7d.appspot.com/o/taj.png?alt=media&token=a0392db2-ef5d-4ff0-af62-7894bae8fa46");
        list.add(model);
        adapter.notifyDataSetChanged();
        return binding.getRoot();
    }
}