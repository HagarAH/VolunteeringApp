package com.mobil.bizden.ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;

public class Home extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    DrawerLayout drawerLayout;
    private ImageView imageView;

    private TextView textViewprofil;
    private ImageView imageViewbarinak;

    private NavigationView  navigationDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageViewbarinak = findViewById(R.id.imageViewbarinak);
        imageViewbarinak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, kod.class);
                startActivity(intent);
            }
        });

        textViewprofil= findViewById(R.id.textViewprofil);
        textViewprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, UserProfile.class);
                startActivity(intent);
            }
        });
        imageView=findViewById(R.id.imageViewprofil);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, UserProfile.class);
                startActivity(intent);
            }
        });


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);

        // Set up the ActionBar and the navigation controller

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        Button logout= findViewById(R.id.logoutButton);
        if(user==null){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
        else {
            user.getEmail();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


    }


    public void onToggleClick(View view) {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        if (drawerLayout.isDrawerOpen(navigationDrawer)) {
            drawerLayout.closeDrawer(navigationDrawer);
        } else {
            drawerLayout.openDrawer(navigationDrawer);
        }
    }
}