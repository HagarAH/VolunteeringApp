package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.common.base.Verify;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.controllers.UserLocationController;
import com.mobil.bizden.models.User;

public class FirstLogin extends AppCompatActivity {
    private Button nextBtn;
    private TextView logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        nextBtn= findViewById(R.id.firstLoginBtn);
        UserController userController = new UserController();
        logoutBtn=findViewById(R.id.logoutBtnFL);
        UserLocationController.UserLocationCheck callbackLocation= new UserLocationController.UserLocationCheck() {
            @Override
            public void onLocationFound() {
                Intent intent = new Intent(FirstLogin.this, Home.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLocationNotFound() {
                Intent intent = new Intent(FirstLogin.this, UserLocation.class);
                startActivity(intent);
                finish();
            }
        };
        ProfileController.ProfileCompletenessCheckCallback isComplete= new ProfileController.ProfileCompletenessCheckCallback(){

            @Override
            public void onIdIncomplete() {
                Intent intent = new Intent(FirstLogin.this, Identification.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onPhoneIncomplete() {
                Intent intent = new Intent(FirstLogin.this, PhoneVerify.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onProfileComplete() {
                UserLocationController userLocationController= new UserLocationController();
                userLocationController.checkUserLocation(userController.getCurrentUser().getUid(),callbackLocation);
            }

            @Override
            public void onCheckError(Exception e) {
                System.out.println(e);

            }
        };

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileController profileController= new ProfileController();
                profileController.checkProfileCompleteness(userController.getCurrentUser().getUid(), isComplete);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userController.logoutUser();
                Intent intent= new Intent(FirstLogin.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}