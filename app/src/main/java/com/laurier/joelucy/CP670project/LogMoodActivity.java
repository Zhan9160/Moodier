package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LogMoodActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME ="LogMoodActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mood);
    }
    public void get_logmood_activity(View view){
        Intent log_mood_intent = new Intent(LogMoodActivity.this,LogMoodActivity.class);
        startActivity(log_mood_intent);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }
}