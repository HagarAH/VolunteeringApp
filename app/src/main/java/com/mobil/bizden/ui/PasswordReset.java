package com.mobil.bizden.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.mobil.bizden.R;
import com.mobil.bizden.databinding.FragmentResetPassBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PasswordReset extends Fragment {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler(Looper.myLooper());

    private View mContentView;
    private View mControlsView;

    private boolean mVisible;

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */


    private FragmentResetPassBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentResetPassBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    private void exitFragment() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStack();
        }
    }
    private FirebaseAuth mAuth;
    private EditText etEmail;
    private Button btnResetPassword;
    private Button btnResetPasswordExit;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVisible = true;

        mAuth = FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.EditTextResetPassword);

        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        btnResetPasswordExit = view.findViewById(R.id.btnResetPasswordExit);

        btnResetPasswordExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitFragment();
            }
        });
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();

                if (!email.isEmpty()) {
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(view.getContext(), "Password reset email sent. Check your inbox.", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(view.getContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(view.getContext(), "Please enter your email address.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

    }





}