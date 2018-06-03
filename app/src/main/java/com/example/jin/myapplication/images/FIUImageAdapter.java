package com.example.jin.myapplication.images;

import android.app.VoiceInteractor;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.jin.myapplication.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jin on 2018/6/3.
 */

public class FIUImageAdapter extends BaseAdapter {

    private Context context;

    List<ImageInfo> list = new ArrayList<ImageInfo>();

    public FIUImageAdapter(Context context) {
        this.context = context;
        loadImages();
    }

    private void loadImages() {
        list.clear();
        getImages(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, list);
        getImages(MediaStore.Images.Media.INTERNAL_CONTENT_URI, list);
        Log.i(MainActivity.tag, "size:" + list.size());

    }

    private void getImages(Uri uri, List<ImageInfo> list) {
        String[] projection = new String[]{MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DESCRIPTION};
        Cursor externalCursor = MediaStore.Images.Media.query(context.getContentResolver(), uri, projection);
        if (externalCursor != null) {
            while (externalCursor.moveToNext()) {
                ImageInfo info = new ImageInfo();
                info.setTitle(externalCursor.getString(externalCursor.getColumnIndex(MediaStore.Images.Media.TITLE)));
                info.setDisplayName(externalCursor.getString(externalCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
                info.setSize(externalCursor.getString(externalCursor.getColumnIndex(MediaStore.Images.Media.SIZE)));
                info.setData(externalCursor.getString(externalCursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                info.setDescription(externalCursor.getString(externalCursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION)));
                list.add(info);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view;
        int widthAndHeight = (int)(getScreenWidth() / 3);
        if (convertView != null) {
            view = (ImageView) convertView;
        } else {
            view = new ImageView(context);

            ViewGroup.LayoutParams params = new AbsListView.LayoutParams(widthAndHeight, widthAndHeight);
            view.setLayoutParams(params);

        }
        view.setImageBitmap(getThumbnail(list.get(position).getData(), widthAndHeight, widthAndHeight));
        return view;
    }

    private Bitmap getThumbnail(String pathName, int width, int height) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, opts);
        int oriWidth = opts.outWidth;
        int oriHeight = opts.outHeight;
        opts.inSampleSize = opts.inSampleSize > oriHeight / height ? opts.inSampleSize : oriHeight / height;

        opts.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(pathName, opts);
        Bitmap result = Bitmap.createScaledBitmap(decodeFile, width, height, false);
        decodeFile.recycle();
        return result;

    }

    private float getScreenWidth() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    private float getScreenHeight() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }

}
