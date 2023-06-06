package com.mobil.bizden.controllers;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.mobil.bizden.models.EntryCode;

import java.util.ArrayList;
import java.util.List;


    public class EntryCodeController {
        private FirebaseFirestore db;
        private CollectionReference entryCodesRef;

        public EntryCodeController() {
            db = FirebaseFirestore.getInstance();
            entryCodesRef = db.collection("entryCodes");
        }
        public interface EntryCodeCallback {
            void onEntryCodeAdded(EntryCode entryCode);
            void onEntryCodeAddError(Exception e);
            void onEntryCodesLoaded(List<EntryCode> entryCodes);
            void onEntryCodesLoadError(Exception e);
            void onEntryCodeDeleted(String entryCodeId);
            void onEntryCodeDeleteError(Exception e);
        }

        public void addEntryCode(EntryCode entryCode, final EntryCodeCallback callback) {
            entryCodesRef.document(entryCode.getDid())
                    .set(entryCode, SetOptions.merge())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                callback.onEntryCodeAdded(entryCode);
                            } else {
                                callback.onEntryCodeAddError(task.getException());
                            }
                        }
                    });
        }

        public void getEntryCodesForUser(String userId, final EntryCodeCallback callback) {
            entryCodesRef.whereEqualTo("uid", userId).orderBy("createdDate", Query.Direction.DESCENDING)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<EntryCode> entryCodes = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    EntryCode entryCode = document.toObject(EntryCode.class);
                                    entryCodes.add(entryCode);
                                }
                                callback.onEntryCodesLoaded(entryCodes);
                            } else {
                                callback.onEntryCodesLoadError(task.getException());
                            }
                        }
                    });
        }

        public void deleteEntryCode(String entryCodeId, final EntryCodeCallback callback) {
            entryCodesRef.document(entryCodeId)
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                callback.onEntryCodeDeleted(entryCodeId);
                            } else {
                                callback.onEntryCodeDeleteError(task.getException());
                            }
                        }
                    });
        }


    }