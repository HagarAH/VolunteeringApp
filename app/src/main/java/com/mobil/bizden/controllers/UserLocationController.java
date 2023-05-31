package com.mobil.bizden.controllers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.UserLocation;
import androidx.annotation.NonNull;

public class UserLocationController {

    public interface UserLocationCallback {
        void onCallback(UserLocation userLocation);
        void onError(Exception e);
    }

    private FirebaseFirestore db;

    public UserLocationController() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void addUserLocation(UserLocation userLocation, final UserLocationCallback callback) {
        db.collection("userLocations").document(userLocation.getUid())
                .set(userLocation)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onCallback(userLocation);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    public void getUserLocation(String uid, final UserLocationCallback callback) {
        db.collection("userLocations").document(uid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            UserLocation userLocation = task.getResult().toObject(UserLocation.class);
                            callback.onCallback(userLocation);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }
}
