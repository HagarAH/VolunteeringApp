package com.mobil.bizden.ui;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.GatheringAreaController;
import com.mobil.bizden.controllers.GatheringAreaInfoController;
import com.mobil.bizden.controllers.RequestController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.GatheringAreaInfo;
import com.mobil.bizden.models.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Requests_frag extends Fragment {
    private Button aTimePickerButton;
    private Button dTimePickerButton;
    private Button confirmBtn;
    private EditText arrivalTime;
    private EditText departureTime;
    private TextView tvInfo;
    private TextView tvAddress;
    private TextView tvCapacityS;
    private TextView tvLocationS;
    private TextView tvOrgS;
    private TextView tvNameS;

    private GatheringArea gatheringAreaS;

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
        tvNameS= view.findViewById(R.id.tvNameS);
        tvAddress= view.findViewById(R.id.tvAddress);
        tvCapacityS=view.findViewById(R.id.tvCapacityS);
        tvInfo=view.findViewById(R.id.tvInfo);
        tvLocationS=view.findViewById(R.id.tvLocationS);
        tvOrgS=view.findViewById(R.id.tvOrgS);
        confirmBtn= view.findViewById(R.id.btnConfirm);
        dTimePickerButton= view.findViewById(R.id.dtimePickerButton);
        aTimePickerButton= view.findViewById(R.id.atimePickerButtonArrival);
        arrivalTime= view.findViewById(R.id.idTVSelectedTimeArrival);
        departureTime= view.findViewById(R.id.idTVSelectedTime);
        String aid= getArguments().getString("gatheringAreaAid");
        GatheringAreaController gatheringAreaController= new GatheringAreaController();
        GatheringAreaInfoController gatheringAreaInfoController = new GatheringAreaInfoController();
        UserController userController= new UserController();
        gatheringAreaController.getGatheringArea(aid, new GatheringAreaController.GatheringAreaCallback() {
            @Override
            public void onGatheringAreaAdded() {

            }

            @Override
            public void onGatheringAreaUpdated() {

            }

            @Override
            public void onGatheringAreaDeleted() {

            }

            @Override
            public void onGatheringAreaLoadFailed(String error) {

            }

            @Override
            public void onGatheringAreaLoaded(GatheringArea gatheringArea) {
                gatheringAreaS=gatheringArea;
                tvNameS.setText(gatheringArea.getName());
                tvLocationS.setText(gatheringArea.getDistrict()+",  "+gatheringArea.getProvince());
               gatheringAreaInfoController.getGatheringAreaByAid(aid, new GatheringAreaInfoController.GetCallback() {
                    @Override
                    public void getSuccessful(GatheringAreaInfo gatheringAreaInfo) {
                        tvOrgS.setText(gatheringAreaInfo.getOrganization());
                        tvInfo.setText(gatheringAreaInfo.getInformation());
                        tvAddress.setText(gatheringAreaInfo.getAddress());
                        tvCapacityS.setText(String.valueOf(gatheringAreaInfo.getOccupancyRate())+"/ "+String.valueOf(gatheringArea.getCapacity()));
                    }

                    @Override
                    public void getFailed(String err) {

                    }
                });





            }

            @Override
            public void onGatheringAreaByLocationLoaded(List<GatheringArea> gatheringAreas) {

            }

            @Override
            public void onCollectionEmpty() {

            }
        });



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
                            aTimePickerButton.setError("Varış zamanı çıkış zamanını aşmamalı");

                        } else if (depTime.isBefore(arrTime)) {
                            dTimePickerButton.setError("Çıkış zamanı varış zamanını aşmalı");

                        }
                        else{

                            RequestController requestController= new RequestController();
                            Random random = new Random();
                            int randomNumber = random.nextInt();
                            String did= String.valueOf(randomNumber);
                            Request request= new Request(aid, did,userController.getCurrentUser().getUid(), Timestamp.now(),false,false,arrv,dep);
                            RequestController.RequestCallback requestCallback= new RequestController.RequestCallback() {
                                @Override
                                public void onCallback(Request request) {
                                    // addNotification
                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                    builder.setTitle("Yeni katılma isteği"); // Set the title of the dialog
                                    builder.setMessage("Oluşturduğunuz katılma istegi Başvurularım sekmesinde görüntüleyebilirsiniz.");

                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();

                                    alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
//add replace fragment to requests view
                                            RequestsView_frag requests_frag= new RequestsView_frag();
                                            FragmentTransaction manager= getParentFragmentManager().beginTransaction();
                                            manager.replace(R.id.flFragment, requests_frag);
                                            manager.addToBackStack(null);
                                            manager.commit();
                                        }
                                    });

                                }

                                @Override
                                public void onError(Exception e) {

                                }

                                @Override
                                public void onRequestsLoaded(List<Request> requests) {

                                }

                                @Override
                                public void onRequestDeleted(String requestId) {

                                }
                            };
                            requestController.addRequest(request,requestCallback);


                        }
                    }
                    else {
                        dTimePickerButton.setError("Çıkış zamanı belirleyin");
                    }


                }else {
                    aTimePickerButton.setError("Varış zamanı belirleyin");
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
                        if (hourOfDay[0] < 8 || (hourOfDay[0] == 8 && minute[0] < 00) || hourOfDay[0] > 22 || (hourOfDay[0] == 22 && minute[0] > 00) ) {
                            Toast.makeText(getView().getContext(), "Lütfen bu aralık içinde bir zaman seçin: 8:00-22:00 ", Toast.LENGTH_LONG).show();
                        } else {

                            String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay[0], minute[0]);

                        handler.onTimeSet(selectedTime);}
                    }
                },
                hourOfDay[0],
                minute[0],
                true
        );

        timePickerDialog.show();
    }


}