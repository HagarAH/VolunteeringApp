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
import android.widget.TextView;
import android.widget.Toast;

import com.mobil.bizden.API.ForeignIdVerification;
import com.mobil.bizden.API.IdVerification;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Identification extends AppCompatActivity {

    private Button datePickerButton;
    private String year_;
    private String day_;
    private String month_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        Spinner spinnerComboBox = findViewById(R.id.spinnerComboBox);
        Button idBtn = findViewById(R.id.IdComboBox);
        ProgressBar progressBar= findViewById(R.id.progressbarId);
        EditText editTextfName= findViewById(R.id.editTextFirstName);
        EditText editTextlName= findViewById(R.id.editTextLastName);
        EditText editTextID= findViewById(R.id.editTextID);
        EditText editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
//        EditText phoneNumber= findViewById(R.id.editTextTelephoneNumber);
        Button verifyBtn= findViewById(R.id.verifyBtn);
        String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
        // Create an ArrayAdapter with the options
        String[] optionsArray= new String[3];
        datePickerButton = findViewById(R.id.datePickerButton);
        optionsArray[0]=" ";
        optionsArray[1]="Yabancı";
        optionsArray[2]="T.C";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, optionsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        progressBar.setVisibility(View.GONE);
        idBtn.setText("▼");
// Set the adapter on the spinner


// Set an item selected listener on the spinner
        spinnerComboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update the EditText with the selected option

                String selectedOption = parent.getItemAtPosition(position).toString();
                idBtn.setText(selectedOption);
                ((TextView)view).setText(null);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                idBtn.setText("▼");
            }
        });
        spinnerComboBox.setAdapter(adapter);
// Set an onClickListener on the EditText to open the spinner dropdown
        idBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idBtn.setText("▼");
                spinnerComboBox.setVisibility(View.VISIBLE);
                spinnerComboBox.performClick();


            }
        });




        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        ProfileController profileController= new ProfileController();
        ProfileController.ProfileUpdateCallback profileUpdateCallback= new ProfileController.ProfileUpdateCallback() {
            @Override
            public void onProfileUpdateSuccess() {
                Toast.makeText(Identification.this, "Kimliğinizi doğrulandı ve kaydedildi",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onProfileUpdateFailure() {
                Toast.makeText(Identification.this, "Kimliğinizi doğrulandı ama kaydedilemedi",
                        Toast.LENGTH_SHORT).show();
            }
        };

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextlName.getText()!=null && editTextlName.getText()!=null && editTextID.getText()!=null && editTextDateOfBirth.getText()!=null){

                   final String fName= String.valueOf(editTextfName.getText());
                   final String lName= String.valueOf(editTextlName.getText());
                   final String ID= String.valueOf(editTextID.getText());
                   progressBar.setVisibility(View.VISIBLE);

                    if(editTextID.length()<11){
                        editTextID.setError("Kimliğinizi kontrol edin");
                        progressBar.setVisibility(View.GONE);


                    } else if (idBtn.getText().toString()=="▼") {
                        editTextID.setError("Kimlik tipi sağ yandan seçiniz ");
                        progressBar.setVisibility(View.GONE);



                    } else{
                        if(idBtn.getText().toString()==optionsArray[2]){
                            //TC
                            IdVerification idVerification= new IdVerification(fName,lName,year_,ID);

                            IdVerification.VerificationListener verificationListener = new IdVerification.VerificationListener() {
                                @Override
                                public void onVerificationCompleted(String verificationResult) {
                                    System.out.println(verificationResult);

                                    progressBar.setVisibility(View.GONE);
                                    if(verificationResult.toString().equalsIgnoreCase("true")){

                                        profileController.updateProfile(fName,lName,ID,editTextDateOfBirth.getText().toString(),profileUpdateCallback );

                                    }
                                    else if(verificationResult.equalsIgnoreCase("false")){
                                        Toast.makeText(Identification.this, "Kimliğinizi doğrulanamadı, lütfen bilgilerinizi kontrol edin",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        System.out.println(verificationResult);
                                    }
                                }

                                @Override
                                public void onVerificationFailed() {
                                    // Handle the failed verification

                                    progressBar.setVisibility(View.GONE);
                                }
                            };
                            idVerification.performVerification(verificationListener);
                        }
                        else if(idBtn.getText().toString()==optionsArray[1]){
                            // Yabanci
                            ForeignIdVerification foreignIdVerification=new ForeignIdVerification(fName,lName,year_,month_,day_,ID);
                            ForeignIdVerification.VerificationListener verificationListener = new ForeignIdVerification.VerificationListener() {
                                @Override
                                public void onVerificationCompleted(String verificationResult) {
                                    System.out.println(verificationResult);

                                    progressBar.setVisibility(View.GONE);
                                    if(verificationResult.toString().equalsIgnoreCase("true")){

                                        profileController.updateProfile(fName,lName,ID,editTextDateOfBirth.getText().toString(),profileUpdateCallback );

                                    }
                                    else if(verificationResult.equalsIgnoreCase("false")){
                                        Toast.makeText(Identification.this, "Kimliğinizi doğrulanamadı, lütfen bilgilerinizi kontrol edin",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        System.out.println(verificationResult);
                                    }
                                }

                                @Override
                                public void onVerificationFailed() {
                                    // Handle the failed verification

                                    progressBar.setVisibility(View.GONE);
                                }
                            };
                            foreignIdVerification.performVerification(verificationListener);

                        }
                        else{
                            editTextID.setError("Kimlik tipini seçiniz");
                            progressBar.setVisibility(View.GONE);

                        }


                    }

                } else {

                }
            }
        });




    }

    Calendar calendar;

    private void showDatePickerDialog() {
        // Get current date
         calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        EditText datePickerEdit = findViewById(R.id.editTextDateOfBirth);
        // Create a new instance of DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(Identification.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        datePickerEdit.setText(formattedDate
                        );
                        year_= String.valueOf(year);
                        day_=String.valueOf(dayOfMonth);
                        month_=String.valueOf(month+1);

                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();


    }

}