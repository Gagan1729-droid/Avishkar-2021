package com.example.avishkar_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.avishkar_2021.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

                }
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