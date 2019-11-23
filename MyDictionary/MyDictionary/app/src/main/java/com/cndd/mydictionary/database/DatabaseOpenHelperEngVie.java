package com.cndd.mydictionary.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelperEngVie extends SQLiteAssetHelper {

    public static final String DATABASE_NAME_EN_VI = "anh_viet.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelperEngVie(Context context){
        super(context, DATABASE_NAME_EN_VI, null, DATABASE_VERSION);
    }

}
