package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobil.bizden.API.ForeignIdVerification;
import com.mobil.bizden.API.IdVerification;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateProfile extends AppCompatActivity {

    private Button datePickerButton;
    private String year_;
    private String day_;
    private String month_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
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
        String[] optionsArray= new String[2];
        datePickerButton = findViewById(R.id.datePickerButton);
        optionsArray[0]="T.C";
        optionsArray[1]="Yabancı";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        progressBar.setVisibility(View.GONE);
// Set the adapter on the spinner
        spinnerComboBox.setAdapter(adapter);

// Set an item selected listener on the spinner
        spinnerComboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update the EditText with the selected option
                String selectedOption = parent.getItemAtPosition(position).toString();
                idBtn.setText(selectedOption);
                editTextID.setText("11 haneli kimlik numaranızı giriniz");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no option is selected
            }
        });

        editTextDateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String inputDate = editTextDateOfBirth.getText().toString();
                    if (!inputDate.matches(datePattern)) {
                        editTextDateOfBirth.setError("Hatalı tarih formatı lütfen bu formatı izleyin: gg/aa/yyyy");
                    }
                }
            }
        });
// Set an onClickListener on the EditText to open the spinner dropdown
        idBtn.setOnClickListener(new View.OnClickListener() {
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
        ProfileController profileController= new ProfileController();


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
                        if(idBtn.getText().toString()==optionsArray[0]){
                            //TC
                            IdVerification idVerification= new IdVerification(fName,lName,year_,ID);

                            IdVerification.VerificationListener verificationListener = new IdVerification.VerificationListener() {
                                @Override
                                public void onVerificationCompleted(String verificationResult) {
                                    System.out.println(verificationResult);

                                    progressBar.setVisibility(View.GONE);
                                    if(verificationResult=="true"){
                                        ProfileController.ProfileUpdateCallback profileUpdateCallback= new ProfileController.ProfileUpdateCallback() {
                                            @Override
                                            public void onProfileUpdateSuccess() {
                                                Toast.makeText(UpdateProfile.this, "Kimliğinizi doğrulandı ve kaydedildi",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(UpdateProfile.this, Home.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void onProfileUpdateFailure() {
                                                Toast.makeText(UpdateProfile.this, "Kimliğinizi doğrulandı ama kaydedilemedi",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        };
                                        profileController.updateProfile(fName,lName,ID,editTextDateOfBirth.getText().toString(),profileUpdateCallback );

                                    }
                                    else{
                                        Toast.makeText(UpdateProfile.this, "Kimliğinizi doğrulanamadı, lütfen bilgilerinizi kontrol edin",
                                                Toast.LENGTH_SHORT).show();
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
                                    // Handle the successful verification result
                                    System.out.println(verificationResult);
                                    progressBar.setVisibility(View.GONE);
                                    if(verificationResult=="true"){
                                        if(verificationResult=="true"){
                                            ProfileController.ProfileUpdateCallback profileUpdateCallback= new ProfileController.ProfileUpdateCallback() {
                                                @Override
                                                public void onProfileUpdateSuccess() {
                                                    Toast.makeText(UpdateProfile.this, "Kimliğinizi doğrulandı ve kaydedildi",
                                                            Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(UpdateProfile.this, Home.class);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                                @Override
                                                public void onProfileUpdateFailure() {
                                                    Toast.makeText(UpdateProfile.this, "Kimliğinizi doğrulandı ama kaydedilemedi",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            };
                                            profileController.updateProfile(fName,lName,ID,editTextDateOfBirth.getText().toString(),profileUpdateCallback );

                                        }
                                        else{
                                            Toast.makeText(UpdateProfile.this, "Kimliğinizi doğrulanamadı, lütfen bilgilerinizi kontrol edin",
                                                    Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                    else{

                                    }
                                }

                                @Override
                                public void onVerificationFailed() {
                                    // Handle the failed verification
                                    System.out.println("Not verified");
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
    public static boolean verifyTelephoneNumber(String phoneNumber) {
        // Regular expression pattern for a valid telephone number in the pattern "+90 233 345 56 56"
        String pattern = "^\\+90\\s\\d{3}\\s\\d{3}\\s\\d{2}\\s\\d{2}$";

        // Create a Pattern object with the given pattern
        Pattern regexPattern = Pattern.compile(pattern);

        // Create a Matcher object for the given input
        Matcher matcher = regexPattern.matcher(phoneNumber);

        // Return true if the input matches the pattern, false otherwise
        return matcher.matches();
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateProfile.this,
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