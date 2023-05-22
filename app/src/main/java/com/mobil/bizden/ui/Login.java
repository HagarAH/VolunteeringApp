package com.mobil.bizden.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;
import com.mobil.bizden.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    TextView register;
    private void showPasswordResetFragment() {
        passwordReset passwordResetFragment = new passwordReset();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, passwordResetFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        EditText passwordE = findViewById(R.id.LpasswordEditText);
        EditText emailE= findViewById(R.id.LemailEditText);
        Button btnLogin= findViewById(R.id.loginButton);
        ProgressBar progressBar= findViewById(R.id.progressBarLogin);
        register = findViewById(R.id.registerNow);
        TextView passwordReset= findViewById(R.id.passwordReset);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email= String.valueOf(emailE.getText());
                password= String.valueOf(passwordE.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this,"Lütfen e-postanızı giriniz.",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this,"Lütfen şifreyi giriniz.",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth=FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    Intent intent = new Intent(getApplicationContext(),Home.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });




            }
        });
    }

}