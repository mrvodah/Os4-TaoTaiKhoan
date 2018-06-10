package com.example.vietvan.taotaikhoan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseManager extends SQLiteAssetHelper {

    private static final String TAG = "TAG";
    public static String DB_NAME = "account.db";
    public static String TABLE = "user";
    public static int DB_VERSION = 1;

    public DatabaseManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void add(String user, String pass, String passtouch){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user);
        values.put("password", pass);
        values.put("passtouch", passtouch);

        sqLiteDatabase.insert(TABLE, null, values);

    }

    public List<User> getList(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE, null);
        List<User> list = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));

            cursor.moveToNext();
        }

        Log.d(TAG, "getList: " + list.size());
        return list;

    }

    public boolean isExists(String user){
        List<User> list = getList();
        for(User u : list)
            if(u.getUsername().equals(user))
                return true;

        return false;
    }

    public boolean checkPassTouch(String user, String passtouch){
        List<User> list = getList();
        for(User u : list)
            if(u.getUsername().equals(user) && u.getPasstouch().equals(passtouch))
                return true;

        return false;
    }

    public User getUser(String user, String pass){
        List<User> list = getList();
        for(User u : list)
            if(u.getUsername().equals(user) && u.getPassword().equals(pass))
                return u;

        return null;
    }

    public User access(String user){
        List<User> list = getList();
        for(User u : list)
            if(u.getUsername().equals(user))
                return u;

        return null;
    }

}
