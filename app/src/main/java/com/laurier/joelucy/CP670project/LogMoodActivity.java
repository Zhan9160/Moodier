package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.google.android.material.snackbar.Snackbar;

public class LogMoodActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME ="LogMoodActivity";
    SeekBar seekbar;
    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mood);
        seekbar = (SeekBar) findViewById(R.id.seekBar2);
        findViewById(R.id.set_goal_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent log_mood_intent = new Intent(LogMoodActivity.this,WriteMood.class);
                startActivity(log_mood_intent);
            }
        });



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                switch (progress){
                    case 0:
                        snackbar.make(findViewById(R.id.seekBar2), "Very bad", Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText(""+progress);

                                    }
                                }).show();
                        break;
                    case 1:
                        snackbar.make(findViewById(R.id.seekBar2), "Bad", Snackbar.LENGTH_LONG)
                            .setAction("Action",new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    snackbar = snackbar.setText("Bad"+progress);

                                }
                            }).show();
                    break;
                    case 2:
                        snackbar.make(findViewById(R.id.seekBar2), "kind of okay", Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Bad"+progress);

                                    }
                                }).show();
                        break;
                    case 3:
                        snackbar.make(findViewById(R.id.seekBar2), "good", Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Bad"+progress);

                                    }
                                }).show();
                        break;
                    case 4:
                        snackbar.make(findViewById(R.id.seekBar2), "Very good", Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Very good"+progress);

                                    }
                                }).show();
                        break;
                    case 5:
                        snackbar.make(findViewById(R.id.seekBar2), "Excellent", Snackbar.LENGTH_LONG)
                                .setAction("Action",new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        snackbar = snackbar.setText("Excellent"+progress);

                                    }
                                }).show();


                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void get_writemood_activity(View v){
        Intent log_mood_intent = new Intent(LogMoodActivity.this,WriteMood.class);
        startActivity(log_mood_intent);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }
}