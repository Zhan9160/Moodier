package com.laurier.joelucy.CP670project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mood);
//        listView = findViewById(R.id.list_view);
//
        list = new ArrayList<>();
        final MoodAdapter messageAdapter = new MoodAdapter(this);
//        listView.setAdapter(messageAdapter);

        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
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

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = simpleDateFormat.format(date)+text_feel.getText().toString() +"\n" + textInput.getText().toString();

                list.add(msg);
                messageAdapter.notifyDataSetChanged();
                textInput.setText("");
                ContentValues values = new ContentValues();
                values.put("message", msg);
                db.insert(MoodDatabaseHelper.TABLE_NAME, null, values);
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

        Intent intent_moodhistory = new Intent(WriteMood.this,ListMoodRecord.class);
        startActivity(intent_moodhistory);

    }
}