package com.cndd.mydictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cndd.mydictionary.Word;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessVieEng {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccessVieEng instance;
    private long count;

    private DatabaseAccessVieEng(Context context){
        this.openHelper = new DatabaseOpenHelperVieEng(context);
    }

    public static DatabaseAccessVieEng getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccessVieEng(context);
        }
        return instance;
    }

    public long getCount() {
        return DatabaseUtils.queryNumEntries(this.database, "viet_anh");
    }

    public void open(){
        this.database = instance.openHelper.getWritableDatabase();
    }

    public void close(){
        if (database != null){
            this.database.close();
        }
    }

    public void addWord(Word word){
        ContentValues values = new ContentValues();
        values.put("id", word.getId());
        values.put("word", word.getWord());
        values.put("content", word.getContent());
        database.insert("viet_anh", null, values);
    }

    public List<Word> getWords(){
        List<Word> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM 'viet_anh'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Word mword = new Word(Integer.parseInt(cursor.getString(0).toString()),cursor.getString(1).toString());
            list.add(mword);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String getMeaningById(int id){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM 'viet_anh' WHERE id=" + id, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor.getString(2);
    }

}
