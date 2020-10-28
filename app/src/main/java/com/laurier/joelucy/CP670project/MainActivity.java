package com.laurier.joelucy.CP670project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME ="MAINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_goals, R.id.navigation_mood)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    //go to set goals page
    public void get_setgoal_activity(View view){
        Intent set_goal_intent = new Intent(MainActivity.this,SetGoalActivity.class);
        startActivity(set_goal_intent);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    public void get_logmood_activity(View view){
        Intent log_mood_intent = new Intent(MainActivity.this,LogMoodActivity.class);
        startActivity(log_mood_intent);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

}