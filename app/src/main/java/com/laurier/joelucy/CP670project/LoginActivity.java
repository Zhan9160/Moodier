package com.laurier.joelucy.CP670project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tooltip.TooltipDrawable;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME="LoginActivity";
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarlogin);
        setSupportActionBar(toolbar);
        //toolbar.inflateMenu(R.menu.menu_main);
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startActivity(View view) {
        String file_name = getString(R.string.LoginName);
        SharedPreferences mPrefs = getSharedPreferences(file_name, MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mPrefs.edit();
        myEditor.clear();
        String email_key  = getString(R.string.LoginName);
        String new_email_entered = ((EditText) findViewById(R.id.editEmail))
                .getText().toString();
        myEditor.putString(email_key, new_email_entered);
        myEditor.commit();
        Intent mIntent = new Intent(LoginActivity.this,
                MainActivity.class);
        startActivity(mIntent);
    }

    public void loadData(){
        String file_name=getString(R.string.LoginName);
        SharedPreferences refs = getSharedPreferences(file_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = refs.edit();
        String  email_key = getString(R.string.LoginName);
        String new_email = refs.getString(email_key,"");
        ((EditText) findViewById(R.id.editEmail)).setText(new_email);
        myEdit.putString(email_key,new_email);
        myEdit.commit();

    }
    public void onOptionItemSelected(MenuItem item) {
        Toast toast = Toast.makeText(this , "Version 1.0 by Peiyu Lu & An Zhang", Toast.LENGTH_LONG); //this is the ListActivity
        toast.show(); //display your message box
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}