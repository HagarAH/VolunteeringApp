package com.mobil.bizden.controllers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.UserLocation;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GatheringAreaController {
    private FirebaseFirestore firestore;
    private CollectionReference gatheringAreasRef;

    public interface GatheringAreaCallback {
        void onGatheringAreaAdded();
        void onGatheringAreaUpdated();
        void onGatheringAreaDeleted();
        void onGatheringAreaLoadFailed(String error);
        void onGatheringAreaLoaded(GatheringArea gatheringArea);

        void onGatheringAreaByLocationLoaded(List<GatheringArea> gatheringAreas);

        void onCollectionEmpty();
    }

    public GatheringAreaController() {
        firestore = FirebaseFirestore.getInstance();
        gatheringAreasRef = firestore.collection("gatheringAreas");
    }

    public void addGatheringArea(GatheringArea gatheringArea, final GatheringAreaCallback callback) {
        gatheringAreasRef.document(gatheringArea.getAid())
                .set(gatheringArea)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onGatheringAreaAdded();
                        } else {
                            callback.onGatheringAreaLoadFailed(task.getException().getMessage());
                        }
                    }
                });
    }

    public void updateGatheringArea(GatheringArea gatheringArea, final GatheringAreaCallback callback) {
        gatheringAreasRef.document(gatheringArea.getAid())
                .set(gatheringArea)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onGatheringAreaUpdated();
                        } else {
                            callback.onGatheringAreaLoadFailed(task.getException().getMessage());
                        }
                    }
                });
    }

    public void deleteGatheringArea(String aid, final GatheringAreaCallback callback) {
        gatheringAreasRef.document(aid)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onGatheringAreaDeleted();
                        } else {
                            callback.onGatheringAreaLoadFailed(task.getException().getMessage());
                        }
                    }
                });
    }

    public void getGatheringArea(String aid, final GatheringAreaCallback callback) {
        gatheringAreasRef.document(aid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                GatheringArea gatheringArea = document.toObject(GatheringArea.class);
                                callback.onGatheringAreaLoaded(gatheringArea);
                            } else {
                                callback.onGatheringAreaLoadFailed("Gathering area not found");
                            }
                        } else {
                            callback.onGatheringAreaLoadFailed(task.getException().getMessage());
                        }
                    }
                });
    }
    public void getGatheringAreaByLocation(UserLocation userLocation, final GatheringAreaCallback callback) {
        gatheringAreasRef
                .whereEqualTo("province", userLocation.getProvince())
                .whereEqualTo("district", userLocation.getDistrict())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<GatheringArea> gatheringAreas = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                GatheringArea gatheringArea = document.toObject(GatheringArea.class);
                                gatheringAreas.add(gatheringArea);
                            }
                            // return the list of matching gathering areas
                            callback.onGatheringAreaByLocationLoaded(gatheringAreas);
                        } else {
                            callback.onGatheringAreaLoadFailed(task.getException().getMessage());
                        }
                    }
                });
    }
    public void isCollectionEmpty(final GatheringAreaCallback callback) {
        gatheringAreasRef.limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty()) {
                        callback.onCollectionEmpty();
                    }
                } else {
                    callback.onGatheringAreaLoadFailed(task.getException().getMessage());
                }
            }
        });
    }

}
