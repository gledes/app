package com.example.clientapp2;

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

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    static {
        System.loadLibrary("native-lib");
        //init();
        //init1();
    }

    private Uri myUri = Uri.parse("content://com.example.jin.NotesContentProvider/notes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent != null && null != intent.getData()) {
            myUri = intent.getData();
        }
        TextView tv = findViewById(R.id.result);
        tv.setText(jniFormC());
//        refresh();
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

        refresh(null);

    }

    public void refresh(View view)
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
        refresh(null);

    }

    public void deleteNotes(View view) {
        ContentResolver resolver = this.getContentResolver();
        resolver.delete(myUri, null, null);
        refresh(null);

    }

    public void andfix(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AndFixActivity.class);
        startActivity(intent);
    }

    public native void test1();

    public native String jniFormC();
}
