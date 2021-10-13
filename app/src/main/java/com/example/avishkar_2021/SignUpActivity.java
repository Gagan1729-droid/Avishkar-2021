package com.example.avishkar_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.avishkar_2021.Models.UserModel;
import com.example.avishkar_2021.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
FirebaseAuth fAuth;
FirebaseDatabase database;
boolean isNGO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("We are creating your account");

        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.userName.getText().toString(),
                        email = binding.email.getText().toString(),
                        password = binding.password.getText().toString();
                if(email.isEmpty()){
                    binding.email.setError("Email necessary");
                    return;
                }
                if(name.isEmpty()){
                    binding.userName.setError("Name necessary");
                    return;
                }
                if(password.isEmpty()){
                    binding.password.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    binding.password.setError("Password must be atleast 6 characters");
                    return;
                }
                if (binding.checkBox.isChecked()){
                    isNGO = true;
                }
                progressDialog.show();
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            UserModel userModel = new UserModel(name, fAuth.getUid(), isNGO);
                            database.getReference().child("Users").child(fAuth.getUid()).child("Details").setValue(userModel);
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            finish();
                            Toast.makeText(SignUpActivity.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                SignUpActivity.this.finish();
            }
        });
    }
}