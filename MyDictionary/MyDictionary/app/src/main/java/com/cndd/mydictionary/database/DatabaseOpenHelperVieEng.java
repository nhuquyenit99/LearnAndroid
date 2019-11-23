package com.cndd.mydictionary.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelperVieEng extends SQLiteAssetHelper {

    public static final String DATABASE_NAME_VI_EN = "viet_anh.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelperVieEng(Context context){
        super(context, DATABASE_NAME_VI_EN, null, DATABASE_VERSION);
    }

}
