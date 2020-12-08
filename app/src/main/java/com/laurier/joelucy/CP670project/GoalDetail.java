package com.laurier.joelucy.CP670project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GoalDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);


        long id = getIntent().getLongExtra("ID", 0);
        String message = getIntent().getStringExtra("Content");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, new MessageFragment(id, message,false));
        ft.commit();
    }

    public static class MessageFragment extends Fragment {
        long id;
        String message;
        SetGoalActivity chatWindow;
        boolean is_exist;

        public MessageFragment(long id, String message, boolean is_exist) {
            this.id = id;
            this.message = message;
            this.is_exist = is_exist;
        }

        public MessageFragment(long id, String message, boolean is_exist, SetGoalActivity chatWindow) {
            this.id = id;
            this.is_exist = is_exist;
            this.message = message;
            this.chatWindow = chatWindow;
        }

        @SuppressLint("SetTextI18n")
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_goal, container, false);

            //view.findViewById(R.id.show_id).setText(id);
            TextView tvId = view.findViewById(R.id.show_id);
            //findViewById(R.id.show_id).setText
            TextView tvMsg = view.findViewById(R.id.show_message);
            tvId.setText("show id: " + id);
            tvMsg.setText("show message:\n" + message);

            Button btnDelete = view.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (is_exist) {
                        chatWindow.remove_fragment();//if message exist, delete
                    } else {
                        Intent intent = new Intent();//click empty
                        intent.putExtra("id", id);
                        getActivity().setResult(RESULT_OK, intent);
                        getActivity().finish();
                    }
                }
            });
            view.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),SetGoalActivity.class);//click empty
                    //intent.putExtra("id", id);
                    getActivity().finish();
                }
            });
            return view;
        }
    }
}
