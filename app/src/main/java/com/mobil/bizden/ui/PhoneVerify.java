package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobil.bizden.R;

public class PhoneVerify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        Button verifyBtn= findViewById(R.id.buttonNextOtp);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneVerify.this, UserLocation.class);
                startActivity(intent);
                finish();
            }
        });
    }
}