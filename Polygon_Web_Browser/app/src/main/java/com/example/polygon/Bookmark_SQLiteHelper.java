package com.example.polygon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

public class Bookmark_SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bookmark.db";
    private static final String TABLE_NAME = "bookmark_table";
    private static final String WEB_TITLE = "title";
    private static final String WEB_URL = "url";
    private static final String IMAGE = "image";
//    private static final

    public Bookmark_SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
//    private static final Image FAV_ICON ;



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+WEB_TITLE+" text,"+WEB_URL+" text,"+IMAGE+" blob);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String url, byte[] image){
        SQLiteDatabase myData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("url",url);
        contentValues.put("image",image);
        long insert = myData.insert(TABLE_NAME,null,contentValues);
        if(insert==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getCursor(){
        SQLiteDatabase myData = this.getWritableDatabase();
        Cursor cursor = myData.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

//    public String getTitle(){
//        SQLiteDatabase myData = this.getWritableDatabase();
//        Cursor cursor = myData.rawQuery("select * from "+TABLE_NAME+" where "+WEB_TITLE+"=?",new String[]{WEB_TITLE});
//        cursor.moveToFirst();
//        return cursor.getString(0);
//    }
//    public String getUrl(){
//        SQLiteDatabase myData = this.getWritableDatabase();
//        Cursor cursor = myData.rawQuery("select * from "+TABLE_NAME+" where "+WEB_URL+"=?",new String[]{WEB_URL});
//        cursor.moveToFirst();
//        return cursor.getString(1);
//    }
//    public Bitmap getFavicon(){
//        SQLiteDatabase myData = this.getWritableDatabase();
//        Cursor cursor = myData.rawQuery("select * from "+TABLE_NAME+" where "+IMAGE+"=?",new String[]{IMAGE});
//        cursor.moveToFirst();
//        byte[] bitmap = cursor.getBlob(2);
//        return BitmapFactory.decodeByteArray(bitmap,0, bitmap.length);
//    }
}
