package com.mobil.bizden.ui;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.mobil.bizden.R;
import com.mobil.bizden.models.GatheringArea;

import java.time.LocalTime;
import java.util.Locale;

public class Requests_frag extends Fragment {
    private Button aTimePickerButton;
    private Button dTimePickerButton;
    private Button confirmBtn;
    private EditText arrivalTime;
    private EditText departureTime;

   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_requests, container, false);
       
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dTimePickerButton= view.findViewById(R.id.dtimePickerButton);
        aTimePickerButton= view.findViewById(R.id.atimePickerButtonArrival);
        arrivalTime= view.findViewById(R.id.idTVSelectedTimeArrival);
        departureTime= view.findViewById(R.id.idTVSelectedTime);
        aTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker(new TimePickerHandler() {
                    @Override
                    public void onTimeSet(String selectedTime) {
                        arrivalTime.setText(selectedTime);
                    }
                });
            }
        });
        dTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker(new TimePickerHandler() {
                    @Override
                    public void onTimeSet(String selectedTime) {
                        departureTime.setText(selectedTime);
                    }
                });
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dep= departureTime.getText().toString();
                String arrv=arrivalTime.getText().toString();
                if( !arrv.isEmpty()){
                    if (!dep.isEmpty()){
                        LocalTime depTime = LocalTime.parse(dep);
                        LocalTime arrTime = LocalTime.parse(arrv);

                        if(arrTime.isAfter(depTime)){
                            arrivalTime.setError("Varış zamanı çıkış zamanını aşmamalı");

                        } else if (depTime.isBefore(arrTime)) {
                            departureTime.setError("Çıkış zamanı varış zamanını aşmalı");

                        }
                        else{



                        }
                    }
                    else {
                        departureTime.setError("Çıkış zamanı belirleyin");
                    }


                }else {
                    arrivalTime.setError("Varış zamanı belirleyin");
                }



            }
        });
    }

    public interface TimePickerHandler {
        void onTimeSet(String selectedTime);
    }

    public void TimePicker(TimePickerHandler handler) {
        final int[] hourOfDay = {0}, minute = {0};

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this.getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hourOfDay[0] = selectedHour;
                        minute[0] = selectedMinute;

                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay[0], minute[0]);

                        handler.onTimeSet(selectedTime);
                    }
                },
                hourOfDay[0],
                minute[0],
                true
        );

        timePickerDialog.show();
    }


}