package com.bynry.libraryapplication.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private static SharedPreferences sharedPreferences;
    private static AppPreferences instance;

    public static AppPreferences getInstance(Context pContext) {
        if (instance == null)
            instance = new AppPreferences();


        sharedPreferences = pContext.getSharedPreferences(
                AppConstants.SHARED_PREF, Activity.MODE_PRIVATE);

        return instance;
    }

    public String getString(String key, String pDefaultVal)
    {
        return sharedPreferences.getString(key, pDefaultVal);
    }

    public void putString(String pKey, String pDefaultVal)
    {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(pKey, pDefaultVal);
        prefsEditor.apply();
    }

    public void deleteAllPreferences() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }

    public void deletePreferences(String name) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.remove(name);
        prefsEditor.apply();
    }

    public static void saveValue(Context context, String key, Object value) {
        SharedPreferences sharedPref = context.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        }
        editor.commit();
    }
}
