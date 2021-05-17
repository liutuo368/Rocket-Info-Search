package com.example.a6442_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RocketDetailActivity extends AppCompatActivity {

    private TextView companyInfo;
    private TextView locationInfo;
    private TextView datumInfo;
    private TextView detailInfo;
    private TextView statusRocket;
    private TextView rocketInfo;
    private TextView statusMission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocket_detail);
        companyInfo = findViewById(R.id.txtCompanyInfo);
        locationInfo = findViewById(R.id.txtLocationInfo);
        datumInfo = findViewById(R.id.txtDatumInfo);
        detailInfo = findViewById(R.id.txtDetailInfo);
        statusRocket = findViewById(R.id.txtStatusRocket);
        rocketInfo = findViewById(R.id.txtRocket);
        statusMission = findViewById(R.id.txtStatusMission);
        Intent intent = getIntent();
        int index = 0;
        if(intent.getAction().equals("rocketInfo")){
            index = Integer.parseInt(intent.getStringExtra("index"));
        }
        Space rocket = MainActivity.rockets.get(index);
        companyInfo.setText(rocket.getCompany());
        locationInfo.setText(rocket.getLocation());
        datumInfo.setText(rocket.getDatum());
        detailInfo.setText(rocket.getDetail());
        statusRocket.setText(rocket.getStatus_rocket());
        rocketInfo.setText(rocket.getRocket());
        statusMission.setText(rocket.getStatus_mission());
    }
}