package com.example.completeandroidjavaapp.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.completeandroidjavaapp.R;
import com.example.completeandroidjavaapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener
{
    ActivityMainBinding binding;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawer = binding.mainDrawer;
        mainToolbar = binding.mainToolbar;

        /// attach our toolbar as action bar
        setSupportActionBar(mainToolbar);

        /// we attach the current activity to the ActionBar drawer
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.nav_open, R.string.nav_close);

        /// adding this, adds support for the drawer to change to back arrow when drawer is opened
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /// adding this makes the drawer icon visible
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addDrawerItemClickListeners();
    }

    private void addDrawerItemClickListeners() {
        NavigationView navView = drawer.findViewById(R.id.drawer_nav_view);
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.first_item:
                Intent secondScreen = new Intent(this, SecondScreen.class);
                startActivity(secondScreen);
                Log.d("Log","ID 1 PRESSED");
                /// if we return true, then it closes the drawer, else doesn't
                return false;
            case R.id.second_item:
                Log.d("Log","ID 2 PRESSED");
                return true;
        }
        //close navigation drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /// adding this opens the drawer when the drawer icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}