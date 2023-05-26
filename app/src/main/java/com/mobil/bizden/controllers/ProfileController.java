package com.mobil.bizden.controllers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.Profile;

import java.util.Date;

public class ProfileController {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseUser mCurrentUser;

    public ProfileController() {
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
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
                            DocumentReference userRef = mFirestore.collection("users")
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

    public interface ProfileUpdateCallback {
        void onProfileUpdateSuccess();
        void onProfileUpdateFailure();
    }


    public interface ProfileCheckCallback {
        void onProfileExists(Profile profile);
        void onProfileEmpty();
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


    // Other methods related to profile management can be added here
    public boolean isProfileComplete(String displayName, String email) {
        return displayName != null && !displayName.isEmpty() &&
                email != null && !email.isEmpty();
    }

    public void signOut() {
        mAuth.signOut();
    }
}
