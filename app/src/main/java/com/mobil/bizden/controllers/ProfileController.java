package com.mobil.bizden.controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileController {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseUser mCurrentUser;

    public ProfileController() {
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
    }

    public void updateProfile(String displayName, String email) {
        if (mCurrentUser != null) {
            // Update user profile in Firebase Authentication
            mCurrentUser.updateProfile(new UserProfileChangeRequest.Builder()
                            .setDisplayName(displayName)
                            .build())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Profile updated successfully in Firebase Authentication
                            // Now update the user's document in Firestore
                            DocumentReference userRef = mFirestore.collection("users")
                                    .document(mCurrentUser.getUid());

                            userRef.update("displayName", displayName, "email", email)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            // User document updated successfully in Firestore
                                            // Do any additional operations if needed
                                        } else {
                                            // Failed to update user document in Firestore
                                        }
                                    });
                        } else {
                            // Failed to update user profile in Firebase Authentication
                        }
                    });
        }
    }

    // Other methods related to profile management can be added here

    public void signOut() {
        mAuth.signOut();
    }
}
