package com.mobil.bizden.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.ProfileController;
import com.mobil.bizden.controllers.UserLocationController;


public class MainActivity extends AppCompatActivity {

    private ImageView logoImageView;
    private View rootView;
    private Runnable animationDelayRunnable;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoImageView = findViewById(R.id.bizdenLogo);
        rootView = findViewById(android.R.id.content);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(animationDelayRunnable);
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
        handler.postDelayed(animationDelayRunnable, 2000); // 2000 milliseconds delay
    }
    UserLocationController.UserLocationCheck callbackLocation= new UserLocationController.UserLocationCheck() {
        @Override
        public void onLocationFound() {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onLocationNotFound() {
            Intent intent = new Intent(MainActivity.this, UserLocation.class);
            startActivity(intent);
            finish();
        }
    };

    private void checkUserProf() {
        // Stop the animation delay if it's still running
        handler.removeCallbacks(animationDelayRunnable);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            ProfileController.ProfileCompletenessCheckCallback profileCheckCallback = new ProfileController.ProfileCompletenessCheckCallback() {
                @Override
                public void onIdIncomplete() {
                    Intent intent = new Intent(MainActivity.this, FirstLogin.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onPhoneIncomplete() {
                    Intent intent = new Intent(MainActivity.this, FirstLogin.class);
                    startActivity(intent);
                    finish();

                }

                @Override
                public void onProfileComplete() {
                    UserLocationController userLocationController= new UserLocationController();
                    userLocationController.checkUserLocation(user.getUid(),callbackLocation);

                }

                @Override
                public void onCheckError(Exception e) {
                    System.out.println(e);
                }
            };
            ProfileController profileController = new ProfileController();
            profileController.checkProfileCompleteness(user.getUid(), profileCheckCallback);
        } else {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }
    }
}
