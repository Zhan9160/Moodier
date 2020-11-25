package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class LogMoodActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME ="LogMoodActivity";
    SeekBar seekbar;
    Snackbar snackbar;
    public String text;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mood);
        seekbar = (SeekBar) findViewById(R.id.seekBar2);
//        findViewById(R.id.set_mood_button).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent log_mood_intent = new Intent(LogMoodActivity.this,WriteMood.class);
//                startActivity(log_mood_intent);
//            }
//        });



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                switch (progress){
                    case 0:
                        text = "Horrible";
                        snackbar.make(findViewById(R.id.seekBar2), text, Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText(""+progress);

                                    }
                                }).show();
                        break;
                    case 1:
                        text = "Bad";
                        snackbar.make(findViewById(R.id.seekBar2), text, Snackbar.LENGTH_LONG)
                            .setAction("Action",new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    snackbar = snackbar.setText("Bad"+progress);

                                }
                            }).show();
                    break;
                    case 2:
                        text = "okay";
                        snackbar.make(findViewById(R.id.seekBar2), text, Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Bad"+progress);

                                    }
                                }).show();
                        break;
                    case 3:
                        text = "nice";
                        snackbar.make(findViewById(R.id.seekBar2), text, Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Bad"+progress);

                                    }
                                }).show();
                        break;
                    case 4:
                        text = "Very good";
                        snackbar.make(findViewById(R.id.seekBar2), text, Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Very good"+progress);

                                    }
                                }).show();
                        break;
                    case 5:
                        text = "Perfect";
                        snackbar.make(findViewById(R.id.seekBar2), text, Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Excellent"+progress);

                                    }
                                }).show();


                }
                textView = findViewById(R.id.mood_value);
                textView.setText(text);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button button = findViewById(R.id.set_mood_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log_mood_intent = new Intent(LogMoodActivity.this,WriteMood.class);
                startActivity(log_mood_intent);
            }
        });
    }
//    public void get_writemood_activity(View v){
//        Intent log_mood_intent = new Intent(LogMoodActivity.this,WriteMood.class);
//        startActivity(log_mood_intent);
//        //textView;
//        Log.i(ACTIVITY_NAME, "In onCreate()");
//    }
}