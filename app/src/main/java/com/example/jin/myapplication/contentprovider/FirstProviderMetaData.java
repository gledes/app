package com.example.jin.myapplication.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jin on 2018/1/7.
 */

public class FirstProviderMetaData {

    public static final String AUTHORIY = "jin.cp.FirstContentProvider";

    public static final String DATABASE_NAME = "FirstProvider.db";

    public static final int DATABASE_VERSION = 1;

    public static final String USER_TABLE_NAME = "users";

    public static final class UserTableMetaData implements BaseColumns {
        public static final String TABLE_NAME = "users";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORIY + "/users");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.firstprovider.user";

        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.firstprovider.user";

        public static final String USER_NAME = "name";

        public static final String DEFAULT_SORT_ORDER = "_id desc";

    }
}
