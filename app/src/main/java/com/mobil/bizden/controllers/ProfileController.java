package com.mobil.bizden.controllers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.Profile;

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
        if (isProfileComplete(displayName, email)) {
            // Proceed with profile update
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
        } else {
            // Profile is not complete, handle the error or show a message to the user
        }
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
