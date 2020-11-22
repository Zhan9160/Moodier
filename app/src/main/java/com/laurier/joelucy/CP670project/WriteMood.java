package com.laurier.joelucy.CP670project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.laurier.joelucy.CP670project.ui.MoodDatabaseHelper;

import java.util.ArrayList;

public class WriteMood extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mood);
    }

    public void go_to_moodhistory(View view){

        Intent intent_moodhistory = new Intent(WriteMood.this,MainActivity.class);
        startActivity(intent_moodhistory);
    }
}