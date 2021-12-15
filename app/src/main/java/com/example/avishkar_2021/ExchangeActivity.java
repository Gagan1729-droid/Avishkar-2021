package com.example.avishkar_2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.avishkar_2021.Models.ExchangeItemModel;
import com.example.avishkar_2021.Models.UserModel;
import com.example.avishkar_2021.databinding.ActivityExchangeBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class ExchangeActivity extends AppCompatActivity {

    ActivityExchangeBinding binding;
    FirebaseDatabase database;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    String uid;
    String pictures_storage_id;
    String seller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExchangeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pictures_storage_id = "";
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getUid();
        database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        long timestamp = new Date().getTime();
        String item_id = uid + timestamp;

         database.getReference().child("Users").child(uid).child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                seller = userModel.getName();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(ExchangeActivity.this);
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.itemName.getText().toString(),
                        description = binding.itemDescription.getText().toString(),
                        location = binding.location.getText().toString(),
                        exchanges = binding.eligibleExchanges.getText().toString();
                if (name.isEmpty()){
                    binding.itemName.setError("Title required");
                    return;
                }
                if(description.isEmpty()){
                    binding.itemDescription.setError("Description required");
                    return;
                }
                if(location.isEmpty()){
                    binding.location.setError("Please enter the city");
                    return;
                }
                if(exchanges.isEmpty()){
                    binding.eligibleExchanges.setError("Please enter atleast one exchanges");
                    return;
                }
                if(pictures_storage_id.equals("")){
                    Toast.makeText(ExchangeActivity.this, "Please upload atleast one image of the item", Toast.LENGTH_SHORT).show();
                    return;
                }

                ExchangeItemModel model = new ExchangeItemModel(name, location, description, item_id, exchanges, pictures_storage_id, seller, uid);

                database.getReference().child("Users").child(uid).child("Exchanges proposed").child(item_id).setValue(model);

                database.getReference().child("Exchanges Available").child(item_id).setValue(model);

            }
        });

        database.getReference().child("Users").child(uid).child("Exchanges proposed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if (dataSnapshot.getKey().equals(item_id)){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK){
            Uri image_uri = CropImage.getPickImageResultUri(this, data);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, image_uri)) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                startCrop(image_uri);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                storageReference = storageReference.child("Items").child(uid + Long.toString(new Date(). getTime()));
                storageReference.putFile(result.getUri()).addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        pictures_storage_id = pictures_storage_id + uri.toString() + "\n";
                    }
                }));
            }
        }
    }
    private void startCrop(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .setAllowFlipping(true)
                .setAllowRotation(true)
                .start(this);
    }
}