package com.example.avishkar_2021;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.avishkar_2021.Models.UserModel;
import com.example.avishkar_2021.databinding.EditProfileDialogBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class EditProfileDialogFragment extends DialogFragment {
    EditProfileDialogBinding binding;
    View dialogView;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String uid;
    EditText ed_name, ed_location;
    TextView ngo;
    UserModel user;
    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.edit_profile_dialog, null);
        builder.setView(dialogView);
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Users").child(uid).child("Details");
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return dialogView;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ed_name = view.findViewById(R.id.name_edit_profile);
        ed_location = view.findViewById(R.id.edit_location);
        ngo = view.findViewById(R.id.ngo_name);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                user = snapshot.getValue(UserModel.class);
                ed_name.setText(user.getName());
                if (user.getLocation()!=null)
                    ed_location.setText(user.getLocation());
                else ed_location.setHint("No location added");
                if(user.isNGO()){
                    ngo.setVisibility(View.VISIBLE);
                    ngo.setText(user.getNgo_name());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        view.findViewById(R.id.save_button).setOnClickListener(v -> {
            String name = ed_name.getText().toString();
            String location = ed_location.getText().toString();
            if (name.isEmpty()){
                ed_name.setError("Name cannot be empty");
                return;
            }
            user.setName(name);
            if (!location.isEmpty())
                user.setLocation(location);
            reference.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), "Details updated", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}