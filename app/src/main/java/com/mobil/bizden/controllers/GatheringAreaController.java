package com.mobil.bizden.controllers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.GatheringArea;

public class GatheringAreaController {
    private FirebaseFirestore firestore;
    private CollectionReference gatheringAreasRef;

    public interface GatheringAreaCallback {
        void onGatheringAreaAdded();
        void onGatheringAreaUpdated();
        void onGatheringAreaDeleted();
        void onGatheringAreaLoadFailed(String error);
        void onGatheringAreaLoaded(GatheringArea gatheringArea);
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
}
