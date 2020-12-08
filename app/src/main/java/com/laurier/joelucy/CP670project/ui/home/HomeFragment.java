package com.laurier.joelucy.CP670project.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.laurier.joelucy.CP670project.BD.MySQLiteHelper;
import com.laurier.joelucy.CP670project.GoalDatabaseHelper;
import com.laurier.joelucy.CP670project.ListMoodRecord;
import com.laurier.joelucy.CP670project.MainActivity;
import com.laurier.joelucy.CP670project.MoodDatabaseHelper;
import com.laurier.joelucy.CP670project.R;
import com.laurier.joelucy.CP670project.WriteMood;
import com.laurier.joelucy.CP670project.ui.log.MylogFragment;

import java.util.ArrayList;
import java.util.SimpleTimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View root;
    ArrayList<ImageView> imageViews= new ArrayList<>();
    ArrayList<View>dots=new ArrayList<>();
    ViewPager vp;
    ViewPagerAdapter adapter;
    int[] image = {R.drawable.main1, R.drawable.main1, R.drawable.main1, R.drawable.main1};
    int oldPosition=0;
    int currentItem;

    ScheduledExecutorService scheduledExecutorService;
    private ArrayList<Object> list;
    GoalAdapter messageAdapter;
    MySQLiteHelper db_helper;
    SQLiteDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        root.findViewById(R.id.user_information_button).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
//                Intent mylog_intent = new Intent(getActivity(), MylogFragment.newInstance().getClass());
//                startActivity(mylog_intent);
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                Fragment fragment = new MylogFragment();
//                fm.beginTransaction().replace(R.id.id_change,fragment).commit();
            }
        });

        setView();
        getTopGoal();

        return root;
    }
    public void getTopGoal(){
        TextView t1 = root.findViewById(R.id.top_goal1);
        TextView t2 = root.findViewById(R.id.top_goal2);
        TextView t3 = root.findViewById(R.id.top_goal3);
//        t1.setText("!@#");
        list = new ArrayList<>();
        messageAdapter =new GoalAdapter( getActivity() );

        LayoutInflater factory = LayoutInflater.from(getActivity());
        View layout = factory.inflate(R.layout.activity_create_new_goal, null);
//
        Button submit = (Button)layout.findViewById(R.id.button6);
        final EditText text = (EditText)layout.findViewById(R.id.editTextTextMultiLine2);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String message = text.getText().toString();
                list.add(message);
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                text.setText("");
                //write
                ContentValues cv = new ContentValues();
                cv.put(MySQLiteHelper.Goal_Text,message);
                cv.put(MySQLiteHelper.Goal_CreateOn, "");
                db.insert(MySQLiteHelper.TABLE_GoalLog,null,cv);
//
            }
        });
        db_helper = new MySQLiteHelper(getActivity());
        db = db_helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + MySQLiteHelper.TABLE_GoalLog,null);
        cursor.moveToFirst();
        //reading from db file
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
//        GoalDatabaseHelper dbHelper = new GoalDatabaseHelper(getActivity());
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor c = db.query(MySQLiteHelper.TABLE_GoalLog, null, null, null, null, null, null);

        if(c.moveToLast()==true) {
            //c.moveToLast();
            t1.setText(c.getString(c.getColumnIndex(MySQLiteHelper.Goal_Text)));
        }
        if (c.moveToPrevious()==true){
            //c.moveToPrevious();
            t2.setText(c.getString(c.getColumnIndex(MySQLiteHelper.Goal_Text)));
        }
        if (c.moveToPrevious()==true){
            t3.setText(c.getString(c.getColumnIndex(MySQLiteHelper.Goal_Text)));
        }
    }
    public void setView() {
        vp = (ViewPager)root.findViewById(R.id.viewContent);
        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(getActivity());//(this);
            imageView.setImageResource(image[i]);
            imageViews.add(imageView);
        }

        dots.add(root.findViewById(R.id.p1));
        dots.add(root.findViewById(R.id.p2));
        dots.add(root.findViewById(R.id.p3));
        dots.add(root.findViewById(R.id.p4));

        adapter = new ViewPagerAdapter();
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                dots.get(position).setBackgroundResource(R.drawable.dot_focus);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v=imageViews.get(position);
            container.removeView(v);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View v=imageViews.get(position);
            container.addView(v);
            return v;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),2,2, TimeUnit.SECONDS);
    }
    class ViewPagerTask implements Runnable{

        @Override
        public void run() {
            currentItem=(currentItem+1)%image.length;
            handler.obtainMessage().sendToTarget();
        }
    }
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            vp.setCurrentItem(currentItem);
        }
    };

    private class GoalAdapter extends ArrayAdapter<String> {
        public GoalAdapter(Context context) {
            super(context,0);
        }

        public GoalAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }

        public int getCount(){
            return list.size();
        }

        public String getItem(int position){
            return (String) list.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = getActivity().getLayoutInflater();
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