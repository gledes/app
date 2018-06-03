package com.example.jin.myapplication.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.sqlite.DatabaseHelper;

/**
 * Created by jin on 2018/6/3.
 */

public class NotesContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.jin.NotesContentProvider";

    private static final String TAG = "NoteContentProvider";

    private static final String DATABASE_NAME = "notes.db";

    private static final int DATABASE_VERSION = 1;

    private static final String NOTES_TABLE_NAME = "notes";

    private static final UriMatcher uriMatcher = null;

    private static final int NOTES = 1;

    private static final int NOTES_ID = 2;

    private DataBaseHepler dataBaseHelper;

    private static class DataBaseHepler extends SQLiteOpenHelper{

        public DataBaseHepler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + NOTES_TABLE_NAME + " (" + Note.Notes.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Note.Notes.TITLE + " VARCHER(255), " + Note.Notes.TEXT + " LONGTEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(MainActivity.tag, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        dataBaseHelper = new DataBaseHepler(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case NOTES:
                return Note.Notes.NOTES_TYPE;
            case NOTES_ID:
                return Note.Notes.NOTES_ID_TYPE;
            default:
                throw new IllegalArgumentException("unknown URI:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues initialValues) {
        if (uriMatcher.match(uri) != NOTES) {
            throw new IllegalArgumentException("unknown URI:" + uri);
        }
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        long rowId = db.insert(NOTES_TABLE_NAME, Note.Notes.TEXT, values);
        if (rowId > 0) {
            Uri noteUri = ContentUris.withAppendedId(Note.Notes.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }

        throw new SQLException("failed to insert row into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
