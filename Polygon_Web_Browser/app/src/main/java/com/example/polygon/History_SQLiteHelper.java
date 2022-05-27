package com.example.polygon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

public class History_SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "history.db";
    private static final String TABLE_NAME = "history_table";
    private static final String WEB_TITLE = "titleh";
    private static final String WEB_URL = "urlh";
    private static final String WEB_DATE = "dateh";
    private static final String IMAGE = "imageh";
//    private static final

    public History_SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }
//    private static final Image FAV_ICON ;



    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table "+TABLE_NAME+"("+WEB_TITLE+" text,"+WEB_URL+" text,"+IMAGE+" blob);");
        db.execSQL("create table "+TABLE_NAME+"("+WEB_TITLE+" text,"+WEB_URL+" text,"+WEB_DATE+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

//    public boolean insertData(String title, String url, byte[] image){
    public boolean insertData(String title, String url, String date){
        SQLiteDatabase myData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titleh",title);
        contentValues.put("urlh",url);
        contentValues.put("dateh",date);
//        contentValues.put("image",image);
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