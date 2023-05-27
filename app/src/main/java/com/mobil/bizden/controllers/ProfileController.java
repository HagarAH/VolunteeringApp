package com.mobil.bizden.controllers;
import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.Profile;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public interface ProfileUpdateCallback {
        void onProfileUpdateSuccess();
        void onProfileUpdateFailure();
    }

    public void verifyPhoneNumber(String phoneNumber, Activity activity, PhoneVerificationCallback callback) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60, // Timeout duration in seconds
                TimeUnit.SECONDS,
                activity,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        // Verification completed successfully
                        callback.onPhoneVerificationSuccess(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // Verification failed
                        callback.onPhoneVerificationFailure(e);
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        // Code sent successfully
                        callback.onCodeSent(verificationId, forceResendingToken);
                    }
                });
    }

    public interface PhoneVerificationCallback {
        void onPhoneVerificationSuccess(PhoneAuthCredential credential);
        void onPhoneVerificationFailure(Exception e);
        void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken);
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
