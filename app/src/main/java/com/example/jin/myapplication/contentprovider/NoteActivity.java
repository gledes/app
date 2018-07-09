package com.example.jin.myapplication.contentprovider;

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

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.R;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_ACTION_VIEW = "notes.intent.action.NOTE_VIEW";

    private Uri myUri = Uri.parse("content://com.example.jin.NotesContentProvider/notes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
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
            Log.d(MainActivity.tag, "DB 查询结果：" + row);
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

    public void GrantPermissionForTest(View view) {
        Uri uri = Uri.parse("content://com.example.jin.NotesContentProvider/notes");
        Intent intent = new Intent();
        intent.setAction(NOTE_ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setData(uri);
        startActivity(intent);

    }
}
