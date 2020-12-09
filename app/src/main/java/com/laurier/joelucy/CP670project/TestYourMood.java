package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestYourMood extends AppCompatActivity {
    public int mark_total = 0;//to get the final result and evaluate the mood status
    public int mark1, mark2, mark3 = 0;
    RadioButton yes1, yes2, yes3;
    RadioButton no1, no2, no3;
    RadioGroup group1, group2, group3;
    String result;

    //    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_your_mood2);

        group1 = findViewById(R.id.group1);
        yes1 = findViewById(R.id.yes1);
        no1 = findViewById(R.id.no1);
        group2 = findViewById(R.id.group2);
        yes2 = findViewById(R.id.yes2);
        no2 = findViewById(R.id.no2);
        group3 = findViewById(R.id.group3);
        yes3 = findViewById(R.id.yes3);
        no3 = findViewById(R.id.no3);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (yes1.isChecked()) {
                    yes1.setTextColor(Color.parseColor("#FF0033"));
                } else {
                    yes1.setTextColor(Color.parseColor("#000000"));
                }
                if (no1.isChecked()) {
                    no1.setTextColor(Color.parseColor("#FF0033"));
                } else {
                    no1.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //question 2
                if (yes2.isChecked()) {
                    yes2.setTextColor(Color.parseColor("#FF0033"));
                } else {
                    yes2.setTextColor(Color.parseColor("#000000"));
                }
                if (no2.isChecked()) {
                    no2.setTextColor(Color.parseColor("#FF0033"));
//                    mark2 = 0;
                } else {
                    no2.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
        group3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //question3
                if (yes3.isChecked()) {
                    yes3.setTextColor(Color.parseColor("#FF0033"));
//                      mark3 +=1;
                } else {
                    yes3.setTextColor(Color.parseColor("#000000"));
                }
                if (no3.isChecked()) {
//                      mark3 = 0;
                    no3.setTextColor(Color.parseColor("#FF0033"));
                } else {
                    no3.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
        findViewById(R.id.button_submit_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int optionID1 = group1.getCheckedRadioButtonId();
                int optionID2 = group2.getCheckedRadioButtonId();
                int optionID3 = group3.getCheckedRadioButtonId();
                TextView tv = findViewById(R.id.invisible_result);
                if (optionID1 == R.id.yes1 & optionID2 == R.id.yes2 & optionID3 == R.id.yes3) {
                    result = "You are an optimistic person. Good luck:)";
                } else if (optionID1 == R.id.no1 & optionID2 == R.id.no2 & optionID3 == R.id.no3) {
                    result = "Oh no. You should be positive. Suggest you to see a doctor.:(";
                } else {
                    result = "You should be careful. Some mental health problems may occur if you do not share your thought with your friends or family. Be relax!:)";
                }
                tv.setText(result);

                Intent result_intent = new Intent(TestYourMood.this, MoodResult.class);
                result_intent.putExtra("result",result);
                startActivity(result_intent);
            }
        });

    }
}