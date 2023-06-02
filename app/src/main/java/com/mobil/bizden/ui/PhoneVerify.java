package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.UserController;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneVerify extends AppCompatActivity {
    PhoneAuthProvider.ForceResendingToken mResendToken;
    String mVerificationId;
    EditText phoneNum;
    Button verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        verifyBtn= findViewById(R.id.buttonNextOtp);
        phoneNum=findViewById(R.id.editTextPhoneNumber);
         String  phoneNumber;
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!phoneNum.getText().toString().isEmpty())


                startPhoneNumberVerification( "+90 "+ phoneNum.getText().toString(), PhoneVerify.this);

                else {
                    phoneNum.setError("Lütfen telefen numaranızı bu formata uyacak şekilde yapınız: XXXXXXXXXX ");
                    }

            }
        });

    }
//    public static boolean verifyTelephoneNumber(String phoneNumber) {
//        // Regular expression pattern for a valid telephone number in the pattern "+90 233 345 56 56"
//        String pattern = "^\\s\\d{10}$";
//
//        // Create a Pattern object with the given pattern
//        Pattern regexPattern = Pattern.compile(pattern);
//
//        // Create a Matcher object for the given input
//        Matcher matcher = regexPattern.matcher(phoneNumber);
//
//        // Return true if the input matches the pattern, false otherwise
//        return matcher.matches();
//    }

    // Method to start phone number verification
    public void startPhoneNumberVerification(String phoneNumber, Activity activity) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                activity,
                mCallbacks);
    }

    // Callback for phone number verification
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    // Handle error
                }

                @Override
                public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

                    // Create an Intent to start the second activity
                    Intent intent = new Intent(PhoneVerify.this, SMSVerification.class);
                    intent.putExtra("verificationId", verificationId);
                    intent.putExtra("resendToken", token);
                    intent.putExtra("phoneNumber", phoneNum.getText().toString());

                    startActivity(intent);
                    finish();

                }
            };


}