package com.mobil.bizden.controllers;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.Request;
import androidx.annotation.NonNull;

public class RequestController {

    public interface RequestCallback {
        void onCallback(Request request);
        void onError(Exception e);
    }

    private FirebaseFirestore db;

    public RequestController() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void addRequest(Request request, final RequestCallback callback) {
        db.collection("requests").document(request.getUid())
                .set(request)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onCallback(request);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    public void getRequest(String uid, final RequestCallback callback) {
        db.collection("requests").document(uid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Request request = task.getResult().toObject(Request.class);
                            callback.onCallback(request);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }
}
