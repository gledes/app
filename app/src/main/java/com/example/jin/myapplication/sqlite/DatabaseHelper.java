package com.example.jin.myapplication.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jin.myapplication.MainActivity;

/**
 * Created by jin on 2018/1/6.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int VERSION = 1;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, VERSION);
    }

    public DatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public DatabaseHelper(Context context, String name) {
        this(context, name, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(MainActivity.tag, "create a Database");
//        db.execSQL("create table user(id int, name varcher(20))");
        db.execSQL("create table users(_id INTEGER PRIMARY KEY AUTOINCREMENT, name varcher(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(MainActivity.tag, "update a Database");
    }
}
