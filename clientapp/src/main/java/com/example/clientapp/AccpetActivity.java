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
import android.widget.TextView;

public class AccpetActivity extends AppCompatActivity {


    private Uri myUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accpet);

        Intent intent = getIntent();
        if (intent != null && null != intent.getData()) {
            myUri = intent.getData();
        }
        //initSqlite(myUri);

    }

    public void initSqlite(Uri uri) {
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(myUri, null, null, null, null);
        if (cursor == null || cursor.getCount() < 2) {
            ContentValues values = new ContentValues();
            values.put("title", "test2");
            values.put("text", "justfortest2");
            resolver.insert(uri, values);
        }

        refresh();

    }

    private void refresh()
    {
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(myUri, null, null, null, null);
        TextView result = (TextView) findViewById(R.id.result);
        StringBuilder cursorResult = new StringBuilder("DB 查询结果：");

        while (cursor.moveToNext()) {
            String row = "id:" + cursor.getInt(cursor.getColumnIndex("_id")) + " "
                    + "title:" + cursor.getString(cursor.getColumnIndex("title"))
                    + "text:" + cursor.getString(cursor.getColumnIndex("text"));
            Log.d(MainActivity.TAG, "DB 查询结果：" + row);
            cursorResult.append("\n").append(row);
        }
        result.setText(cursorResult);
        cursor.close();
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
        cursor.close();

        refresh();

    }

    public void deleteNotes(View view) {
        ContentResolver resolver = this.getContentResolver();
        resolver.delete(myUri, null, null);
        refresh();

    }

    public void grantClientapp2(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.example.clientapp2", "com.example.clientapp2.MainActivity");
        Uri data = Uri.parse("content://com.example.jin.NotesContentProvider/notes");
        intent.setData(data);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivity(intent);
    }
}
