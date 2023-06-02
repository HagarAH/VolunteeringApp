package com.mobil.bizden.ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;
import com.mobil.bizden.models.Profile;

import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
            private ImageView logoImageView;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                logoImageView = findViewById(R.id.logo_image_view);
                logoImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth mAuth= FirebaseAuth.getInstance();
                        FirebaseUser user= mAuth.getCurrentUser();

                        if (user!= null){
                            ProfileController.ProfileCheckCallback profileCheckCallback= new ProfileController.ProfileCheckCallback() {
                                @Override
                                public void onProfileExists(Profile profile) {
                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onProfileEmpty() {
                                    Intent intent = new Intent(MainActivity.this, FirstLogin.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onProfileCheckError(Exception e) {
                                    System.out.println(e);
                                }
                            };
                            ProfileController profileController= new ProfileController();
                            profileController.checkDocument(user.getUid(),profileCheckCallback);
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
            }
        }


