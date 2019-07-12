package com.example.reportingapplication.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reportingapplication.Database.DatabaseHelper;
import com.example.reportingapplication.LoginActivity;
import com.example.reportingapplication.NewsItem.NewsItem;
import com.example.reportingapplication.NewsItem.NewsItemAdapter;
import com.example.reportingapplication.R;
import com.example.reportingapplication.ReportActivity;
import com.example.reportingapplication.RewardActivity;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseHelper myDb;
    private ArrayList<NewsItem> newsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myDb = new DatabaseHelper(getActivity());

        Intent intent = getActivity().getIntent();
        TextView text = view.findViewById(R.id.greetings);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        setGreetingsMessage(text, intent);

        //spinner that sorts data by city name
        Spinner cities = view.findViewById(R.id.city_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cities.setAdapter(adapter);
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();
                newsList.clear();
                boolean res = myDb.checkEmpty();
                if (res) {
                    switch (city) {
                        case "Shah Alam":
                            populateView(1);
                            break;
                        case "Petaling Jaya":
                            populateView(2);
                            break;
                        case "Klang":
                            populateView(3);
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Reports button onClick listener
        Button reportButton = view.findViewById(R.id.btn_report);
        reportButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReportActivity.class));
            }
        });

        //Rewards button onClick listener
        Button rewardButton = view.findViewById(R.id.btn_rewards);
        rewardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RewardActivity.class));
            }
        });
        return view;
    }

    //sets top greetings message
    public static void setGreetingsMessage(TextView text, Intent intent){
        String name = intent.getStringExtra(LoginActivity.EXTRA_TEXT);
        String message = "Greetings " + name;

        text.setText(message);
    }

    //populates recyclerView
    public void populateView(int n){
        Cursor sData = myDb.getSAData();
        Cursor pData = myDb.getPJData();
        Cursor kData = myDb.getKData();

        switch (n){
            case 1:
                while(sData.moveToNext()){
                    String title = sData.getString(1);
                    String desc = sData.getString(2);
                    switch (title) {
                        case "Traffic":
                            newsList.add(new NewsItem(R.drawable.ic_traffic_light_24dp, title, desc));
                            break;
                        case "Roads":
                            newsList.add(new NewsItem(R.drawable.ic_road_24dp, title, desc));
                            break;
                        case "Signs":
                            newsList.add(new NewsItem(R.drawable.ic_sign_24dp, title, desc));
                            break;
                    }
                }
                break;
            case 2:
                while(pData.moveToNext()){
                    String title = pData.getString(1);
                    String desc = pData.getString(2);
                    switch (title) {
                        case "Traffic":
                            newsList.add(new NewsItem(R.drawable.ic_traffic_light_24dp, title, desc));
                            break;
                        case "Roads":
                            newsList.add(new NewsItem(R.drawable.ic_road_24dp, title, desc));
                            break;
                        case "Signs":
                            newsList.add(new NewsItem(R.drawable.ic_sign_24dp, title, desc));
                            break;
                    }
                }
                break;
            case 3:
                while(kData.moveToNext()){
                    String title = kData.getString(1);
                    String desc = kData.getString(2);
                    switch (title) {
                        case "Traffic":
                            newsList.add(new NewsItem(R.drawable.ic_traffic_light_24dp, title, desc));
                            break;
                        case "Roads":
                            newsList.add(new NewsItem(R.drawable.ic_road_24dp, title, desc));
                            break;
                        case "Signs":
                            newsList.add(new NewsItem(R.drawable.ic_sign_24dp, title, desc));
                            break;
                    }
                }
                break;
        }
        mAdapter = new NewsItemAdapter(newsList);
        mRecyclerView.setAdapter(mAdapter);
    }
}