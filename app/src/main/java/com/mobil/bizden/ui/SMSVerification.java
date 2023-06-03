package com.mobil.bizden.ui;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.controllers.UserLocationController;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.TimeUnit;

public class SMSVerification extends AppCompatActivity {
    private EditText smsArray1;
    private EditText smsArray2;
    private EditText smsArray3;
    private EditText smsArray4;
    private EditText smsArray5;
    private EditText smsArray6;
    private TextView smsResend;
    private String phoneNumber;
    private Button verifyBtn;
    private  String mVerificationId;
    private  String getPhoneNumber;
    private  PhoneAuthProvider.ForceResendingToken mresendToken;
    UserController userController= new UserController();
    FirebaseUser currentUser= userController.getCurrentUser();
    private EditText[] editTexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsverification);
        verifyBtn=findViewById(R.id.buttonVerifyOtp);
        smsArray1=findViewById(R.id.smsArray1);
        smsArray2=findViewById(R.id.smsArray2);
        smsArray3=findViewById(R.id.smsArray3);
        smsArray4=findViewById(R.id.smsArray4);
        smsArray5=findViewById(R.id.smsArray5);
        smsArray6=findViewById(R.id.smsArray6);
        EditText[] smsArray = {smsArray1, smsArray2, smsArray3, smsArray4, smsArray5, smsArray6};
        smsResend=findViewById(R.id.resendSMSTextView);
        Intent intent = getIntent();
        mVerificationId= intent.getStringExtra("verificationId");
        mresendToken= intent.getParcelableExtra("resendToken");
        getPhoneNumber= intent.getStringExtra("phoneNumber");

        editTexts = new EditText[]{smsArray1, smsArray2, smsArray3, smsArray4, smsArray5, smsArray6};

        for (int i = 0; i < editTexts.length; i++) {
            EditText currentEditText = editTexts[i];
            EditText nextEditText = null;
            if (i < editTexts.length - 1) {
                nextEditText = editTexts[i + 1];
            }
            currentEditText.addTextChangedListener(new CustomTextWatcher(currentEditText, nextEditText));
        }
        verifyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("hhhh");
                        StringBuilder concatenatedText = new StringBuilder();
                        boolean allFieldsFilled = true;

                        for (EditText sms : smsArray) {
                            String smsText = sms.getText().toString();
                            if (smsText.isEmpty()) {
                                allFieldsFilled = false;
                                break; // Exit the loop if any field is empty
                            } else {
                                concatenatedText.append(smsText);
                            }
                        }

                        if (allFieldsFilled) {
                            String concatenatedResult = concatenatedText.toString();
                            System.out.println(concatenatedResult); System.out.println(mVerificationId);
                            linkPhoneNumberToUser(mVerificationId, concatenatedResult);

                        } else {
                            verifyBtn.setError("Lütfen tüm alanları doldur");
                        }
                    }
                });



                smsResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                       "+90 "+ getPhoneNumber,        // Phone number to verify
                        60,                 // Timeout duration in seconds
                        TimeUnit.SECONDS,   // Timeout unit
                        SMSVerification.this,      // Activity (or context) for callback binding
                        callbacks, mresendToken );       // OnVerificationStateChangedCallbacks

            }
        });


    }

    class CustomTextWatcher implements TextWatcher {
        private EditText currentEditText;
        private EditText nextEditText;

        public CustomTextWatcher(EditText currentEditText, EditText nextEditText) {
            this.currentEditText = currentEditText;
            this.nextEditText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1 && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {
                    Toast.makeText(SMSVerification.this, "DOGRUUU.", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    // Verification failed, handle the error
                }

                @Override
                public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                    Toast.makeText(SMSVerification.this, "SMS mesajı tekrar gönderildi.", Toast.LENGTH_SHORT).show();
                }
            };
    UserLocationController.UserLocationCheck userLocationCheck=new UserLocationController.UserLocationCheck() {
        @Override
        public void onLocationFound() {
            Intent intent= new Intent(SMSVerification.this,Home.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onLocationNotFound() {
            Intent intent= new Intent(SMSVerification.this,UserLocation.class);
            startActivity(intent);
            finish();
        }
    };
    ProfileController.ProfileUpdateCallback profileUpdateCallback= new ProfileController.ProfileUpdateCallback() {
        @Override
        public void onProfileUpdateSuccess() {
            Toast.makeText(SMSVerification.this, "Telefonunuz başarıyla doğrulandı.", Toast.LENGTH_SHORT).show();
            UserLocationController userLocationController= new UserLocationController();
            userLocationController.checkUserLocation(currentUser.getUid(), userLocationCheck);



        }

        @Override
        public void onProfileUpdateFailure() {
            Toast.makeText(SMSVerification.this, "Telefonunuz doğrulanamadı.", Toast.LENGTH_SHORT).show();
        }
    };


    private void linkPhoneNumberToUser(String mVerificationId, String code ) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);


        if (currentUser != null) {
            currentUser.linkWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "linkWithCredential:success");
                                System.out.println("linkWithCredential:success");
                                ProfileController profileController= new ProfileController();
                                profileController.updateProfilePhoneNumber(getPhoneNumber, profileUpdateCallback);
                            } else {
                                Log.d(TAG, "linkWithCredential:failure", task.getException());
                                System.out.println("Error during linking credential: " + task.getException().getMessage());
                            }
                        }
                    });
        } else {
            Log.d(TAG, "No current user found");
            System.out.println("No current user found");
        }
    }
}