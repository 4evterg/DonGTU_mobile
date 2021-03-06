package com.chetverg.dongtu_mobile.api;

/**
 * Created by chetverg on 01.06.16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // Версия БД для обновления структуры БД необходимо изменить значение
    private static final int DATABASE_VERSION = 1;

    //Название БД
    private static final String DATABASE_NAME = "DonGTU_DB";

    //название таблицы
    private static final String TABLE_USER = "user_table";

    //имена столбцов БД
    private static final String KEY_ID = "id";
    private static final String KEY_UID =  "uid";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "second_name";
    private static final String KEY_THIRDNAME = "third_name";
    private static final String KEY_CITY = "city";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_CATHEDRA = "cathedra";
    private static final String KEY_GROUP = "user_group";
    private static final String KEY_POST = "post";
    private static final String KEY_PHOTO = "photo";



    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_UID + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_SURNAME + " TEXT,"
                + KEY_THIRDNAME + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_COUNTRY + " TEXT,"
                + KEY_CATHEDRA + " TEXT,"
                + KEY_GROUP + " TEXT,"
                + KEY_POST + " TEXT,"
                + KEY_PHOTO + " TEXT"
                + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    //на случай изменения версии БД
    public void freshDB(SQLiteDatabase db){
        //сброс существующеей таблицы
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        //создание новой
        onCreate(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        freshDB(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        freshDB(db);
    }


    /**
     * Storing user details in database
     * */
    public void addUser(String uid, String name, String surname, String third_name, String city, String country, String cathedra, String group, String post, String photo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UID, uid); // uid
        values.put(KEY_NAME, name); // Name
        values.put(KEY_SURNAME, surname); // Second name
        values.put(KEY_THIRDNAME, third_name); // Third name
        values.put(KEY_CITY, city); //
        values.put(KEY_COUNTRY, country); //
        values.put(KEY_CATHEDRA, cathedra); //
        values.put(KEY_GROUP, group); //
        values.put(KEY_POST, post); //
        values.put(KEY_PHOTO, photo); //

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(KEY_UID, cursor.getString(1));
            user.put(KEY_NAME, cursor.getString(2));
            user.put(KEY_SURNAME, cursor.getString(3));
            user.put(KEY_THIRDNAME, cursor.getString(4));
            user.put(KEY_CITY, cursor.getString(5));
            user.put(KEY_COUNTRY, cursor.getString(6));
            user.put(KEY_CATHEDRA, cursor.getString(7));
            user.put(KEY_GROUP, cursor.getString(8));
            user.put(KEY_POST, cursor.getString(9));
            user.put(KEY_PHOTO, cursor.getString(10));
            //user.put("privilegue_level", String.valueOf(cursor.getInt(3)));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}