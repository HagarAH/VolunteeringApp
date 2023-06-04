package com.mobil.bizden.ui;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.R;
import com.mobil.bizden.databinding.FragmentResetPassBinding;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.w3c.dom.Document;


public class UserProfile_frag extends Fragment {


    private Button button;

    private FragmentResetPassBinding binding;
    public UserProfile_frag(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile_frag, container, false);
    }

    private void openUpdateProfileFragment() {
        updatePrfl updateProfileFragment = new updatePrfl();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, updateProfileFragment)
                .addToBackStack(null)  // allows user to navigate back
                .commit();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid(); // Kullanıcının uid'si

            DocumentReference docRef = db.collection("profiles").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()){
                            String tcId = document.getString("tcId");
                            EditText edittextTC = view.findViewById(R.id.edittextTC);
                            edittextTC.setText(tcId);

                            String telephone = document.getString("telephone");
                            EditText edittextTelephone = view.findViewById(R.id.edittextTelephone);
                            edittextTelephone.setText(telephone);

                            String date = document.getString("birthDate");
                            EditText editTextDate = view.findViewById(R.id.editTextDate);
                            editTextDate.setText(date);

                            String firstName = document.getString("firstName");
                            String lastName = document.getString("lastName");

                            String fullName = firstName + " " + lastName;

                            TextView textView = view.findViewById(R.id.textView);
                            textView.setText(fullName);

                        } else {
                            Log.d(TAG, " no such document  ");
                        }
                    }else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        } else {
            // Kullanıcı giriş yapmamışsa ilgili işlemler
        }
        String uid = user.getUid();
        DocumentReference userLocationDocRef = db.collection("userLocations").document(uid);
        userLocationDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        String location = document.getString("province");  // Eğer alan adı "location" ise
                        EditText edittextLocation = view.findViewById(R.id.textViewPostcode);
                        edittextLocation.setText(location);

                        String mahalle = document.getString("district");
                        String adres = document.getString("address");
                        String fulladress = mahalle + ", " + adres;
                        EditText adrestext = view.findViewById(R.id.textViewcity);
                        adrestext.setText(fulladress);
                    } else {
                        Log.d(TAG, " no such document ");
                    }
                }else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });





    button = view.findViewById(R.id.updateButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateProfileFragment();
            }
        });
    }

}