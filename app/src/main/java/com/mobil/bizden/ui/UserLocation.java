package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.mobil.bizden.API.LocationCCD;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.controllers.UserLocationController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserLocation extends AppCompatActivity {
    private LocationCCD locationCCD;
    private String selectedProvince;
    private String selectedDistrict;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> districtAdapter;
    private Set<String> loadedProvinces;
    private Set<String> loadedDistricts;
    private Button saveBtn;
    private EditText adressLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);
        AutoCompleteTextView autoCompleteDistrict = findViewById(R.id.autocomplete_districtAuto);
        AutoCompleteTextView autoCompleteProvince = findViewById(R.id.autocomplete_provinceAuto);
        saveBtn= findViewById(R.id.saveLocationBtn);
        adressLine= findViewById(R.id.editTextAddress);

        LocationCCD.LocationListener locationListener = new LocationCCD.LocationListener() {
            @Override
            public void onProvincesLoaded(List<String> provinces) {
                loadedProvinces.addAll(provinces);
                provinceAdapter.addAll(provinces);
                provinceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDistrictsLoaded(List<String> districts) {
                loadedDistricts.addAll(districts);
                districtAdapter.clear();
                districtAdapter.addAll(districts);
                districtAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLocationLoadFailed(String error) {
                System.out.println(error);
            }
        };

        locationCCD = new LocationCCD();
        loadedProvinces = new HashSet<>();
        loadedDistricts = new HashSet<>();
        provinceAdapter = new ArrayAdapter<>(UserLocation.this, android.R.layout.simple_dropdown_item_1line);
        districtAdapter = new ArrayAdapter<>(UserLocation.this, android.R.layout.simple_dropdown_item_1line);
        autoCompleteProvince.setAdapter(provinceAdapter);
        autoCompleteDistrict.setAdapter(districtAdapter);

        locationCCD.loadProvinces(locationListener);

        autoCompleteProvince.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                selectedProvince = autoCompleteProvince.getText().toString().trim();
                if (TextUtils.isEmpty(selectedProvince)) {
                    autoCompleteProvince.setError(getString(R.string.error_empty_province));
                } else if (!loadedProvinces.contains(selectedProvince)) {
                    autoCompleteProvince.setError(getString(R.string.error_invalid_province));
                } else {
                    locationCCD.loadDistricts(selectedProvince, locationListener);
                }
            }
        });

        autoCompleteProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedProvince = autoCompleteProvince.getText().toString().trim();
                if (TextUtils.isEmpty(selectedProvince)) {
                    autoCompleteProvince.setError(getString(R.string.error_empty_province));
                } else if (!loadedProvinces.contains(selectedProvince)) {
                    autoCompleteProvince.setError(getString(R.string.error_invalid_province));
                } else {
                    locationCCD.loadDistricts(selectedProvince, locationListener);
                }
            }
        });

        UserLocationController.UserLocationCallback callback=new UserLocationController.UserLocationCallback() {
            @Override
            public void onCallback(com.mobil.bizden.models.UserLocation userLocation) {

            }

            @Override
            public void onError(Exception e) {

            }
        };

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedProvince.isEmpty()){
                    selectedDistrict= autoCompleteDistrict.getText().toString();
                    if (!selectedDistrict.isEmpty() && !adressLine.getText().toString().isEmpty()){

                        UserLocationController userLocationController= new UserLocationController();
                        UserController userController= new UserController();

                        com.mobil.bizden.models.UserLocation location= new com.mobil.bizden.models.UserLocation(userController.getCurrentUser().getUid(),selectedDistrict,selectedDistrict, adressLine.getText().toString());
                        userLocationController.addUserLocation(location, callback);
                    }
                    else {
                        System.out.println("EKSIK BILGI");
                    }
                }
            }
        });
    }
}
