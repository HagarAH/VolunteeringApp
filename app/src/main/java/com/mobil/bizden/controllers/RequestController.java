package com.mobil.bizden.controllers;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.Request;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RequestController {

    public interface RequestCallback {
        void onCallback(Request request);
        void onError(Exception e);

        void onRequestsLoaded(List<Request> requests);

        void onRequestDeleted(String requestId);
    }

    private FirebaseFirestore db;

    public RequestController() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void addRequest(Request request, final RequestCallback callback) {
        db.collection("requests").add(request).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    DocumentReference document = task.getResult();
                    String requestId = document.getId();
                    request.setUid(requestId); // Set the generated document ID as the request's UID
                    callback.onCallback(request);
                } else {
                    callback.onError(task.getException());
                }
            }
        });
    }

    public void getRequest(String uid, final RequestCallback callback) {
        db.collection("requests")
                .whereEqualTo("uid", uid)
                .orderBy("creationDate", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Request> requests = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Request request = document.toObject(Request.class);
                                requests.add(request);
                            }
                            // return the list of matching gathering areas
                            callback.onRequestsLoaded(requests);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }   public void getRequestByDid(String did, final RequestCallback callback) {
        db.document(did)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Request request = document.toObject(Request.class);
                            // return the list of matching gathering areas
                            callback.onCallback(request);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    public void deleteRequest(String requestId, final RequestCallback callback) {
        db.collection("requests").whereEqualTo("did", requestId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot queryDocumentSnapshots = task.getResult();
                            if (!queryDocumentSnapshots.isEmpty()) {
                                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot document : documents) {
                                    document.getReference().delete();
                                }
                                callback.onRequestDeleted(requestId);
                            } else {
                                callback.onError(new Exception("No document found with the id: " + requestId));
                            }
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }



}
