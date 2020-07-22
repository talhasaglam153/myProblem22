package com.tcoding.bugunkasoruzdn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dateDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbDate";
    private static final String TABLE_NAME = "tbDate";
    private static final String COL1 = "ID";
    private static final String COL2 = "dateMonth";
    private static final String COL3 = "dateDay";
    private Context context;



    public dateDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + TABLE_NAME + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT , " +
                COL3 + " TEXT ); ";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    void AddDate(String dateMonth, String dateDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, dateMonth);
        cv.put(COL3, dateDay);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Tarih Eklenemedi ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Tarih Başarıyla Eklendi...", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor ShowData() {
        String query = "Select *from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(query, null);
        }
        return c;
    }

    void DeleteChoose(String gelenId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID=?", new String[]{gelenId});
        db.close();
    }

    void DeleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from " + TABLE_NAME);
        db.close();
    }


}
