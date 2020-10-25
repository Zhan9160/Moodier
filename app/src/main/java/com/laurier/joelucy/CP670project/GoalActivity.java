package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class GoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        //go to set goals page
//        findViewById(R.id.set_goal_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(GoalActivity.this, SetGoalActivity.class);
//                startActivityForResult(intent, 10);
//
//            }
//        });
    }
    //go to set goals page
    public void get_setgoal_activity(View v){
        Intent set_goal_intent = new Intent(GoalActivity.this,SetGoalActivity.class);
        startActivity(set_goal_intent);
        //Log.i(ACTIVITY_NAME, "In onCreate()");
    }
}