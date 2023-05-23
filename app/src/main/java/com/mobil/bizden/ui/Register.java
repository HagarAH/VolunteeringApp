package com.mobil.bizden.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.databinding.ActivityMainBinding;

public class Register extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    UserController userController= new UserController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);
        EditText passwordE = findViewById(R.id.passwordEditText);
        EditText passConfirmE= findViewById(R.id.passwordConfirmEditText);
        EditText emailE= findViewById(R.id.emailEditText);
        Button btnRegister= findViewById(R.id.registerButton);
        ProgressBar progressBar= findViewById(R.id.progressBar);
        TextView login = findViewById(R.id.loginNow);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        UserController.RegistrationCallback registrationCallback= new UserController.RegistrationCallback() {
            @Override
            public void onRegistrationSuccess(FirebaseUser user) {
                Toast.makeText(Register.this,"Hesap Oluşturuldu. Lütfen giriş yap",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRegistrationFailure(String errorMessage) {

                Toast.makeText(Register.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        };
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password, confirmPassword;
                email= String.valueOf(emailE.getText());
                password= String.valueOf(passwordE.getText());
                confirmPassword=String.valueOf(passConfirmE.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Lütfen e-postanızı giriniz.",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"Lütfen şifreyi giriniz.",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Register.this,"Lütfen şifre tekrarını giriniz.",Toast.LENGTH_SHORT).show();
                    return;
                }
                userController.registerUser(email.trim(),password, registrationCallback);



            }
        });
    }
}