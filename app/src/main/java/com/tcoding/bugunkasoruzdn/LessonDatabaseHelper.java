package com.tcoding.bugunkasoruzdn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class LessonDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Sorular";
    private static final String TABLE_NAME = "Sorularim";
    private static final String COL1 = "ID";
    private static final String COL2 = "Ders_Adi";
    private static final String COL3 = "Ders_Konusu";
    private static final String COL4 = "Soru_Sayisi";
    private static final String COL5 = "Date_Id";




    public LessonDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateSentences = "CREATE TABLE " + TABLE_NAME + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT ," +
                COL3 + " TEXT ," +
                COL4 + " INTEGER ," +
                COL5 + " INTEGER );";
        db.execSQL(CreateSentences);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    void AddLesson(String gelenDersAd, String gelenDersKonu, String gelenSolvedProblemCount, String gelenDateId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, gelenDersAd);
        cv.put(COL3, gelenDersKonu);
        cv.put(COL4, gelenSolvedProblemCount);
        cv.put(COL5, gelenDateId);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Warning!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(context, "Succesfully", Toast.LENGTH_SHORT).show();
    }

    Cursor ViewAllData(String gelenId){
        String query = "SELECT *FROM  "+ TABLE_NAME +" WHERE "+ COL5 +"="+gelenId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        if(db != null){
            c = db.rawQuery(query,null);
        }
        return c;
    }


    void DeleteChoosing(String gelenId){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID=?",new String[]{gelenId});
        db.close();
    }


}
