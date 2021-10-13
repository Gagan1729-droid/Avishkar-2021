package com.example.avishkar_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.avishkar_2021.Fragments.HomeFragment;
import com.example.avishkar_2021.Fragments.MyActivityFragment;
import com.example.avishkar_2021.Fragments.ProfileFragment;
import com.example.avishkar_2021.Fragments.SellFragment;
import com.example.avishkar_2021.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ActionBar actionBar;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();
        actionBar.setTitle("Hello");

        binding.navigation.setOnNavigationItemSelectedListener(selectedListener);
        binding.navigation.setSelectedItemId(R.id.nav_home);
        HomeFragment fragment = new HomeFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, "");
        fragmentTransaction.commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            actionBar.setTitle("Home");
                            HomeFragment fragment = new HomeFragment();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container, fragment, "");
                            fragmentTransaction.commit();
                            return true;
                        case R.id.nav_activity:
                            actionBar.setTitle("My Activity");
                            MyActivityFragment fragment1 = new MyActivityFragment();
                            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction1.replace(R.id.container, fragment1, "");
                            fragmentTransaction1.commit();
                            return true;
                        case R.id.nav_profile:
                            actionBar.setTitle("Profile");
                            ProfileFragment fragment2 = new ProfileFragment();
                            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction2.replace(R.id.container, fragment2, "");
                            fragmentTransaction2.commit();
                            return true;
                        case R.id.nav_sell:
                            actionBar.setTitle("Sell");
                            SellFragment fragment3 = new SellFragment();
                            FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction3.replace(R.id.container, fragment3, "");
                            fragmentTransaction3.commit();
                            return true;
                        }
                    return false;
                }
            };

}