package com.android.radiobuttons;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DB="student.db";
    public static final int VER=1;
    public DBmain(@Nullable Context context) {
        super(context, DB, null, VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qauery="create table studenttable(id integer primary key,gender text)";
        db.execSQL(qauery);}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    { String qauery="drop table if exists studenttable";
        db.execSQL(qauery);
        onCreate(db);
    }
}