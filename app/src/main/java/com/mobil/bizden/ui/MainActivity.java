package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;

public class MainActivity extends AppCompatActivity {


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
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            finish();
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


