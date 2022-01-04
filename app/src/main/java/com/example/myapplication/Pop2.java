package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Pattern;

public class Pop2 extends Activity {
    public EditText centerName;
    public EditText centerAdress;
    public double latitude;
    public double longitude;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        centerName = findViewById(R.id.centerName);
        centerAdress = findViewById(R.id.adress);

        Button addCenter = findViewById(R.id.addCenterBtn);

        addCenter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String address= centerAdress.getText().toString();
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAdress(address, getApplicationContext(), new GeoHandler());
            }
        });

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.6));

    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String address;
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    address=bundle.getString("address");
                    break;
                default:
                    address = null;
            }
            // Step 1: Define REGEX
            String my_pattern = "\\s";

            // Step 2: Create a pattern using compile method
            Pattern pattern = Pattern.compile(my_pattern);

            // Step 3: Create an array of strings using the
            // pattern we already defined
            String[] string_array = pattern.split(address);
            latitude = Double.parseDouble(string_array[0]);
            longitude = Double.parseDouble(string_array[1]);
            String numeReciclare = centerName.getText().toString();
            LatLng  centru = new LatLng(latitude,longitude);
            MapsActivity.mMap.addMarker((new MarkerOptions().position(centru).title(numeReciclare)));

        }
    }
}
