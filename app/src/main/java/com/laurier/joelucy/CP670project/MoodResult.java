package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.laurier.joelucy.CP670project.ui.mood.MoodFragment;

public class MoodResult extends AppCompatActivity {
    TextView textInput;
    String result;
//    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_result);

        SharedPreferences preferences=getSharedPreferences("sharedata", Context.MODE_PRIVATE);
        result = preferences.getString("testMoodResult","");
        /*Intent intent =getIntent();
        result = intent.getStringExtra("result");*/
        textInput = findViewById(R.id.mood_result);
        textInput.setText(result);

        //InitEvents();

        findViewById(R.id.mood_result_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences mySharedPreferences = getSharedPreferences("moodResult", MODE_PRIVATE);
//                SharedPreferences.Editor editor = mySharedPreferences.edit();
//                editor.putString("mood", result);
//                editor.commit();

//                Intent back_intent = new Intent(MoodResult.this,MainActivity.class);

              /*  Intent back_intent = new Intent(MoodResult.this,MoodFragment.class);
                back_intent.putExtra("result",result);
                startActivity(back_intent);*/
                returnBack();

            }
        });

        //findViewById(R.id)

    }

   /* private void InitEvents() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("moodResult", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("result", result);
        editor.commit();
//        Intent intent = new Intent(MoodResult.this, CreateNewGoal.class);
//        startActivity(intent);
    }*/

    private void returnHome()
    {
        Intent intent = new Intent(MoodResult.this, MainActivity.class);
        startActivity(intent);
    }

    private  void returnBack()
    {
        this.finish();
    }
}