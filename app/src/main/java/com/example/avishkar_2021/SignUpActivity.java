package com.example.avishkar_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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

import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    boolean isNGO;
    LocationManager locationManager;
    static final int REQUEST_FINE_LOCATION = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
                if (email.isEmpty()) {
                    binding.emailText.setError("Email necessary");
                    return;
                } else binding.emailText.setError(null);

                if (name.isEmpty()) {
                    binding.nameText.setError("Name necessary");
                    return;
                } else binding.nameText.setError(null);

                if (password.isEmpty()) {
                    binding.passwordText.setError("Password is required");
                    return;
                } else binding.passwordText.setError(null);

                if (password.length() < 6) {
                    binding.passwordText.setError("Password must be atleast 6 characters");
                    return;
                } else binding.passwordText.setError(null);

                if (binding.checkBox.isChecked()) {
                    isNGO = true;
                }
                progressDialog.show();
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            long timestamp = new Date().getTime();
                            if (ActivityCompat.checkSelfPermission(SignUpActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                                ActivityCompat.requestPermissions(SignUpActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
                            }
                            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            double latitude = location.getLatitude(),
                                    longitude = location.getLongitude();
                            UserModel userModel = new UserModel(name, fAuth.getUid(), isNGO, timestamp, latitude, longitude);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_FINE_LOCATION){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }
        }
    }
}