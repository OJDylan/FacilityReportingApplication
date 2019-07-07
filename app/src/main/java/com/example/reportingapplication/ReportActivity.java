package com.example.reportingapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reportingapplication.ReportDialogs.ReportDialog;
import com.example.reportingapplication.ReportDialogs.ReportDialogRoad;
import com.example.reportingapplication.ReportDialogs.ReportDialogSign;

public class ReportActivity extends AppCompatActivity implements ReportDialog.ReportDialogListener,
        ReportDialogRoad.ReportDialogListener, ReportDialogSign.ReportDialogListener {
    private RadioButton traffic, road, sign;
    private TextView rDetails;
    private Button btnReport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //get city suggestions
        String[] cities = getResources().getStringArray(R.array.cities);

        AutoCompleteTextView editText = findViewById(R.id.city_autocomplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, cities);
        editText.setAdapter(adapter);

        traffic = findViewById(R.id.radio_traffic);
        road = findViewById(R.id.radio_road);
        sign = findViewById(R.id.radio_signs);
        rDetails = findViewById(R.id.report_details);
        btnReport = findViewById(R.id.add_report);
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

    @Override
    public void applyTexts(String description) {
        rDetails.setText(description);
    }
}