package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class RewardsActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rewards);
        configureButton();
        ImageButton button1 = findViewById(R.id.recyclelogo);
        ImageButton button2 = findViewById(R.id.ic_lightbulb);
        ImageButton button3 = findViewById(R.id.ic_tree);
        ImageButton button4 = findViewById(R.id.ic_turtule);
        ImageButton button5 = findViewById(R.id.ic_windmill);
        ImageButton button6 = findViewById(R.id.ic_solarpanel);
        if(QrScannerActivity.points>20){
            button1.setOnClickListener(this);
        }
        if(QrScannerActivity.points > 1){
            button1.setOnClickListener(this);
        }
        if(QrScannerActivity.points > 5){
            button2.setOnClickListener(this);
        }
        if(QrScannerActivity.points>25){
            button3.setOnClickListener(this);
        }
        if(QrScannerActivity.points>50){
            button4.setOnClickListener(this);
        }
        if(QrScannerActivity.points>100){
            button5.setOnClickListener(this);
        }
        if(QrScannerActivity.points>500){
            button6.setOnClickListener(this);
        }

    }

    private void configureButton() {
        ImageButton buttonMap = findViewById(R.id.button_mapActivity);
        ImageButton buttonReview = findViewById(R.id.button_QrScannerActivity);

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RewardsActivity.this,MapsActivity.class));
            }
        });

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RewardsActivity.this, QrScannerActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recyclelogo:
                Intent a = new Intent(getApplicationContext(),RecycleActivity.class);
                startActivity(a);
                break;
            case R.id.ic_lightbulb:
                Intent b = new Intent(getApplicationContext(),LightbulbActivity.class);
                startActivity(b);
                break;
            case R.id.ic_tree:
                Intent c = new Intent(getApplicationContext(),TreeActivity.class);
                startActivity(c);
                break;
            case R.id.ic_turtule:
                Intent d = new Intent(getApplicationContext(),TurtleActivity.class);
                startActivity(d);
                break;
            case R.id.ic_windmill:
                Intent e = new Intent(getApplicationContext(),WindmillActivity.class);
                startActivity(e);
                break;
            case R.id.ic_solarpanel:
                Intent f = new Intent(getApplicationContext(),SolarActivity.class);
                startActivity(f);
                break;
        }
    }
}