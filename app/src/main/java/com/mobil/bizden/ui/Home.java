package com.mobil.bizden.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.UserController;

public class Home extends AppCompatActivity{
    DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    BottomNavigationView bottomNavigationView;
    UserProfile_frag userProfile_frag= new UserProfile_frag();
    GatheringAreas_frag gatheringAreas= new GatheringAreas_frag();
    Requests_frag userRequestsFrag = new Requests_frag();
    MainPage_frag mainPage=new MainPage_frag();
    ActionBarDrawerToggle toggle;
    UserController userController= new UserController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this::onBottomNavigationItemSelected);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        navigationDrawer.setNavigationItemSelectedListener(this::onDrawerNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.home);
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
                if (drawerLayout.isDrawerOpen(navigationDrawer)) {
                    navigationDrawer.setCheckedItem(0);
                    drawerLayout.closeDrawer(navigationDrawer);
                }

                return true;

            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, mainPage)
                        .commit();
                if (drawerLayout.isDrawerOpen(navigationDrawer)) {
                    navigationDrawer.setCheckedItem(0);
                    drawerLayout.closeDrawer(navigationDrawer);
                }

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
                        .replace(R.id.flFragment, userRequestsFrag)
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
                drawerLayout.closeDrawer(navigationDrawer);  // Close the drawer after making a selection
        }
        return true;
    }
}