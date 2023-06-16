package com.mobil.bizden.controllers;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.Profile;

import java.util.concurrent.TimeUnit;

public class ProfileController {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseUser mCurrentUser;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    FirebaseFirestore db;
    public ProfileController() {
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
         db = FirebaseFirestore.getInstance();

    }

    public void getProfile( ProfileCheckCallback callback) {

      db.collection("profiles").document(mCurrentUser.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                  @Override
                  public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                      if (task.isSuccessful()){
                          Profile profile;
                          profile= task.getResult().toObject(Profile.class);
                          callback.onGetProfile(profile);
                      }
                  }
              });

    }

    public void updateProfile(String fname, String lname, String idno, String dateOfBirth, ProfileUpdateCallback callback) {
        if (mCurrentUser != null) {
            // Update user profile in Firebase Authentication
            mCurrentUser.updateProfile(new UserProfileChangeRequest.Builder()
                            .setDisplayName(fname)
                            .build())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Profile updated successfully in Firebase Authentication
                            // Now update the user's document in Firestore
                            DocumentReference userRef = mFirestore.collection("profiles")
                                    .document(mCurrentUser.getUid());

                            // Create a Profile object
                            Profile profile = new Profile(mCurrentUser.getUid(), fname, lname, "", idno, dateOfBirth);

                            // Save the profile document in Firestore
                            userRef.set(profile)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            // Profile document created successfully in Firestore
                                            // Invoke the success callback
                                            callback.onProfileUpdateSuccess();
                                        } else {
                                            // Failed to create profile document in Firestore
                                            // Invoke the error callback
                                            callback.onProfileUpdateFailure();
                                        }
                                    });
                        } else {
                            // Failed to update user profile in Firebase Authentication
                            // Invoke the error callback
                            callback.onProfileUpdateFailure();
                        }
                    });
        }
    }



    public void updateProfilePhoneNumber(String phoneNum, ProfileUpdateCallback callback) {
        if (mCurrentUser != null) {
            // Update user profile in Firebase Authentication
            mCurrentUser.updateProfile(new UserProfileChangeRequest.Builder().build())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Profile updated successfully in Firebase Authentication
                            // Now update the user's document in Firestore
                            DocumentReference userRef = mFirestore.collection("profiles")
                                    .document(mCurrentUser.getUid());

                            // Save the profile document in Firestore
                            userRef.update("telephone",phoneNum)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            // Profile document created successfully in Firestore
                                            // Invoke the success callback
                                            callback.onProfileUpdateSuccess();
                                        } else {
                                            // Failed to create profile document in Firestore
                                            // Invoke the error callback
                                            callback.onProfileUpdateFailure();
                                        }
                                    });
                        } else {
                            // Failed to update user profile in Firebase Authentication
                            // Invoke the error callback
                            callback.onProfileUpdateFailure();
                        }
                    });
        }
    }




    public interface ProfileUpdateCallback {
        void onProfileUpdateSuccess();

        void onProfileUpdateFailure();
    }

    public interface ProfileCheckCallback {
        void onProfileExists(Profile profile);

        void onProfileEmpty();
        void onGetProfile(Profile profile);

        void onProfileCheckError(Exception e);
    }

    public void checkDocument(String userId, ProfileCheckCallback callback) {
        DocumentReference profileRef = mFirestore.collection("profiles").document(userId);

        profileRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document != null && document.exists()) {
                    Profile profile = document.toObject(Profile.class);
                    if (profile != null) {
                        if (profile.getFirstName() != null && !profile.getFirstName().isEmpty()
                                && profile.getLastName() != null && !profile.getLastName().isEmpty()
                                && profile.getTelephone() != null && !profile.getTelephone().isEmpty()
                                && profile.getTcId() != null && !profile.getTcId().isEmpty()
                                && profile.getBirthDate() != null) {
                            callback.onProfileExists(profile);
                        } else {
                            callback.onProfileEmpty();
                        }
                    }
                } else {
                    callback.onProfileEmpty();
                }
            } else {
                callback.onProfileCheckError(task.getException());
            }
        });
    }

    public interface ProfileCompletenessCheckCallback {
        void onIdIncomplete();

        void onPhoneIncomplete();

        void onProfileComplete();

        void onCheckError(Exception e);
    }

    public void checkProfileCompleteness(String userId, ProfileCompletenessCheckCallback callback) {
        DocumentReference profileRef = mFirestore.collection("profiles").document(userId);

        profileRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document != null && document.exists()) {
                    Profile profile = document.toObject(Profile.class);
                    if (profile != null) {
                        // Check if ID related information is incomplete
                        if (profile.getFirstName() == null || profile.getFirstName().isEmpty()
                                || profile.getLastName() == null || profile.getLastName().isEmpty()
                                || profile.getTcId() == null || profile.getTcId().isEmpty()
                                || profile.getBirthDate() == null || profile.getBirthDate().isEmpty()) {
                            callback.onIdIncomplete();
                        }
                        // Check if phone is incomplete
                        else if (profile.getTelephone() == null || profile.getTelephone().isEmpty()) {
                            callback.onPhoneIncomplete();
                        } else {
                            callback.onProfileComplete();
                        }
                    }
                } else {
                    callback.onCheckError(new Exception("Profile document doesn't exist"));
                }
            } else {
                callback.onCheckError(task.getException());
            }
        });
    }

    // Other methods related to profile management can be added here
    public boolean isProfileComplete(String displayName, String email) {
        return displayName != null && !displayName.isEmpty() &&
                email != null && !email.isEmpty();
    }

    public void signOut() {
        mAuth.signOut();
    }


}


