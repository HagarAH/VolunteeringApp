package com.mobil.bizden.controllers;
import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobil.bizden.models.Profile;
import com.mobil.bizden.models.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    private FirebaseAuth mAuth;

    public UserController() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(String email, String password, final RegistrationCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        String uid = user.getUid();

                        // Create a UserModel object with email and uid
                        User userModel = new User(email, uid);

                        // Add the User to the 'users' collection
                        FirebaseFirestore.getInstance().collection("users").document(uid)
                                .set(userModel)
                                .addOnSuccessListener(aVoid -> {
                                    // User added to 'users' collection successfully

                                    // Add UID to the 'profiles' collection
                                    Profile profileModel = new Profile(uid,"","","","",new Date());

                                    FirebaseFirestore.getInstance().collection("profiles").document(uid)
                                            .set(profileModel)
                                            .addOnSuccessListener(aVoid1 -> {
                                                // UID added to 'profiles' collection successfully
                                                callback.onRegistrationSuccess(user);
                                            })
                                            .addOnFailureListener(e -> {
                                                // Failed to add UID to 'profiles' collection
                                                String errorMessage = e.getMessage();
                                                Log.e(TAG, "Failed to add UID to 'profiles' collection: " + errorMessage);
                                                callback.onRegistrationFailure(errorMessage);
                                            });
                                })
                                .addOnFailureListener(e -> {
                                    // Failed to add User to 'users' collection
                                    String errorMessage = e.getMessage();
                                    Log.e(TAG, "Failed to add User to 'users' collection: " + errorMessage);
                                    callback.onRegistrationFailure(errorMessage);
                                });
                    } else {
                        String errorMessage = task.getException().getMessage();
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        callback.onRegistrationFailure(errorMessage);
                    }
                });
    }


    public void loginUser(String email, String password, final ProgressBar progressBar, final LoginCallback callback) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            callback.onSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            callback.onFailure(task.getException());
                        }
                    }
                });
    }

    // Define a callback interface
   public  interface LoginCallback {
        void onSuccess();
        void onFailure(Exception exception);

    }

    public void logoutUser() {
        mAuth.signOut();
    }

    public interface RegistrationCallback {
        void onRegistrationSuccess(FirebaseUser user);

        void onRegistrationFailure(String errorMessage);
    }


}
