package com.cognitev.nearbyapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.cognitev.nearbyapp.Networking.NearRequestExplore_Model;
import com.cognitev.nearbyapp.Utilites.Constant;
import com.cognitev.nearbyapp.Utilites.SharedPref;

import java.util.List;

public class LocationModul {
    @SuppressLint("StaticFieldLeak")
    private static LocationModul instance = null;
    private LocationListener onLocationChange = null;
    private LocationManager locationManager = null;
    private String provider = null;
    private double lastLongitude = 0;
    private double lastLatitude = 0;
    private  Context ctx;


    // singleton
    static LocationModul getInstance() {
        if (instance == null) {
            synchronized (LocationModul.class) {
                if (instance == null) {
                    instance = new LocationModul();
                }
            }
        }
        return instance;
    }

      void startLocation(final Context context, final ListView listView) {
        try {
            if (context == null) {
                return;
            }
            ctx=context;

            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            onLocationChange = new LocationListener() {
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        try {
                            setLastLongitude(location.getLongitude());
                            setLastLatitude(location.getLatitude());

                            // save location
                            SharedPref.SaveLATALONG(context,String.valueOf(location.getLongitude()),String.valueOf(location.getLatitude()));

                            // update near places
                            new NearRequestExplore_Model().getInstance(context).AllNearPlaces_API(context,listView);

                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onProviderDisabled(String arg0) {
                }

                public void onProviderEnabled(String arg0) {
                }

                public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                    try {
                        if (provider != null) {
                            if (ContextCompat.checkSelfPermission(ctx,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                    ContextCompat.checkSelfPermission(ctx,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    Activity#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for Activity#requestPermissions for more details.
                                return;
                            }

                            // TODO requestLocationUpdates( provider, long min time , float min distance
                            locationManager.requestLocationUpdates(provider, Constant.LOCATION_DELAY_TIME_UNIT, (float) 500.0, onLocationChange);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            };
            List<String> providerList = locationManager.getProviders(true);
            if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
                provider = LocationManager.NETWORK_PROVIDER;
            } else {
                provider = null;
            }
            if (provider != null && locationManager != null) {
                if (ContextCompat.checkSelfPermission(ctx,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(ctx,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(provider, Constant.LOCATION_DELAY_TIME_UNIT, 1, onLocationChange);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

      void destroy() {
        try {
            if (locationManager != null) {
                if (onLocationChange != null) {
                    locationManager.removeUpdates(onLocationChange);
                }
                locationManager = null;
            }

            if (onLocationChange != null) {
                onLocationChange = null;
            }

            instance = null;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }




    public double getLastLongitude() {
        return lastLongitude;
    }

    private void setLastLongitude(double lastLastLongitude) {
        this.lastLongitude = lastLastLongitude;
    }

    public double getLastLatitude() {
        return lastLatitude;
    }

    private void setLastLatitude(double lastLatitude) {
        this.lastLatitude = lastLatitude;
    }
}
