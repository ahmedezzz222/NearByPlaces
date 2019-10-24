package com.cognitev.nearbyapp.Utilites;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.cognitev.nearbyapp.R;

import java.util.ArrayList;

public class Utilis {

    Context ctx;
    public Utilis(Context context) {
        this.ctx=context;
    }
   static ProgressDialog progressDialog;

    public static  void Show_Progress_Dialog(Context ctx) {

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        } else {
            progressDialog.show();
        }


    }

    public static  void init_Progress_Dialog(Context ctx) {
        //progressDialog = new ProgressDialog(ctx, R.style.AppTheme_Dark_Dialog);
        progressDialog = new ProgressDialog(ctx, R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("please wait...");
    }

}
