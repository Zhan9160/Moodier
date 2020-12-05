package com.laurier.joelucy.CP670project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
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

//        findViewById(R.id.set_mood_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent log_mood_intent = new Intent(MainActivity.this,LogMoodActivity.class);
//                startActivity(log_mood_intent);
//                Log.i(ACTIVITY_NAME, "In onCreate()");
//            }
//        });
//        findViewById(R.id.set_goal_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent set_goal_intent = new Intent(MainActivity.this,SetGoalActivity.class);
//                startActivity(set_goal_intent);
//                Log.i(ACTIVITY_NAME, "In onCreate()");
//            }
//        });
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
    public void go_to_moodhistory(View view){
        Intent log_mood_intent = new Intent(MainActivity.this,ListMoodRecord.class);
        startActivity(log_mood_intent);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }


}
