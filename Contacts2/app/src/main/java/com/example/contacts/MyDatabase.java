package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase<db> extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Contact_Manager";
    public MyDatabase(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script= "Create table Contact(id Integer Primary Key, name Text, phone Text)";
        db.execSQL(script);
    }
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",contact.getName());
        values.put("phone",contact.getPhone());
        db.insert("Contact",null,values);
    }
    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String script = "Select * from Contact";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script,null);
        while (cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contacts.add(contact);
        }
        return contacts;
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Contact","id = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public void updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",contact.getName());
        values.put("phone",contact.getPhone());
        db.update("Contact",values,"id = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
