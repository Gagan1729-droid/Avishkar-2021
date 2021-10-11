package com.example.avishkar_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.avishkar_2021.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
ActivitySignInBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString(),
                        passw = binding.password.getText().toString();
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }
        });
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                SignInActivity.this.finish();
            }
        });
    }
}