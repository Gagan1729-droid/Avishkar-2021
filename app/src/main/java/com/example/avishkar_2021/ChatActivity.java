package com.example.avishkar_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.avishkar_2021.Adapters.ChatAdapter;
import com.example.avishkar_2021.Models.ExchangeItemModel;
import com.example.avishkar_2021.Models.MessagesModel;
import com.example.avishkar_2021.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
ActivityChatBinding binding;
ExchangeItemModel model;
FirebaseDatabase database;
FirebaseAuth fAuth;
String sender_id, receiver_id;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        String json = getIntent().getStringExtra("ItemModelClass");
        model = new Gson().fromJson(json, ExchangeItemModel.class);
        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();

        sender_id = fAuth.getUid();
        receiver_id = model.getSeller_id();
        binding.userName.setText(model.getSeller_name());
        reference = database.getReference().child("Exchanges Available").child(model.getItem_id()).child("chats").child(sender_id);

        ArrayList<MessagesModel> list = new ArrayList<>();
        ChatAdapter adapter = new ChatAdapter(list, this, receiver_id);
        binding.chatRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        binding.backArrow.setOnClickListener(v -> {
            finish();
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    MessagesModel messagesModel = dataSnapshot.getValue(MessagesModel.class);
                    messagesModel.setId(dataSnapshot.getKey());
                    list.add(messagesModel);
                }
                adapter.notifyDataSetChanged();
                binding.chatRecyclerView.scrollToPosition(list.size()-1);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        binding.send.setOnClickListener(v -> {
            String message = binding.etMessage.getText().toString().trim();
            if(message.equals(""))
                return;
            MessagesModel messagesModel = new MessagesModel(message, sender_id, new Date().getTime());
            reference.push().setValue(messagesModel)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            database.getReference().child("Users").child(receiver_id).child("Activity").child("Chats")
                                    .child(sender_id).push().setValue(messagesModel);
                        }
                    });
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}