package com.laurier.joelucy.CP670project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

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

public class SetGoalActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "SetGoal";

    private EditText textInput;
    private ArrayList<String> list;
    GoalAdapter messageAdapter;
    GoalDatabaseHelper db_helper;
    SQLiteDatabase db;
    Cursor cursor;
    SimpleDateFormat simpleDateFormat;
    Date date;
    //ItemTouchHelper.Callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        //recyclerView.setItemViewSwipeEnabled(true);

        simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = new Date(System.currentTimeMillis());

        ListView listView = findViewById(R.id.recyclerView_goalRecord);
        list = new ArrayList<>();
        messageAdapter =new GoalAdapter( this );
        listView.setAdapter(messageAdapter);
//        ItemTouchHelper.Callback callback=new RecycleItemTouchHelper(adapter);
//        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
//        itemTouchHelper.attachToRecyclerView(recycleview);
        //listView.setOnItemSelectedListener();//.setItemViewSwipeEnabled(true);


        LayoutInflater create_goal_activity = LayoutInflater.from(SetGoalActivity.this);
        View create_goal_layout = create_goal_activity.inflate(R.layout.activity_create_goal, null);
        //textInput = create_goal_layout.findViewById(R.id.create_goal);
        textInput = findViewById(R.id.create_goal);
        //FrameLayout frameLayout;
        Button sendButton = findViewById(R.id.button3);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = simpleDateFormat.format(date) +"\n" + textInput.getText().toString();
                list.add(msg);
                messageAdapter.notifyDataSetChanged();
                textInput.setText("");
                ContentValues values = new ContentValues();
                values.put("message", msg);
                db.insert(GoalDatabaseHelper.TABLE_NAME, null, values);
            }
        });
        db_helper = new GoalDatabaseHelper(this);
        db = db_helper.getWritableDatabase();
        cursor = db.rawQuery("select * from " + GoalDatabaseHelper.TABLE_NAME,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(GoalDatabaseHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount());
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.i(ACTIVITY_NAME, cursor.getColumnName(i));
            }
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

    }
    public void create_goal(View view){
        Intent create_goal_intent = new Intent(SetGoalActivity.this,CreateGoalActivity.class);
        startActivity(create_goal_intent);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        db_helper.close();
        db.close();
    }

    private class GoalAdapter extends ArrayAdapter<String> {

        public GoalAdapter(Context context) {
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
            LayoutInflater inflater = SetGoalActivity.this.getLayoutInflater();
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

}