package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MoodResult extends AppCompatActivity {
    TextView textInput;
//    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_result);

        Intent intent =getIntent();
        String result = intent.getStringExtra("result");
        textInput = findViewById(R.id.mood_result);
        textInput.setText(result);

        findViewById(R.id.mood_result_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_intent = new Intent(MoodResult.this,MainActivity.class);
                startActivity(back_intent);
            }
        });

    }

}