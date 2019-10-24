package com.cognitev.nearbyapp.Utilites;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPref {

    public static  void SaveLATALONG(Context ct, String lat, String along) {

        SharedPreferences.Editor editor = ct.getSharedPreferences("latlong", MODE_PRIVATE).edit();
        editor.putString("lat", lat);
        editor.putString("long", along);
        editor.apply();
    }

    public static   String RetriveLATALONG(Context ct) {

        SharedPreferences prefs = ct.getSharedPreferences("latlong", MODE_PRIVATE);
        String alat = prefs.getString("lat", "29.961116");
        String aLong = prefs.getString("long", "30.960700");
        return (alat + "," + aLong);
    }


    //TODO: -----   single mode=1   Realtime Mode =2      Default is Realtime
    public static  void Save_Mode(Context ct, int single) {
        SharedPreferences.Editor editor = ct.getSharedPreferences("mode", MODE_PRIVATE).edit();
        editor.putInt("mode", single);
        editor.apply();
    }

    public static int Retrive_Mode(Context ct) {

        SharedPreferences prefs = ct.getSharedPreferences("mode", MODE_PRIVATE);
        int mode = prefs.getInt("mode", 2 );
         return  mode;
    }
}
