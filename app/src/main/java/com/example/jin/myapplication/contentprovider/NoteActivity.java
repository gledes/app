package com.example.jin.myapplication.contentprovider;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jin.myapplication.R;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_ACTION_VIEW = "notes.intent.action.NOTE_VIEW";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    public void GrantPermissionForTest(View view) {
        Uri uri = Uri.parse("content://com.example.jin.NotesContentProvider/notes");
        Intent intent = new Intent();
        intent.setAction(NOTE_ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
