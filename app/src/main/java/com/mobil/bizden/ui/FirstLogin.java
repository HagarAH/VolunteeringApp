package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.common.base.Verify;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;
import com.mobil.bizden.controllers.UserController;

public class FirstLogin extends AppCompatActivity {
Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        nextBtn= findViewById(R.id.firstLoginBtn);
        UserController userController = new UserController();
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
                Intent intent = new Intent(FirstLogin.this, Home.class);
                startActivity(intent);
                finish();
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

    }
}