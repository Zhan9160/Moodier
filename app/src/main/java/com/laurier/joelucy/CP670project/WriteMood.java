package com.laurier.joelucy.CP670project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.laurier.joelucy.CP670project.BD.MySQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteMood extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "WriteMood";

    private ListView listView;
    private EditText textInput;
    private ArrayList<String> list;
    //MoodAdapter adapter;
    MoodDatabaseHelper db_helper;
    SQLiteDatabase db;
    Cursor cursor;
    //FrameLayout frameLayout;
    private Button sendButton;
    SimpleDateFormat simpleDateFormat;
    Date date;
    TextView text_feel;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mood);
//        listView = findViewById(R.id.list_view);
//
        list = new ArrayList<>();
        final MoodAdapter messageAdapter = new MoodAdapter(this);
//        listView.setAdapter(messageAdapter);
        progress = findViewById(R.id.progressBar);
        simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// HH:mm:ss
//获取当前时间
        date = new Date(System.currentTimeMillis());
        //time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
//
        textInput = findViewById(R.id.write_mood);
        sendButton = findViewById(R.id.submit_new_mood);
        //text = findViewById(R.id.seekBar2).toString();
        //LayoutInflater factory = LayoutInflater.from(ListMoodRecord.this);
        LayoutInflater log_mood_activity = LayoutInflater.from(WriteMood.this);
        View log_mood_layout = log_mood_activity.inflate(R.layout.activity_log_mood, null);
        text_feel = log_mood_layout.findViewById(R.id.mood_value);
        progress.setProgress(50);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInput.getText().toString().trim().isEmpty())
                {
                    showDialog();
                    return;
                }
                String msg = simpleDateFormat.format(date)+text_feel.getText().toString() +"\n" + textInput.getText().toString();

                list.add(msg);
                messageAdapter.notifyDataSetChanged();
                textInput.setText("");
                ContentValues values = new ContentValues();
                values.put("message", msg);
                db.insert(MoodDatabaseHelper.TABLE_NAME, null, values);
                progress.setProgress(100);
                SaveMoodData(msg);
                returnHome();
            }
        });
        findViewById(R.id.write_mood_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress.getProgress()==100){
                    Intent write_mood_back_intent = new Intent(WriteMood.this,LogMoodActivity.class);
                    startActivity(write_mood_back_intent);
                }
                else{
//                    Toast toask = Toast.makeText(this,"Are you make sure to exit before saving your mood? ")
                    AlertDialog.Builder builder = new AlertDialog.Builder(WriteMood.this);
// 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                            .setTitle(R.string.dialog_title)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                    Intent resultIntent = new Intent(  );
                                    resultIntent.putExtra("Response", "Here is my response");
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            })
                            .show();
                }

            }
        });
        db_helper = new MoodDatabaseHelper(this);
        db = db_helper.getWritableDatabase();
        cursor = db.rawQuery("select * from " + MoodDatabaseHelper.TABLE_NAME,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(MoodDatabaseHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount());
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.i(ACTIVITY_NAME, cursor.getColumnName(i));
            }
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

    }

    private void SaveMoodData(String msg) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        ContentValues values = new ContentValues();
        //values.put(MySQLiteHelper.Mood_ID, goalCategory);
        values.put(MySQLiteHelper.Mood_Text, msg);
        values.put(MySQLiteHelper.Mood_CreateOn, date.toString());
        MySQLiteHelper dbOpenHelper = new MySQLiteHelper(this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();

        long insertId = database.insert(MySQLiteHelper.TABLE_MoodLog, null,
                values);
/*        String[] columns = {MySQLiteHelper.Mood_ID,
                MySQLiteHelper.Mood_Text, MySQLiteHelper.Mood_UID};
        Cursor c = database.query(MySQLiteHelper.TABLE_MoodLog,
                columns, MySQLiteHelper.Mood_ID + " = " + insertId, null,
                null, null, null);
        c.moveToFirst();
        Log.i("goal is : " ,c.getString(1));
        c.close();*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db_helper.close();
        db.close();
    }

    private class MoodAdapter extends ArrayAdapter<String> {

        public MoodAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = WriteMood.this.getLayoutInflater();
            View result = null;
            if (position%2 == 0)
                result = inflater.inflate(R.layout.first_row, null);
            else
                result = inflater.inflate(R.layout.second_row, null);
            TextView message = result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }

    public void go_to_moodhistory(View view){
        if (progress.getProgress()==100 || progress.getProgress()==50){
            Intent intent_moodhistory = new Intent(WriteMood.this,ListMoodRecord.class);
            startActivity(intent_moodhistory);
        }


    }

    private void returnHome()
    {
        Intent intent = new Intent(WriteMood.this, MainActivity.class);
        startActivity(intent);
    }

    private void showDialog(){
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        //builder.setIcon(R.drawable.picture);
        builder.setTitle("Tips");
        builder.setMessage("please input mood text");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        android.app.AlertDialog dialog=builder.create();
        dialog.show();

    }
}