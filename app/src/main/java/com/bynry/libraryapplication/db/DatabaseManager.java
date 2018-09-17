package com.bynry.libraryapplication.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bynry.libraryapplication.activities.LoginActivity;
import com.bynry.libraryapplication.activities.SignUpActivity;
import com.bynry.libraryapplication.db.tables.RegistrationTable;

import java.util.ArrayList;

public class DatabaseManager {

    public static int cnt;
    private Context context;
    private static ArrayList<LoginActivity> dataList = new ArrayList<>();

    public static String getDbPath(Context context) {
        return context.getDatabasePath("libraryApplication.db").getAbsolutePath();
    }


    // Insert values in to database

    public static void saveRegistrationDetails(Context signUpActivity, SignUpActivity data) {
        saveUserInfo(signUpActivity, data);
    }

    public static void saveUserInfo(Context signUpActivity, SignUpActivity data) {
        if (data != null) {
            ContentValues values = getContentValuesLoginTable(data);
            saveValues(signUpActivity, RegistrationTable.CONTENT_URI, values, null);
        }
    }



    public static ContentValues getContentValuesLoginTable(SignUpActivity data) {
        ContentValues values = new ContentValues();
        try {
            values.put(RegistrationTable.Cols.USER_NAME, data.userName != null ? data.userName : "");
            values.put(RegistrationTable.Cols.PASSWORD, data.password != null ? data.password : "");
            values.put(RegistrationTable.Cols.USER_CONTACT_NO, data.contactNo != null ? data.contactNo : "");
            values.put(RegistrationTable.Cols.USER_EMAIL, data.email != null ? data.email : "");
            values.put(RegistrationTable.Cols.GENDER, data.gender != null ? data.gender : "");
            values.put(RegistrationTable.Cols.USER_ADDRESS, data.address != null ? data.address : "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    public static void saveValues(Context signUpActicity, Uri table, ContentValues values, String condition) {

        try {
            ContentResolver resolver = signUpActicity.getContentResolver();
            Cursor cursor = resolver.query(table, null, condition, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                resolver.insert(table, values);
            } else {
                resolver.insert(table, values);
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }




    // For selecting stored values from database

    public static ArrayList<LoginActivity> getData(){
        SQLiteDatabase sqLiteDatabase = DatabaseProvider.dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + RegistrationTable.TABLE_NAME, null);
        ArrayList<LoginActivity> cards = new ArrayList<>();
        cards = getDataListFromCursor(cursor);
        if(cursor != null){
            cnt = cursor.getCount();
            cursor.close();
        }
        if(sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        return cards;
    }

    public static ArrayList<LoginActivity> getDataListFromCursor(Cursor cursor){
        ArrayList<LoginActivity> cards = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                cards = getDataFromCursor(cursor);
                cursor.moveToNext();
            }

            if (cursor != null)
                cursor.close();
        }
        return cards;
    }

    public static ArrayList<LoginActivity> getDataFromCursor(Cursor cursor){
        LoginActivity newCards = new LoginActivity();
        newCards.userName = cursor.getString(cursor.getColumnIndex(RegistrationTable.Cols.USER_NAME))!= null ? cursor.getString(cursor.getColumnIndex(RegistrationTable.Cols.USER_NAME)) : "";
        newCards.password = cursor.getString(cursor.getColumnIndex(RegistrationTable.Cols.PASSWORD)) != null ? cursor.getString(cursor.getColumnIndex(RegistrationTable.Cols.PASSWORD)) : "";
        dataList.add(newCards);
        return dataList;
    }
}
