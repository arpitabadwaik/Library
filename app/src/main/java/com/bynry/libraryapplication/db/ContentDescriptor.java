package com.bynry.libraryapplication.db;

import android.content.UriMatcher;
import android.net.Uri;

import com.bynry.libraryapplication.db.tables.RegistrationTable;

public class ContentDescriptor {

        public static final String AUTHORITY = "com.bynry.libraryapplication";
        public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
        public static final UriMatcher URI_MATCHER = buildUriMatcher();

        private static UriMatcher buildUriMatcher(){

            final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
            matcher.addURI(AUTHORITY, RegistrationTable.PATH, RegistrationTable.PATH_TOKEN);
            return matcher;
        }
}
