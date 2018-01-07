package com.example.jin.myapplication.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.sqlite.DatabaseHelper;

import java.util.HashMap;

/**
 * Created by jin on 2018/1/7.
 */

public class FirstContentProvider extends ContentProvider {

    public static final UriMatcher uriMatcher;

    public static final int USER_COLLECTION = 1;

    public static final int USER_SINGLE = 2;

    private DatabaseHelper databaseHelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FirstProviderMetaData.AUTHORIY, "/users", USER_COLLECTION);
        uriMatcher.addURI(FirstProviderMetaData.AUTHORIY, "/users/#", USER_SINGLE);


    }

    public static HashMap<String, String> userProjectionMap;
    static {
        userProjectionMap = new HashMap<String, String>();
        userProjectionMap.put(FirstProviderMetaData.UserTableMetaData._ID, FirstProviderMetaData.UserTableMetaData._ID);
        userProjectionMap.put(FirstProviderMetaData.UserTableMetaData.USER_NAME, FirstProviderMetaData.UserTableMetaData.USER_NAME);

    }

    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext(), FirstProviderMetaData.DATABASE_NAME);
        Log.i(MainActivity.tag, "onCreate");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.i(MainActivity.tag, "getType");

        switch (uriMatcher.match(uri)) {
            case USER_COLLECTION:
                return FirstProviderMetaData.UserTableMetaData.CONTENT_TYPE;
            case USER_SINGLE:
                return FirstProviderMetaData.UserTableMetaData.CONTENT_TYPE_ITEM;
            default:
                throw new RuntimeException("unknow uri");

        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.i(MainActivity.tag, "insert");
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long rowId = db.insert(FirstProviderMetaData.UserTableMetaData.TABLE_NAME, null, values);
        if (rowId > 0) {
            Uri insertUserUri = ContentUris.withAppendedId(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(insertUserUri, null);
            return insertUserUri;
        }

        throw new SQLException("Faild to insert row into " + uri);
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
