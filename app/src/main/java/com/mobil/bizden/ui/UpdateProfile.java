package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobil.bizden.R;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
    }

    public static class kullaniciprofil extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_kullaniciprofil);
        }
    }
}