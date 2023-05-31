package com.mobil.bizden.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobil.bizden.R;

public class UserProfile extends AppCompatActivity {

    private ImageView imageViewsettings;
    private Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        button = findViewById(R.id.updateButton);
        imageViewsettings = findViewById(R.id.imageViewsettings);
        imageViewsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, Identification.class);
                startActivity(intent);
            }
        });

    }




}
