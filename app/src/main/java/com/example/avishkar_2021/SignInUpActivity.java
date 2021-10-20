package com.example.avishkar_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.avishkar_2021.databinding.ActivitySignInUpBinding;

public class SignInUpActivity extends AppCompatActivity {
ActivitySignInUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();

        binding.signin.setOnClickListener(v -> {
            Intent i = new Intent(SignInUpActivity.this, SignInActivity.class);
            startActivity(i);
        });

        binding.signup.setOnClickListener(v -> {
            Intent i = new Intent(SignInUpActivity.this, SignUpActivity.class);
            startActivity(i);
        });

        binding.skip.setOnClickListener(v -> {
            Intent i = new Intent(SignInUpActivity.this, MainActivity.class);
            startActivity(i);
        });

    }
}