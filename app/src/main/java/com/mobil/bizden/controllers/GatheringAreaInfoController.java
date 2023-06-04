package com.mobil.bizden.controllers;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.GatheringAreaInfo;

import java.util.ArrayList;
import java.util.List;

public class GatheringAreaInfoController {
        private List<GatheringAreaInfo> gatheringAreasList;
    CollectionReference gatheringAreasRef;
    FirebaseFirestore firestore;
        public  GatheringAreaInfoController() {
            firestore = FirebaseFirestore.getInstance();
           gatheringAreasRef = firestore.collection("gatheringAreasInfo");
        }
        public void addGatheringArea(GatheringAreaInfo g) {
        GatheringAreaInfo gatheringArea = new GatheringAreaInfo(g.getAid(), g.getAddress(), g.getOccupancyRate(), g.getInformation(), g.getOrganization());
        gatheringAreasRef.document(g.getAid())
                .set(gatheringArea)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Gathering area added successfully.");
                        } else {
                            Exception exception = task.getException();
                            if (exception != null) {
                                String errorMessage = exception.getMessage();
                                System.out.println("Error adding gathering area: " + errorMessage);
                            }
                        }
                    }
                });
    }

    public List<GatheringAreaInfo> getGatheringAreasList() {
            return gatheringAreasList;
        }

        public GatheringAreaInfo getGatheringAreaByAid(String aid) {
            for (GatheringAreaInfo gatheringArea : gatheringAreasList) {
                if (gatheringArea.getAid().equals(aid)) {
                    return gatheringArea;
                }
            }
            return null; // return null if no match found
        }

        public void removeGatheringArea(String aid) {
            for (int i = 0; i < gatheringAreasList.size(); i++) {
                if (gatheringAreasList.get(i).getAid().equals(aid)) {
                    gatheringAreasList.remove(i);
                    return;
                }
            }
        }

        public void updateGatheringArea(String aid, String address, double occupancyRate, String information, String organization) {
            for (int i = 0; i < gatheringAreasList.size(); i++) {
                if (gatheringAreasList.get(i).getAid().equals(aid)) {
                    gatheringAreasList.set(i, new GatheringAreaInfo(aid, address, occupancyRate, information, organization));
                    return;
                }
            }
        }




}


