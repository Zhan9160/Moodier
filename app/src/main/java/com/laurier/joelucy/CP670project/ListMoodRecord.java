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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListMoodRecord extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "ListMoodRecord";
    private EditText textInput;
    Button submit;
    private ArrayList<String> list; //store mood message detail
    SQLiteDatabase db;
    MoodDatabaseHelper db_helper;
    Cursor cursor;
    TextView text_feel;
    MoodAdapter messageAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mood_record);

        listView = findViewById(R.id.recyclerView_moodRecord);
        list = new ArrayList<>();
//        Toast.makeText(ListMoodRecord.this, ">>>>>",
//                Toast.LENGTH_SHORT).show();

        messageAdapter =new MoodAdapter( this );
        listView.setAdapter(messageAdapter);

        LayoutInflater factory = LayoutInflater.from(ListMoodRecord.this);
        View layout = factory.inflate(R.layout.activity_write_mood, null);
//        TextView textInput = (TextView) layout.findViewById(R.id.write_mood);
        textInput = (EditText)layout.findViewById(R.id.write_mood);
        submit = (Button)layout.findViewById(R.id.submit_new_mood);

//        LayoutInflater log_mood_activity = LayoutInflater.from(ListMoodRecord.this);
//        View log_mood_layout = factory.inflate(R.layout.activity_log_mood, null);
//        text_feel = (TextView)log_mood_layout.findViewById(R.id.mood_value);

//        Toast.makeText(ListMoodRecord.this, textInput.getText(),
//                Toast.LENGTH_SHORT).show();

        //textInput = findViewById(R.id.write_mood);
       // Log.i(ACTIVITY_NAME,"" + textInput);
        //Button sendButton = findViewById(R.id.sendButton);
        //findViewById(R.id.submit_new_mood).setOnClickListener(new View.OnClickListener(){
        submit.setOnClickListener(new View.OnClickListener(){
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
        cursor = db.rawQuery("select * from " + MoodDatabaseHelper.TABLE_NAME,null);
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
        //cursor.close();
    }
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db_helper.close();
        db.close();
    }


    public void return_to_main(View v){
        Intent intent_to_main = new Intent(ListMoodRecord.this,MainActivity.class);
        startActivity(intent_to_main);
    }

    public void clear_record(View v){
        listView.setAdapter(null);
//        SQLiteDatabase.execSQL("DELETE FROM CUSTOMERS");
        db_helper.onUpgrade(db,4,5);

//        if (list.size() > 0) {
//            //list.removeAll(list);
//            list.clear();
//            messageAdapter.notifyDataSetChanged();
//            listView.setAdapter(messageAdapter);
//
//
//        }
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