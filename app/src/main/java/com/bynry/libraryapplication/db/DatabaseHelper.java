package com.bynry.libraryapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bynry.libraryapplication.db.tables.RegistrationTable;

import java.text.MessageFormat;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String KEY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS {0} ({1})";
    public static final String KEY_DROP_TABLE = "DROP TABLE IF EXISTS {0}";
    public final static String SQL = "SELECT COUNT(*) FROM sqlite_master WHERE name=?";
    public static final String TAG = "DatabaseHelper";
    private static final int CURRENT_DB_VERSION = 1;
    private static final String DB_NAME = "libraryApplication.db";
    private static final String DROP_RECORD_TRIGGER = "drop_records";

    public DatabaseHelper(Context context){

        super(context, DB_NAME,null,CURRENT_DB_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){

        createLoginTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createLoginTable(SQLiteDatabase sqLiteDatabase){

        String loginTableFields = RegistrationTable.Cols.USER_NAME + " VARCHAR, " +
                RegistrationTable.Cols.PASSWORD + " VARCHAR, " +
                RegistrationTable.Cols.USER_CONTACT_NO + " VARCHAR, " +
                RegistrationTable.Cols.USER_EMAIL + " VARCHAR, " +
                RegistrationTable.Cols.GENDER + " VARCHAR, " +
                RegistrationTable.Cols.USER_ADDRESS + " VARCHAR";
        createTable(sqLiteDatabase, RegistrationTable.TABLE_NAME, loginTableFields);

    }

    public void createTable(SQLiteDatabase sqLiteDatabase, String name, String fields) {
        String query = MessageFormat.format(DatabaseHelper.KEY_CREATE_TABLE,
                name, fields);
        sqLiteDatabase.execSQL(query);
    }

}
