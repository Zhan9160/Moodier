package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
    }
    public void create_goal(View view){
        Intent create_goal_intent = new Intent(SetGoalActivity.this,CreateGoalActivity.class);
        startActivity(create_goal_intent);

    }
}