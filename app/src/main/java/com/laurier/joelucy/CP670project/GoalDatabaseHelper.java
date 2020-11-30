package com.laurier.joelucy.CP670project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoalDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Goals.db";
    private static final int VERSION_NUM = 3 ;
    public static final String TABLE_NAME = "message";
    public static final String KEY_ID = "ID" ;
    public static final String KEY_MESSAGE = "message" ;

    public GoalDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ("+ KEY_ID +" integer primary key autoincrement," + KEY_MESSAGE + " varchar(256))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //recreate database
        db.execSQL(sql);
        onCreate(db);
    }
}
