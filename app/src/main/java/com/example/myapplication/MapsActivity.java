package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        configureButton();
        Button btnAddCenter = findViewById(R.id.btnAddCenter);
        btnAddCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_popup();
            }
        });
    }

    public void open_popup() {
        Intent intent = new Intent(this, Pop2.class);
        startActivity(intent);
    }

    private void configureButton() {
        ImageButton buttonMap = findViewById(R.id.button_QrScannerActivity);
        ImageButton buttonReview = findViewById(R.id.button_mainActivity);

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, QrScannerActivity.class));
            }
        });

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, RewardsActivity.class));
            }
        });
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng cluj = new LatLng(46.770439, 23.591423);
        //mMap.addMarker(new MarkerOptions().position(cluj).title("Marker in Cluj-Napoca"));
        mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                        cluj, 13f
                ));

        LatLng centru1 = new LatLng(46.7643211,23.5676296);
        mMap.addMarker((new MarkerOptions().position(centru1).title("Centru Colectare Materiale Reciclabile")));

        LatLng centru2 = new LatLng(46.7863536,23.5983725);
        mMap.addMarker((new MarkerOptions().position(centru2).title("Recycle International")));

        LatLng centru3 = new LatLng(46.7672833,23.5984033);
        mMap.addMarker((new MarkerOptions().position(centru3).title("Rematinvest")));
    }


}