package com.cndd.mydictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cndd.mydictionary.Word;

import java.util.ArrayList;

public class MyWordsDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MY_WORD";


    public MyWordsDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE My_word(id INTEGER primary key,"
                + "word TEXT,"
                + "content TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("word", word.getWord());
        values.put("content", word.getContent());
        db.insert("My_word", null, values);
        db.close();
    }

    public ArrayList<Word> getAllWords(){
        ArrayList<Word> words = new ArrayList<>();
        String script = "Select * from My_word";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script, null);
        while (cursor.moveToNext()){
            Word word = new Word();
            word.setId(cursor.getInt(0));
            word.setWord(cursor.getString(1));
            words.add(word);
        }
        return words;
    }

    public void deleteWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("My_word", "id=?", new String[]{String.valueOf(word.getId())});
    }
}