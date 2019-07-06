package com.example.reportingapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity implements ReportDialog.ReportDialogListener {
    private RadioButton traffic, road, sign;
    private TextView rDetails;

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

        //add database
    }

    //radio button onClick
    public void showDialog (View v){
        if (traffic.isChecked()){
            ReportDialog reportDialog = new ReportDialog();
            reportDialog.show(getSupportFragmentManager(), "report dialog");
        } else if (road.isChecked()){
            Toast.makeText(this, "Road Selected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Signs Selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void applyTexts(String description) {
        rDetails.setText(description);
    }
}