package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.mobil.bizden.R;

import java.util.Calendar;

public class UpdateProfile extends AppCompatActivity {

    private Button datePickerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Spinner spinnerComboBox = findViewById(R.id.spinnerComboBox);
        EditText editTextComboBox = findViewById(R.id.IdComboBox);
        ProgressBar progressBar= findViewById(R.id.progressbarId);
        // Create an ArrayAdapter with the options
        String[] optionsArray= new String[2];
        datePickerButton = findViewById(R.id.datePickerButton);
        optionsArray[0]="T.C";
        optionsArray[1]="Yabancı";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Set the adapter on the spinner
        spinnerComboBox.setAdapter(adapter);

// Set an item selected listener on the spinner
        spinnerComboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update the EditText with the selected option
                String selectedOption = parent.getItemAtPosition(position).toString();
                editTextComboBox.setText(selectedOption);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no option is selected
                editTextComboBox.setText("");
            }
        });

// Set an onClickListener on the EditText to open the spinner dropdown
        editTextComboBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerComboBox.performClick();
            }
        });



        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateProfile.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        // Here, you can update the selected date to your EditText or perform any other action
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();


    }
}