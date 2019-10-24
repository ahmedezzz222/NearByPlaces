package com.cognitev.nearbyapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.cognitev.nearbyapp.Ipermission.RuntimePermissionsActivity;



public class SplashScreenActivity extends RuntimePermissionsActivity {

    private static final int REQUEST_PERMISSIONS = 20;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void onPermissionsGranted(final int requestCode) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, Main_Activity.class);
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);

    }

/*	@Override
	public void onBackPressed() {
		finish();
	}*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        SplashScreenActivity.super.requestAppPermissions(new
                        String[]{
                         Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_COARSE_LOCATION ,
                        Manifest.permission.ACCESS_FINE_LOCATION
        },
                    R.string.runtime_permissions_txt,
                       REQUEST_PERMISSIONS);

    }


}
