package com.laurier.joelucy.CP670project;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu m)  {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
//        switch (item_id) {
//            case R.id.action_one:
//                Log.i("Toolbar", "Option 1 selected");
//                //Start an activity
//                //snackbar= snackbar.make(view,message, LENGTH_LONG); //this is the ListActivity
//                //snackbar.show(); //display your message box
//                Toast toast = Toast.makeText(this, "Application by Peiyu Lu and An Zhang", Toast.LENGTH_LONG);
//                toast.show();
//        }
        return true;
    }

    public void onOptionItemSelected(MenuItem item) {
        Toast toast = Toast.makeText(this , "Version 1.0 by Peiyu Lu", Toast.LENGTH_LONG); //this is the ListActivity
        toast.show(); //display your message box
    }

}