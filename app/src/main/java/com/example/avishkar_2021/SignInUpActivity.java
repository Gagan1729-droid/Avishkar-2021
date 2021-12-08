package com.example.avishkar_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.avishkar_2021.databinding.ActivitySignInUpBinding;

import org.jetbrains.annotations.NotNull;

public class SignInUpActivity extends AppCompatActivity {
    ActivitySignInUpBinding binding;
    static final int REQUEST_FINE_LOCATION = 11;
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

        if (ActivityCompat.checkSelfPermission(SignInUpActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(SignInUpActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_FINE_LOCATION){
            if(!(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                Toast.makeText(this, "Please grant location permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }
}