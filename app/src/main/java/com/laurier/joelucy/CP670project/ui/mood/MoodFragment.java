package com.laurier.joelucy.CP670project.ui.mood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.laurier.joelucy.CP670project.LogMoodActivity;
import com.laurier.joelucy.CP670project.MainActivity;
import com.laurier.joelucy.CP670project.R;
import com.laurier.joelucy.CP670project.WriteMood;

public class MoodFragment extends Fragment {

    private MoodViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(MoodViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mood, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
//        View.OnClickListener listener = new
//        root.findViewById(R.id.set_mood_button)

        return root;
    }
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.set_mood_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log_mood_intent = new Intent(getActivity(), WriteMood.class);
                startActivity(log_mood_intent);
//                Log.i(ACTIVITY_NAME, "In onCreate()");
            }
        });

    }

}