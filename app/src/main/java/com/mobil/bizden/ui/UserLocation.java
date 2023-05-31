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

import com.mobil.bizden.API.LocationCCD;
import com.mobil.bizden.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserLocation extends AppCompatActivity {
    private LocationCCD locationCCD;
    private String selectedProvince;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> districtAdapter;
    private Set<String> loadedProvinces;
    private Set<String> loadedDistricts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);
        AutoCompleteTextView autoCompleteDistrict = findViewById(R.id.autocomplete_districtAuto);
        AutoCompleteTextView autoCompleteProvince = findViewById(R.id.autocomplete_provinceAuto);

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
    }
}
