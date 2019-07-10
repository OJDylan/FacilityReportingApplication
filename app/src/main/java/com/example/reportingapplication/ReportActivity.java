package com.example.reportingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reportingapplication.Database.DatabaseHelper;
import com.example.reportingapplication.Fragments.HomeFragment;
import com.example.reportingapplication.ReportDialogs.ReportDialog;
import com.example.reportingapplication.ReportDialogs.ReportDialogRoad;
import com.example.reportingapplication.ReportDialogs.ReportDialogSign;

public class ReportActivity extends AppCompatActivity implements ReportDialog.ReportDialogListener,
        ReportDialogRoad.ReportDialogListener, ReportDialogSign.ReportDialogListener {
    DatabaseHelper rDb;
    private RadioButton traffic, road, sign;
    private TextView rDetails;
    private Button btnReport;
    private static String desc;
    private AutoCompleteTextView rCity;
    private EditText rLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        rDb = new DatabaseHelper(this);
        traffic = findViewById(R.id.radio_traffic);
        road = findViewById(R.id.radio_road);
        sign = findViewById(R.id.radio_signs);
        rDetails = findViewById(R.id.report_details);
        rCity = findViewById(R.id.city_autocomplete);
        rLocation = findViewById(R.id.editText_location);
        btnReport = findViewById(R.id.add_report);

        //get city suggestions
        String[] cities = getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, cities);
        rCity.setAdapter(adapter);

        //onClick listener that adds report data into database
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rDetails.getText().toString().matches("none")){
                    if(traffic.isChecked()){
                        AddData("Traffic", desc, getLocation(), getCityName());
                    } else if (road.isChecked()){
                        AddData("Roads", desc, getLocation(), getCityName());
                    } else if (sign.isChecked()){
                        AddData("Signs", desc, getLocation(), getCityName());
                    }
                    finish();
                } else {
                    Toast.makeText(ReportActivity.this, "Report description must not be empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //adds data to reports database
    public void AddData (String title, String description, String loc, String city){
        boolean insertData = rDb.addReport(title, description, loc, city);
        if(insertData){
            Toast.makeText(this, "Report successfully made", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    //radio button onClick
    public void showDialog (View v){
        if (traffic.isChecked()){
            ReportDialog reportDialog = new ReportDialog();
            reportDialog.show(getSupportFragmentManager(), "report dialog");
        } else if (road.isChecked()){
            ReportDialogRoad rdRoad = new ReportDialogRoad();
            rdRoad.show(getSupportFragmentManager(), "report dialog");
        } else {
            ReportDialogSign rdSign = new ReportDialogSign();
            rdSign.show(getSupportFragmentManager(), "report dialog");
        }
    }

    //apply text from dialogBox to description
    @Override
    public void applyTexts(String description) {
        rDetails.setText(description);
        desc = description;
    }

    //gets city name string
    public String getCityName(){
        return rCity.getText().toString();
    }

    //gets location string
    public String getLocation(){
        return rLocation.getText().toString();
    }
}