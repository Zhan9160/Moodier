package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.laurier.joelucy.CP670project.BD.MySQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_goal);
        InitEvents();
    }

    private void InitEvents() {
        Button btnSubmit = (Button) findViewById(R.id.button6);
        final EditText editText = (EditText)findViewById(R.id.editTextTextMultiLine2);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputGoal = editText.getText().toString().trim();
                if(inputGoal.isEmpty())
                    return;
                SaveGoalData(inputGoal);
                returnHome();
            }
        });
    }

    private void SaveGoalData(String inputGoal) {

        SharedPreferences preferences=getSharedPreferences("sharedata", Context.MODE_PRIVATE);

        String goalCategory=preferences.getString("CategoryType", "1");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.Goal_CID, goalCategory);
        values.put(MySQLiteHelper.Goal_Text, inputGoal);
        values.put(MySQLiteHelper.Goal_CreateOn, date.toString());
        MySQLiteHelper dbOpenHelper = new MySQLiteHelper(this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();

        //database.delete(MySQLiteHelper.TABLE_GoalLog, null, null);

        long insertId = database.insert(MySQLiteHelper.TABLE_GoalLog, null,
                values);
        String[] columns = {MySQLiteHelper.Goal_ID,
                MySQLiteHelper.Goal_Text, MySQLiteHelper.Goal_CID};
        Cursor cursor = database.query(MySQLiteHelper.TABLE_GoalLog,
                columns, MySQLiteHelper.Goal_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Log.i("goal is : " ,cursor.getString(1));
        cursor.close();
    }

    private void returnHome()
    {
        Intent intent = new Intent(CreateNewGoal.this, MainActivity.class);
        startActivity(intent);
    }
}