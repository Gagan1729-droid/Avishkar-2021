package com.example.avishkar_2021;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avishkar_2021.databinding.ActivityMainSplashBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainSplash extends AppCompatActivity {
ActivityMainSplashBinding binding;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainSplashBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        fAuth = FirebaseAuth.getInstance();

        // Transparent Action Bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        // To hide toolbar
        getSupportActionBar().hide();

        // Setting timing for splash screen and moving to next activity
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    Intent intent;
                    if(fAuth.getCurrentUser()==null){
                        intent = new Intent(MainSplash.this, SignInUpActivity.class);
                    }
                    else {
                        intent = new Intent(MainSplash.this, MainActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();

    }

}