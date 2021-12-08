package com.example.avishkar_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.avishkar_2021.Fragments.ExchangeFragment;
import com.example.avishkar_2021.Fragments.HomeFragment;
import com.example.avishkar_2021.Fragments.MyActivityFragment;
import com.example.avishkar_2021.Fragments.ProfileFragment;
import com.example.avishkar_2021.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ActionBar actionBar;
    BottomNavigationView navigationView;
    FirebaseAuth fAuth;
    MeowBottomNavigation bottomNavigation;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fAuth = FirebaseAuth.getInstance();
        frameLayout = binding.frame;
        bottomNavigation = binding.bottomnavigation;
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_forum_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_dollars_money_bag_on_a_hand));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_account_circle_24));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new ExchangeFragment();
                        break;
                    case 2:
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        fragment = new MyActivityFragment();
                        break;
                    case 4:
                        fragment = new ProfileFragment();
                        break;
                    default : fragment = new HomeFragment();
                }
                loadfragment(fragment);
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });
        bottomNavigation.show(2, true);
    }

    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame,fragment,"").
                commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            fAuth.signOut();
            startActivity(new Intent(MainActivity.this, SignInUpActivity.class));
            MainActivity.this.finish();
            Toast.makeText(this, "Logged out Successfully", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}