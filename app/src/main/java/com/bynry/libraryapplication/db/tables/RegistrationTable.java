package com.bynry.libraryapplication.db.tables;

import android.net.Uri;

import com.bynry.libraryapplication.db.ContentDescriptor;

public class RegistrationTable {

    public static final String TABLE_NAME = "RegistrationTable";
    public static final String PATH = "REGISTRATION_TABLE";
    public static final int PATH_TOKEN = 1;
    public static final Uri CONTENT_URI = ContentDescriptor.BASE_URI.buildUpon().appendPath(PATH).build();

    public static class Cols{

        public static final String USER_NAME = "user_name";
        public static final String PASSWORD = "password";
        public static final String USER_CONTACT_NO = "user_contact_no";
        public static final String USER_EMAIL = "user_email";
        public static final String GENDER = "gender";
        public static final String USER_ADDRESS = "user_address";
    }
}
