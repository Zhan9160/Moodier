package com.laurier.joelucy.CP670project.ui.log;

import androidx.lifecycle.ViewModelProvider;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.laurier.joelucy.CP670project.BD.LogItem;
import com.laurier.joelucy.CP670project.BD.MySQLiteHelper;
import com.laurier.joelucy.CP670project.MoodDatabaseHelper;
import com.laurier.joelucy.CP670project.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    String strDate="";
    long btnClicktype=0;
    private final static long allItems = 0;
    private final static long goalItems = 1;
    private final static long moodItems = 2;
    private ArrayAdapter <LogItem> mAdapter;
    private ArrayAdapter<String> testAdapter;
    private View rView;
    public static MylogFragment newInstance() {
        return new MylogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //initList();
        rView= inflater.inflate(R.layout.mylog_fragment, container, false);
        /*try {
            initList(strDate,btnClicktype);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return rView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(MylogViewModel.class);
        registerEvents();
        try {
            initList(strDate,btnClicktype);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void registerEvents()
    {
        CalendarView cv = (CalendarView)rView.findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                TextView textView = (TextView) rView.findViewById(R.id.txtDate);
                strDate = year+"-"+month+"-"+dayOfMonth;
                textView.setText(strDate);

                try {
                    initList(strDate, btnClicktype);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        Button displaybtn = (Button)rView.findViewById(R.id.btnDisplayall);
        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClicktype = allItems;
                try {
                    //Resources resources=getContext().getResources();
                    //Drawable drawable=resources.getDrawable(R.drawable.buttonshape2);
                    TextView textView = (TextView) rView.findViewById(R.id.txtDate);
                    textView.setBackgroundResource(R.drawable.buttonshape2);
                    initList(strDate,btnClicktype);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        Button displayGoal = (Button)rView.findViewById(R.id.btnDisplayGoals);
        displayGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClicktype = goalItems;
                try {
                    TextView textView = (TextView) rView.findViewById(R.id.txtDate);
                    textView.setBackgroundResource(R.drawable.buttonshape_down);
                    initList(strDate,btnClicktype);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


        Button displayMood = (Button)rView.findViewById(R.id.btnDisplayMoods);
        displayMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClicktype = moodItems;
                try {
                    TextView textView = (TextView) rView.findViewById(R.id.txtDate);
                    textView.setBackgroundResource(R.drawable.buttonshape);
                    initList(strDate,btnClicktype);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private List<LogItem> getAllItems(long itemType)
    {
        dbOpenHelper = new MySQLiteHelper(getActivity());
        dbOpenHelper.getReadableDatabase();
        List<LogItem> items = new ArrayList<>();
        Cursor cursor;
        //Goal
        if (itemType == goalItems || itemType == allItems) {
            cursor = database.query(MySQLiteHelper.TABLE_GoalLog, goalColumns, null, null, null, null,
                    MySQLiteHelper.Goal_CreateOn + " desc");
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                LogItem item = new LogItem(cursor.getLong(0), cursor.getString(4), 1, cursor.getString(3));
                items.add(item);
                cursor.moveToNext();
            }
            // Make sure to close the cursor
            cursor.close();
        }


        // mood
        if (itemType == moodItems || itemType == allItems) {
            cursor = database.query(MySQLiteHelper.TABLE_MoodLog, moodColumns, null, null, null, null,
                    MySQLiteHelper.Mood_CreateOn + " desc");
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                LogItem item2 = new LogItem(cursor.getLong(0), cursor.getString(4), 1, cursor.getString(3));
                items.add(item2);
                cursor.moveToNext();
            }
            // Make sure to close the cursor
            cursor.close();
        }
        return  items;

    }
    private List<LogItem> filterbyDate(List<LogItem> listItems, String stringDate) throws ParseException {
        List<LogItem> values = new ArrayList<>();
        String formatType = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(stringDate);

        for(int i=0;i<listItems.size();i++)
        {
            if(formatter.parse(listItems.get(i).CreateOn) == date)
            {
                values.add(listItems.get(i));
            }
        }

        return values;
    }

    private void initList(String selectDate, long logType) throws ParseException {
        /*List<LogItem> values;

        if(selectDate.isEmpty())
        {
            values = getAllItems(logType);
        }
        else
        {
            values = getAllItems(logType);
            values = filterbyDate(values, selectDate);
        }


        mAdapter= new ArrayAdapter <LogItem>(getActivity(), R.layout.mylog_fragment, values);*/

        String[] listdata = new String[]{"image", "title", "date", "time"};
        if (selectDate.isEmpty())
        {
            listdata = new String[]{Long.toString(logType), "title", "date", "time"};
        }
        else
        {
            listdata = new String[]{selectDate, Long.toString(logType), "title", "date", "time"};
        }

        testAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listdata);
        try {
            ListView listView = (ListView)rView.findViewById(R.id.lstLog);
            listView.setAdapter(testAdapter);
        }
        catch (Exception e)
        {
            Log.i("listview exception", e.getMessage());
        }


    }



}