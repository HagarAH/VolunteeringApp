package com.mobil.bizden.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.service.autofill.UserData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.models.Profile;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends Fragment {


    public UpdateProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_prfl, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser!=null) {
            String currentUserId = currentUser.getUid();
            DocumentReference profilesDocRef = db.collection("profiles").document(currentUserId);
            profilesDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String firstName = document.getString("firstName");
                            String lastName = document.getString("lastName");

                            String fullName = firstName + " " + lastName;

                            TextView textView = getView().findViewById(R.id.textView);
                            textView.setText(fullName);
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }


            });
            String uid = currentUser.getUid();
            DocumentReference userLocationDocRef = db.collection("userLocations").document(uid);
            userLocationDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String location = document.getString("address");
                            EditText edittextLocation = getView().findViewById(R.id.textViewPostcode);
                            edittextLocation.setText(location);
                            String district = document.getString("district");
                            EditText textViewcity = getView().findViewById(R.id.textViewcity);
                            textViewcity.setText(district);
                            String province = document.getString("province");
                            EditText EdittextProvince = getView().findViewById(R.id.EdittextProvince);
                            EdittextProvince.setText(province);

                        }
                    }
                }
            });
            String uidd = currentUser.getUid();
            DocumentReference email = db.collection("users").document(uid);
            email.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String email = document.getString("email");
                            EditText editextViewEposta = view.findViewById(R.id.editextViewEposta);
                            editextViewEposta.setText(email);
                        }
                    }
                }
            });


            Button updateButton = view.findViewById(R.id.updateButton);
            updateButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // get the root view

                    EditText editextViewEpostaa = getView().findViewById(R.id.editextViewEposta);
                    String email = editextViewEpostaa.getText().toString();

                    EditText edittextLocationn = getView().findViewById(R.id.textViewPostcode);
                    String adres = edittextLocationn.getText().toString();

                    EditText textViewcityy = getView().findViewById(R.id.textViewcity);
                    String adres2 = textViewcityy.getText().toString();

                    EditText EdittextProvincee = getView().findViewById(R.id.EdittextProvince);
                    String adres3 = EdittextProvincee.getText().toString();

                    // ...


                    Map<String, Object> userData = new HashMap<>();
                    userData.put("adres", adres);
                    userData.put("email", email);
                    userData.put("adres2", adres2);
                    userData.put("adres3", adres3);

                    db.collection("users").document(currentUserId)
                            .update(userData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
                                    profilesDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    String email = document.getString("email");
                                                    EditText editextViewEposta = getView().findViewById(R.id.editextViewEposta);

                                                } else {
                                                    Log.d(TAG, "No such document");
                                                }
                                            } else {
                                                Log.d(TAG, "get failed with ", task.getException());
                                            }
                                        }
                                    });

                                    db.collection("userLocations").document(currentUserId)
                                                    .update(userData)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                                    userLocationDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                            if (task.isSuccessful()) {
                                                                                DocumentSnapshot document = task.getResult();
                                                                                if (document.exists()) {
                                                                                    String adres = document.getString("address");
                                                                                    EditText edittextLocationn = getView().findViewById(R.id.textViewPostcode);
                                                                                    edittextLocationn.setText(adres);

                                                                                    String adres1 = document.getString("district");
                                                                                    EditText textViewcityy = getView().findViewById(R.id.textViewcity);
                                                                                    textViewcityy.setText(adres1);

                                                                                    String adres2 = document.getString("province");
                                                                                    EditText EdittextProvincee = getView().findViewById(R.id.EdittextProvince);
                                                                                    EdittextProvincee.setText(adres2);
                                                                                }
                                                                            }
                                                                        }
                                                                    });
                                                                }
                                                            });

                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error updating document", e);

                                }
                            });
                }
            });
        }}}