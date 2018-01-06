package com.example.jin.myapplication.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.R;

public class SQLiteActivity extends Activity {

    private Button createDatabase;

    private Button updateDatabase;

    private Button insert;

    private Button update;

    private Button query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        createDatabase = (Button)findViewById(R.id.createDatabase);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "test_db");
                SQLiteDatabase db = dbHelper.getReadableDatabase();
            }
        });

        updateDatabase = (Button)findViewById(R.id.updateDatabase);
        updateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "test_db", 3);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
            }
        });

        insert = (Button)findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("id", 1);
                values.put("name", "zhangsan");
                DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "test_db", 3);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.insert("user", null, values);
            }
        });

        update = (Button)findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("name", "李四");
                DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "test_db", 3);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.update("user", values, "id=?", new String[]{"1"});

            }
        });

        query = (Button)findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(SQLiteActivity.this, "test_db", 3);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query("user", new String[]{"id", "name"}, "id=?", new String[]{"1"}, null, null, null);
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                    Log.i(MainActivity.tag, "id:" + id + "  name:" + name);
                }
            }
        });

    }
}
