package com.example.a6442_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchText;
    public static RBTree companies;
    public static RBTree countries;
    public static List<Space> rockets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.txtSearchText);
        String jsonData = LoadAndSave.getJson(this, "rocket_info.json");
        rockets = LoadAndSave.loadRocketFromJsonFile(jsonData);
        companies = LoadAndSave.loadCompanyFromXMLFile(LoadAndSave.getXML(this, "company.xml"));
        countries = LoadAndSave.loadCountryFromXMLFile(LoadAndSave.getXML(this, "country.xml"));
    }

    public void btnSearch_onclick(View view) {
        Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
        intent.setAction("search");
        intent.putExtra("text", searchText.getText().toString().toLowerCase());
        startActivity(intent);
    }

    public void btnAboutUs_onclick(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void btnAboutGetHelp_onclick(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}