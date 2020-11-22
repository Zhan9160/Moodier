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

public class ListMoodRecord extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "ListMoodRecord";
    private EditText textInput;
    private ArrayList<String> list; //store mood message detail
    SQLiteDatabase db;
    MoodDatabaseHelper db_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mood_record);

        ListView listView = findViewById(R.id.recyclerView_moodRecord);
        list = new ArrayList<>();

        final MoodAdapter messageAdapter =new MoodAdapter( this );
        listView.setAdapter (messageAdapter);
        textInput = findViewById(R.id.write_mood);
        //Button sendButton = findViewById(R.id.sendButton);
        findViewById(R.id.submit_new_mood).setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String message = textInput.getText().toString();
                list.add(message);
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                textInput.setText("");
                //write
                ContentValues cv = new ContentValues();
                cv.put("message",message);
                db.insert(MoodDatabaseHelper.TABLE_NAME,null,cv);

            }
        });
        db_helper = new MoodDatabaseHelper(this);
        //gets a writeable database
        db = db_helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + MoodDatabaseHelper.TABLE_NAME,null);
        cursor.moveToFirst();
        //reading from db file
        while (!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(MoodDatabaseHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount());
            //print out the name of each column returned by the cursor
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.i(ACTIVITY_NAME, cursor.getColumnName(i));
            }
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void return_to_main(View v){
        Intent intent_to_main = new Intent(ListMoodRecord.this,LogMoodActivity.class);
        startActivity(intent_to_main);
    }

    private class MoodAdapter extends ArrayAdapter<String> {
    public MoodAdapter(Context context) {
        super(context,0);
    }

    public MoodAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public int getCount(){
        return list.size();
    }

    public String getItem(int position){
        return (String) list.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = ListMoodRecord.this.getLayoutInflater();
        View result = null ;
        if(position%2 == 0)
            result = inflater.inflate(R.layout.first_row, null);
        else
            result = inflater.inflate(R.layout.second_row, null);
        TextView message = (TextView)result.findViewById(R.id.message_text);
        message.setText(getItem(position)); // get the string at position
        return result;
    }
}
}