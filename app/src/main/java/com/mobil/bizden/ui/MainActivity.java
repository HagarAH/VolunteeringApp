package com.mobil.bizden.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;
import com.mobil.bizden.controllers.UserLocationController;
public class MainActivity extends AppCompatActivity {

    private ImageView logoImageView;
    private View rootView;
    private Runnable animationDelayRunnable;
    private Handler handler = new Handler();
    MotionLayout motionLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        setContentView(R.layout.activity_main);
        motionLayout = findViewById(R.id.motionLayout);
        logoImageView = findViewById(R.id.bizdenLogo);
        rootView = findViewById(android.R.id.content);

        motionLayout = findViewById(R.id.motionLayout);
        logoImageView = findViewById(R.id.bizdenLogo);

        motionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(animationDelayRunnable);
                motionLayout.transitionToEnd();
                checkUserProf();
            }
        });

        animationDelayRunnable = new Runnable() {
            @Override
            public void run() {
                checkUserProf();
            }
        };

        // Start the animation delay
        handler.postDelayed(animationDelayRunnable, 1000);
    }
    UserLocationController.UserLocationCheck callbackLocation = new UserLocationController.UserLocationCheck() {
        @Override
        public void onLocationFound() {
            navigateTo(Home.class);
        }

        @Override
        public void onLocationNotFound() {
            navigateTo(UserLocation.class);
        }
    };

    private void checkUserProf() {
        handler.removeCallbacks(animationDelayRunnable);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            ProfileController.ProfileCompletenessCheckCallback profileCheckCallback = new ProfileController.ProfileCompletenessCheckCallback() {
                @Override
                public void onIdIncomplete() {
                    navigateToFirstLogin();
                }

                @Override
                public void onPhoneIncomplete() {
                    navigateToFirstLogin();
                }

                @Override
                public void onProfileComplete() {
                    UserLocationController userLocationController = new UserLocationController();
                    userLocationController.checkUserLocation(user.getUid(), callbackLocation);

                }

                @Override
                public void onCheckError(Exception e) {
                    System.out.println(e);
                }
            };
            ProfileController profileController = new ProfileController();
            profileController.checkProfileCompleteness(user.getUid(), profileCheckCallback);
        } else {
            navigateTo(Login.class);
        }
    }

    private void navigateToFirstLogin() {
        navigateTo(FirstLogin.class);
    }

    private void navigateTo(Class<?> activityClass) {
        handler.removeCallbacks(animationDelayRunnable);
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
        finish();
    }
}
