package com.laurier.joelucy.CP670project.ui.log;

import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.laurier.joelucy.CP670project.BD.LogItem;
import com.laurier.joelucy.CP670project.BD.MySQLiteHelper;
import com.laurier.joelucy.CP670project.MoodDatabaseHelper;
import com.laurier.joelucy.CP670project.R;

import java.util.ArrayList;
import java.util.List;

public class MylogFragment extends Fragment {

    private MylogViewModel mViewModel;
    private SQLiteDatabase database;
    private MySQLiteHelper dbOpenHelper;
    private List<LogItem> logItems = null;
    private String[] moodColumns =
            {MySQLiteHelper.Mood_ID,
                    MySQLiteHelper.Mode_Type, MySQLiteHelper.Mood_UID,MySQLiteHelper.Mood_CreateOn, MySQLiteHelper.Mood_Text};
    private String[] goalColumns =
            {MySQLiteHelper.Goal_ID,
                    MySQLiteHelper.Goal_CID, MySQLiteHelper.Goal_UID, MySQLiteHelper.Goal_CreateOn, MySQLiteHelper.Goal_Text};

    private View rView;
    public static MylogFragment newInstance() {
        return new MylogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //initList();
        rView= inflater.inflate(R.layout.mylog_fragment, container, false);

/*        displaybtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView textView =  (TextView) v.findViewById(R.id.txtDate);
               textView.setText("2001-1-1");
            }
        });*/
        return rView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(MylogViewModel.class);
        registerEvents();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

    }

    private void registerEvents()
    {
        Button displaybtn = (Button)getActivity().findViewById(R.id.btnDisplayall);
        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) getActivity().findViewById(R.id.txtDate);
                textView.setText("all");
            }
        });

        Button displayGoal = (Button)getActivity().findViewById(R.id.btnDisplayGoals);
        displayGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) getActivity().findViewById(R.id.txtDate);
                textView.setText("goal");
            }
        });


        Button displayMood = (Button)getActivity().findViewById(R.id.btnDisplayMoods);
        displayMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) getActivity().findViewById(R.id.txtDate);
                textView.setText("mood");
            }
        });
    }
    private List<LogItem> getAllItems()
    {
        dbOpenHelper = new MySQLiteHelper(getActivity());
        dbOpenHelper.getReadableDatabase();
        List<LogItem> items = new ArrayList<>();
        //Goal
        Cursor cursor  =database.query(MySQLiteHelper.TABLE_GoalLog, goalColumns, null,null,null,null,
                MySQLiteHelper.Goal_CreateOn+ " desc");
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            LogItem item = new LogItem(cursor.getLong(0), cursor.getString(4), 1);
            items.add(item);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        // mood
        cursor  =database.query(MySQLiteHelper.TABLE_MoodLog, moodColumns, null,null,null,null,
                MySQLiteHelper.Mood_CreateOn+ " desc");
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            LogItem item2 = new LogItem(cursor.getLong(0), cursor.getString(4), 1);
            items.add(item2);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();

        return  items;

    }

    private void initList() {
        List<LogItem> items = getAllItems();

    }



}