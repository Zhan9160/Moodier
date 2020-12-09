package com.laurier.joelucy.CP670project.ui.mood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.laurier.joelucy.CP670project.ListMoodRecord;
import com.laurier.joelucy.CP670project.LogMoodActivity;
import com.laurier.joelucy.CP670project.MainActivity;
import com.laurier.joelucy.CP670project.R;
import com.laurier.joelucy.CP670project.WriteMood;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MoodFragment extends Fragment {

    private MoodViewModel notificationsViewModel;
    String result ="";
    int resultType;
    ListView listView;
    private ArrayList<String> list;
    private  static final int Moodoptimistic = 1;
    private  static final int MoodNegetive = 2;
    private  static final int Moodill = 3;
    private int[] images = { R.drawable.m1, R.drawable.m2, R.drawable.m3,};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mood, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
/*        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
//        View.OnClickListener listener = new
//        root.findViewById(R.id.set_mood_button)
/*        Intent intent =getActivity().getIntent();
        result = intent.getStringExtra("result");
        TextView tv = root.findViewById(R.id.recyclerView_moodResult);
        tv.setText(result);*/

//        listView = root.findViewById(R.id.recyclerView_moodRecord);
//        list = new ArrayList<>();
//
//        messageAdapter =new ListMoodRecord.MoodAdapter( this );
//        listView.setAdapter(messageAdapter);
//        View view = root.findViewById(R.id.recyclerView_moodResult);
//        listView.setAdapter(messageAdapter);

        return root;
    }
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        RegisterEvents();
        InitData();

    }

    private void InitData() {
        getMoodTestResult();
        TextView textView = (TextView)getActivity().findViewById(R.id.recyclerView_moodResult);
        ImageView imageView = (ImageView)getActivity().findViewById(R.id.imageViewMood);
        if (result.isEmpty())
        {

            textView.setText("You have not do any mood test. Please test on the home page");
        }
        else
        {
            switch (resultType)
            {
                case Moodoptimistic:
                    imageView.setImageResource(images[0]);
                    break;
                case MoodNegetive:
                    imageView.setImageResource(images[1]);
                    break;
                case Moodill:
                    imageView.setImageResource(images[2]);
                  break;
            }
            textView.setText(result);
        }
    }

    private void RegisterEvents() {


    }

    private  void getMoodTestResult()
    {
        SharedPreferences preferences=getActivity().getSharedPreferences("sharedata", Context.MODE_PRIVATE);
        result = preferences.getString("testMoodResult","");
        resultType = preferences.getInt("testMoodResultType", 0);
    }


}