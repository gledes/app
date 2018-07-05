package com.example.clientapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AccpetActivity extends AppCompatActivity {


    private ContentResolver resolver;

    private Uri myUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accpet);

        Intent intent = getIntent();
        if (intent != null && null != intent.getData()) {
            myUri = intent.getData();
        }
        initSqlite(myUri);

    }

    public void initSqlite(Uri uri) {
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor == null || cursor.getCount() < 2) {
            ContentValues values = new ContentValues();
            values.put("title", "test2");
            values.put("text", "justfortest2");
            resolver.insert(uri, values);
        } else {
            while (cursor.moveToNext()) {
                Log.i(MainActivity.TAG, "id:" + cursor.getInt(cursor.getColumnIndex("_id")) + " "
                        + "title:" + cursor.getString(cursor.getColumnIndex("title"))
                        + "text:" + cursor.getString(cursor.getColumnIndex("text")));
            }
        }
    }

    public void insertNotes(View view) {
        //        Uri myUri = Uri.parse("content://com.example.notes.providers.NotesContentProvider/notes/1");
        // 此处无论如何不要使用固定的uri，只能使用从intent传入的，即使是完全相同也会报无权限
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(myUri, null, null, null, null);
        if (cursor == null || cursor.getCount() < 10) {
            ContentValues values = new ContentValues();
            values.put("title", "zhuxian");
            values.put("text", "goodbook");
            resolver.insert(myUri, values);
        }

    }
}
