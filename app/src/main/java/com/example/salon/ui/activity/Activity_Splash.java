package com.example.salon.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.salon.R;

public class Activity_Splash extends AppCompatActivity {

    AlertDialog alertDialog;
    Button btn_datamobile, btn_wifi,btn_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Connect();
    }

    private void Connect() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected()){
                    if(isLocationEnabled())
                    {
                        startActivity(new Intent(Activity_Splash.this, MainActivity.class));
                        finish();
                    }
                    else
                    {
                        SplashShowLocation();
                    }
                }
                else
                {
                    SplashShowInternet();
                }
            }
        }, 2000);
    }

    private void SplashShowInternet()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_splash_internet, null);
        builder.setView(view);
        alertDialog = builder.create();

        alertDialog.setCancelable(false);

        btn_wifi = view.findViewById(R.id.btn_wifi);
        btn_datamobile = view.findViewById(R.id.btn_datamobile);

        btn_datamobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                startActivity(new Intent(Settings.ACTION_DATA_USAGE_SETTINGS));
                Connect();
            }
        });

        btn_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                Connect();
            }
        });

        alertDialog.show();
    }

   private void SplashShowLocation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_splash_location, null);
        builder.setView(view);
        alertDialog = builder.create();

        alertDialog.setCancelable(false);

        btn_location = view.findViewById(R.id.btn_location);


        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                // در بخش کد Java
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                Connect();
            }
        });



        alertDialog.show();
    }
    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}