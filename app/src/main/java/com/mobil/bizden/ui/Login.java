package com.mobil.bizden.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.controllers.UserLocationController;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView register;

    private void showPasswordResetFragment() {
        PasswordReset_frag passwordResetFragment = new PasswordReset_frag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, passwordResetFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    UserController userController = new UserController();


    ProfileController profileController = new ProfileController();

    UserLocationController.UserLocationCheck callbackLocation= new UserLocationController.UserLocationCheck() {
        @Override
        public void onLocationFound() {
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onLocationNotFound() {
            Intent intent = new Intent(Login.this, FirstLogin.class);
            startActivity(intent);
            finish();
        }
    };


    ProfileController.ProfileCompletenessCheckCallback checkCallback = new ProfileController.ProfileCompletenessCheckCallback() {

        @Override
        public void onIdIncomplete() {
            Intent intent = new Intent(Login.this, FirstLogin.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onPhoneIncomplete() {
            Intent intent = new Intent(Login.this, FirstLogin.class);
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

    UserController.LoginCallback loginCallback = new UserController.LoginCallback() {


        @Override
        public void onSuccess(FirebaseUser userid) {
            profileController.checkProfileCompleteness(userid.getUid(), checkCallback);
        }

        @Override
        public void onFailure(Exception exception) {
            Toast.makeText(Login.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        EditText passwordE = findViewById(R.id.LpasswordEditText);
        EditText emailE = findViewById(R.id.LemailEditText);
        Button btnLogin = findViewById(R.id.loginButton);
        ProgressBar progressBar = findViewById(R.id.progressBarLogin);
        register = findViewById(R.id.registerNow);
        TextView passwordReset = findViewById(R.id.passwordReset);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }

        });
        passwordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPasswordResetFragment();
            }
        });
        TextView toplanma = findViewById(R.id.toplanma);
        toplanma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),toplanma.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(emailE.getText());
                password = String.valueOf(passwordE.getText());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Lütfen e-postanızı giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Lütfen şifreyi giriniz.", Toast.LENGTH_SHORT).show();
                    return;
                }
                userController.loginUser(email.trim(), password, progressBar, loginCallback);

            }
        });


    }

}