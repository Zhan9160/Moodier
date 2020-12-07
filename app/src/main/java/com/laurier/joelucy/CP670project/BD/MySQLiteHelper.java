package com.laurier.joelucy.CP670project.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    //define table Goallog
    public static final String TABLE_GoalLog = "GoalLog";
    public  static final String Goal_ID = "ID";
    public  static  final String Goal_UID = "UserID";
    public static final String Goal_CID = "CategoryID";
    public static final String Goal_Text = "Content";
    public static final String Goal_CreateOn = "CreateOn";

    //define table Moodlog
    public static final String TABLE_MoodLog = "MoodLog";
    public  static final String Mood_ID = "ID";
    public  static  final String Mood_UID = "UserID";
    public static final String Mode_Type = "ModeType";
    public static final String Mood_Text = "Content";
    public static final String Mood_CreateOn = "CreateOn";

    public static final String DATABASE_NAME = "Moodier.db";
    private static final int DATABASE_VERSION = 1;

    // Table/Database creation statement
    private static final String CREATE_TableGoal = "create table "
            + TABLE_GoalLog + "(" + Goal_ID
            + " integer primary key autoincrement, " + Goal_UID
            + " integer, " + Goal_CID
            + " integer, " + Goal_Text
            + " text, " + Goal_CreateOn
            + " text not null);";

    private static  final String CREATE_TableMood = "create table "
            + TABLE_MoodLog + "(" + Mood_ID
            + " integer primary key autoincrement, " + Mood_UID
            + " integer, " + Mode_Type
            + " integer, " + Mood_Text
            + " text, " + Mood_CreateOn
            + " text not null);";

    // super constructor call
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TableGoal);
        db.execSQL(CREATE_TableMood);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GoalLog);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MoodLog);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        Log.w(SQLiteOpenHelper.class.getName(),
                "Downgrading database from version " + newVersion + " to "
                        + oldVersion);*/
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GoalLog);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MoodLog);
        onCreate(db);
    }

    public long count(SQLiteDatabase database, String tableName) {
        long count = 0;
        database.execSQL("select count(*) " + " from " + tableName);
        return count;
    }


    //public static
}
