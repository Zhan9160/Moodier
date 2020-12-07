package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SetGoal2 extends AppCompatActivity {
    private int catetype = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal2);
        InitEvents();

    }

    private void InitEvents() {

        TextView textView1 = (TextView) findViewById(R.id.treatGoal);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catetype =1;
                startCreateNewGoal(catetype);
            }
        });
    }

    private void startCreateNewGoal(int categoryType)
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CategoryType", Integer.toString(categoryType));
        editor.commit();
        Intent intent = new Intent(SetGoal2.this, CreateNewGoal.class);
        startActivity(intent);
    }
}