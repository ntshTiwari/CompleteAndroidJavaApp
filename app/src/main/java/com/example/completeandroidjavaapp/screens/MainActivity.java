package com.example.completeandroidjavaapp.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        /// if we want to hide title
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(R.string.welcome);

        /// to set a custom view in the toolbar
//        getSupportActionBar().setCustomView(R.layout.toolbar_header);


        /// we attach the current activity to the ActionBar drawer
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.nav_open, R.string.nav_close);

        /// adding this, adds support for the drawer to change to back arrow when drawer is opened
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /// this changes the drawer logo color
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(
           getResources().getColor(R.color.white)
        );

        /// adding this makes the drawer icon visible
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addDrawerItemClickListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_dropdown_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        String[] states = getResources().getStringArray(R.array.states);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, states);

        spinner.setAdapter(adapter);

        /// this is how we change the bg of the dropdown
        ColorDrawable colorDrawable = new ColorDrawable(0xFFCE9B2C);
        spinner.setPopupBackgroundDrawable(colorDrawable);

        /// to change the color of the text of the dropdown, we set this theme
        Resources.Theme spinnerTheme = new ContextThemeWrapper(getApplicationContext(), R.style.SpinnerTheme).getTheme();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            adapter.setDropDownViewTheme(spinnerTheme);
        }
        return true;
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