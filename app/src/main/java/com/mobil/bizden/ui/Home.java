package com.mobil.bizden.ui;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.UserController;

public class Home extends AppCompatActivity{
    DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    BottomNavigationView bottomNavigationView;
    UserProfile_frag userProfile_frag= new UserProfile_frag();
    GatheringAreas gatheringAreas= new GatheringAreas();
    Requests userRequests= new Requests();
    MainPage mainPage=new MainPage();
    ActionBarDrawerToggle toggle;
    UserController userController= new UserController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this::onBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        navigationDrawer.setNavigationItemSelectedListener(this::onDrawerNavigationItemSelected);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private boolean onBottomNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, userProfile_frag)
                        .commit();
                drawerLayout.closeDrawer(navigationDrawer);  // Close the drawer after making a selection

                return true;

            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, mainPage)
                        .commit();
                drawerLayout.closeDrawer(navigationDrawer);  // Close the drawer after making a selection

                return true;


            case R.id.drawer:
                if (drawerLayout.isDrawerOpen(navigationDrawer)) {
                    drawerLayout.closeDrawer(navigationDrawer);
                } else {
                    drawerLayout.openDrawer(navigationDrawer);
                }
                return true;
        }
        return false;
    }
    private boolean onDrawerNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_requests:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, userProfile_frag)
                        .commit();
                drawerLayout.closeDrawer(navigationDrawer);  // Close the drawer after making a selection

                return true;

            case R.id.nav_logout:
                userController.logoutUser();
                Intent intent= new Intent(Home.this,Login.class);
                startActivity(intent);
                finish();
                drawerLayout.closeDrawer(navigationDrawer);  // Close the drawer after making a selection

                return true;

            case R.id.nav_entryCodes:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, userRequests)
                        .commit();
                drawerLayout.closeDrawer(navigationDrawer);  // Close the drawer after making a selection

                return true;

            case R.id.nav_gatheringAreas:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, gatheringAreas )
                        .commit();
                drawerLayout.closeDrawer(navigationDrawer);  // Close the drawer after making a selection

                return true;
            default:
                System.out.println("IDNOTFOUNDS");
        }
        return true;
    }
}