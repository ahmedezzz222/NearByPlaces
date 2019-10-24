package com.cognitev.nearbyapp;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cognitev.nearbyapp.Networking.NearRequestExplore_Model;
import com.cognitev.nearbyapp.Utilites.SharedPref;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Main_Activity extends AppCompatActivity {

    LinearLayout switch_mode;
    LinearLayout lay_nodata;
    TextView txt_mode;
    ListView listView;
    ProgressBar progress;
    FusedLocationProviderClient fusedLocationClient;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch_mode = (LinearLayout) findViewById(R.id.switchmode);
        lay_nodata = (LinearLayout)findViewById(R.id.lay_nodata);
        txt_mode = (TextView)findViewById(R.id.txt_mode);
        listView =(ListView) findViewById(R.id.listivew);
        progress =(ProgressBar) findViewById(R.id.progress);

        // switch between realtime mode and sindle update mode
        switch_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch_Mode_Realtime_Single();
            }
        });

        Switch_Mode_Realtime_Single();

        //TODO:_____.. First time enter to this screen
        new NearRequestExplore_Model().AllNearPlaces_API_firstload(getApplicationContext(), listView,progress);

    }

    @SuppressLint("SetTextI18n")
    public void Switch_Mode_Realtime_Single() {
        int mode = SharedPref.Retrive_Mode(getApplicationContext());
        //TODO single mode
        if (mode == 1) {
            SharedPref.Save_Mode(Main_Activity.this, 2);
            txt_mode.setText("Realtime");

            LocationModul.getInstance().destroy();

            DetectLocation();
        }
        //TODO realtime
        else {
            SharedPref.Save_Mode(Main_Activity.this, 1);
            txt_mode.setText("Single");

            LocationModul.getInstance().startLocation(getApplicationContext(), listView);
        }
    }

    public void DetectLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            SharedPref.SaveLATALONG(Main_Activity.this, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                        }
                    }
                });
    }

}

